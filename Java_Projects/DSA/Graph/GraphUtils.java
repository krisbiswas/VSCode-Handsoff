package Java_Projects.DSA.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

class GraphUtils {

    private static HashMap<Integer,Integer> distanceTable;

    private static int rec(Graph map, int start, int end, boolean[] visited){
        if(start == end){
            return 0;
        }else{
            if(visited[start]){

            }else{
                visited[start] = true;
                int costToEnd = rec(map, start, end, visited);
                
            }

        }
        return -1;
    }

    public static int dijkstra(Graph map, int src, int dest){
        distanceTable = new HashMap<>();
        PriorityQueue<Edge> visitQue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });
        visitQue.addAll(map.edges.get(src));

        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distanceMap = new HashMap<>();
        distanceMap.put(src, 0);

        while (!visitQue.isEmpty()) {
            Edge shortEdge = visitQue.poll();
            // paid the cost to dest, now src is shortEdge.dest
            if(distanceMap.containsKey(shortEdge.dest)){
                int oldDist = distanceMap.get(shortEdge.dest);
                if(distanceMap.get(shortEdge.src) + shortEdge.cost < oldDist){
    
                }
            }else{
                distanceMap.put(shortEdge.dest, distanceMap.get(shortEdge.src) + shortEdge.cost);
            }
            // add all places that can be visited from shortEdge.dest or current src
            visitQue.addAll(map.edges.get(shortEdge.dest));
            visited.add(shortEdge.src);
        }
        return distanceTable.get(dest);
    }

    private static class DistanceItem {
        Integer node;
        Integer distance;
    }
}