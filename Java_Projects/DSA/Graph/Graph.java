package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph {
    HashMap<Integer, ArrayList<Integer>> nodes;
    HashMap<Integer, ArrayList<Edge>> edges;

    Graph (int size) {
        nodes = new HashMap<>(size);
        edges = new HashMap<>();
    }

    public void add(int src, int dest, int distance){
        if(!nodes.containsKey(src)){
            nodes.put(src, new ArrayList<>());
        }
        nodes.get(src).add(dest);

        if(!edges.containsKey(src)){
            edges.put(src, new ArrayList<>());
        }
        edges.get(src).add(new Edge(src, dest, distance));
    }

    public void print(){
        for (Entry edge : edges.entrySet()) {
            System.out.println(edge);
        }
    }
}

class Edge {
    int src, dest, cost;

    public Edge(int src, int dest, int distance) {
        this.src = src;
        this.dest = dest;
        cost = distance;
    }

    @Override
    public String toString() {
        return String.format("{%d->%d, (%d)}", src, dest, cost);
    }
}