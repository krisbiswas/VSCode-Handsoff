package AStarSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AStar {
  @FunctionalInterface
  public interface Heuristic {
    int estimate(String from, String to);
  }

  public static class PathResult {
    private final List<String> path;
    private final int cost;
    private final boolean found;

    public PathResult(List<String> path, int cost, boolean found) {
      this.path = path;
      this.cost = cost;
      this.found = found;
    }

    public List<String> getPath() {
      return path;
    }

    public int getCost() {
      return cost;
    }

    public boolean isFound() {
      return found;
    }
  }

  private final Heuristic heuristic;

  public AStar() {
    this((from, to) -> 0);
  }

  public AStar(Heuristic heuristic) {
    this.heuristic = heuristic;
  }

  public PathResult findShortestPath(Graph graph, String start, String goal) {
    if (!graph.containsNode(start) || !graph.containsNode(goal)) {
      return new PathResult(List.of(), 0, false);
    }

    if (start.equals(goal)) {
      return new PathResult(List.of(start), 0, true);
    }

    Map<String, Integer> gScore = new HashMap<>();
    Map<String, String> cameFrom = new HashMap<>();
    PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::fScore));

    gScore.put(start, 0);
    openSet.offer(new Node(start, heuristic.estimate(start, goal)));

    while (!openSet.isEmpty()) {
      Node current = openSet.poll();
      String currentNode = current.name;

      if (currentNode.equals(goal)) {
        return new PathResult(reconstructPath(cameFrom, goal), gScore.get(goal), true);
      }

      int currentG = gScore.get(currentNode);
      if (current.fScore > currentG + heuristic.estimate(currentNode, goal)) {
        continue;
      }

      for (Graph.Edge edge : graph.neighbors(currentNode)) {
        int tentativeG = currentG + edge.distance;

        if (tentativeG < gScore.getOrDefault(edge.to, Integer.MAX_VALUE)) {
          cameFrom.put(edge.to, currentNode);
          gScore.put(edge.to, tentativeG);
          int fScore = tentativeG + heuristic.estimate(edge.to, goal);
          openSet.offer(new Node(edge.to, fScore));
        }
      }
    }

    return new PathResult(List.of(), 0, false);
  }

  private List<String> reconstructPath(Map<String, String> cameFrom, String goal) {
    List<String> path = new ArrayList<>();
    String current = goal;

    while (current != null) {
      path.add(current);
      current = cameFrom.get(current);
    }

    Collections.reverse(path);
    return path;
  }

  private static class Node {
    private final String name;
    private final int fScore;

    private Node(String name, int fScore) {
      this.name = name;
      this.fScore = fScore;
    }

    private int fScore() {
      return fScore;
    }
  }
}
