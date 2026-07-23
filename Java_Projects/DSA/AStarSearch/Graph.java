package AStarSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addEdge(String from, String to, int distance) {
        adjacencyList.computeIfAbsent(from, key -> new ArrayList<>())
                .add(new Edge(from, to, distance));
        adjacencyList.computeIfAbsent(to, key -> new ArrayList<>());
    }

    public List<Edge> neighbors(String node) {
        return adjacencyList.getOrDefault(node, List.of());
    }

    public boolean containsNode(String node) {
        return adjacencyList.containsKey(node);
    }

    public static class Edge {
        public final String from;
        public final String to;
        public final int distance;

        public Edge(String from, String to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }
}
