package AStarSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final String DEFAULT_INPUT = "AStarSearch/input.txt";

  public static void main(String[] args) {
    String inputPath = args.length > 0 ? args[0] : DEFAULT_INPUT;
    AStar aStar = new AStar();

    try {
      List<TestCase> testCases = parseInput(inputPath);
      for (int i = 0; i < testCases.size(); i++) {
        runTestCase(i + 1, testCases.get(i), aStar);
      }
    } catch (IOException e) {
      System.err.println("Failed to read input file: " + inputPath);
      e.printStackTrace();
    }
  }

  private static void runTestCase(int testCaseNumber, TestCase testCase, AStar aStar) {
    System.out.println("TC " + testCaseNumber + ":");

    for (Query query : testCase.queries) {
      AStar.PathResult result = aStar.findShortestPath(testCase.graph, query.start, query.goal);

      if (!result.isFound()) {
        System.out.println("Query " + query.start + " -> " + query.goal + ": No path found");
        continue;
      }

      System.out.println("Query " + query.start + " -> " + query.goal + ":");
      System.out.println("  Path: " + String.join(" -> ", result.getPath()));
      System.out.println("  Cost: " + result.getCost());
    }

    System.out.println();
  }

  private static List<TestCase> parseInput(String inputPath) throws IOException {
    List<TestCase> testCases = new ArrayList<>();
    TestCase current = null;

    try (BufferedReader reader = new BufferedReader(new FileReader(Path.of(inputPath).toFile()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        if (line.startsWith("TC")) {
          current = new TestCase();
          testCases.add(current);
          continue;
        }

        if (current == null) {
          current = new TestCase();
          testCases.add(current);
        }

        if (line.startsWith("#0")) {
          String[] parts = line.substring(2).trim().split("\\s+");
          if (parts.length < 3) {
            throw new IOException("Invalid edge line: " + line);
          }
          String from = parts[0];
          String to = parts[1];
          int distance = Integer.parseInt(parts[2]);
          current.graph.addEdge(from, to, distance);
        } else if (line.startsWith("#1")) {
          String[] parts = line.substring(2).trim().split("\\s+");
          if (parts.length < 2) {
            throw new IOException("Invalid query line: " + line);
          }
          current.queries.add(new Query(parts[0], parts[1]));
        } else {
          throw new IOException("Unrecognized line: " + line);
        }
      }
    }

    return testCases;
  }

  private static class TestCase {
    private final Graph graph = new Graph();
    private final List<Query> queries = new ArrayList<>();
  }

  private static class Query {
    private final String start;
    private final String goal;

    private Query(String start, String goal) {
      this.start = start;
      this.goal = goal;
    }
  }
}
