package Floyd_Warshell;

import java.util.Arrays;

public class Main {
    private static int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[][] graph = new int[][]{
            {0, 2, MAX, MAX},
            {1, 0, 3, MAX},
            {MAX, MAX, 0, MAX},
            {3, 5, 4, 0}
        };
        int[][] negativeCycleGraph = new int[][]{
            {0, -2, MAX},
            {MAX, 0, -3},
            {2, MAX, 0},
        };
        int n=4;
        // int n=3;
        floydWarshelAlgo(n, graph);
        // floydWarshelAlgo(n, negativeCycleGraph);
        for(int j=0;j<n;j++){
            System.out.println(Arrays.toString(graph[j]));
            // System.out.println(Arrays.toString(negativeCycleGraph[j]));
        }
    }

    private static void floydWarshelAlgo(int n, int[][] graph) {
        // Multi-source shortest distance finding algo
        // Brute force -> finding distance all possible distances from i->j via k, 
        // whichever is min, we keep that
        boolean hasNegativeCycle = false;
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(graph[i][k] != MAX && graph[k][j] != MAX && (graph[i][k] < MAX - graph[k][j])){
                        graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                    }
                    if(graph[i][j] < 0){
                        hasNegativeCycle = true;
                    }
                }
            }
        }
        System.out.println("hasNegativeCycle= "+ hasNegativeCycle);
    }
}
