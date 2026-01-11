package Grid_Teleportation3552;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // String[] matrix = new String[]{"B","G"};
        // String[] matrix = new String[]{"C#","#B"};
        // String[] matrix = new String[]{".","#"};
        String[] matrix = new String[]{".#...",".#.#.",".#.#.","...#."};
        // String[] matrix = new String[]{
        //     "..C#A",
        //     "C.B#A",
        //     "#.A#.",
        //     "##AC#",
        //     "..BC.",
        //     "CA..."
        // };
        // String[] matrix = new String[]{".A","CA"};
        Main m = new Main();
        int ans = m.minMoves(matrix);
        System.out.println("Move = "+ans);
    }

    class Pair implements Comparable<Pair> {
        int x, y;
        Pair(int x, int y){
            this.x=x;this.y=y;
        }
        
        public int compareTo(Pair p){
            int thisDistance = (rows-x)*(rows-x) + (cols-y)*(cols-y);
            int pDistance = (rows-p.x)*(rows-p.x) + (cols-p.y)*(cols-p.y);
            return thisDistance-pDistance;
        }

        public String toString(){
            return "("+x+","+y+")";
        }
    }

    int rows, cols;
    char[][] grid;
    boolean[][] visited;
    int[] dr = new int[]{0, 1, 0, -1};
    int[] dc = new int[]{1, 0, -1, 0};

    public int minMoves(String[] matrix) {
        HashMap<Character, PriorityQueue<Pair>> map = new HashMap<>();
        grid = new char[matrix.length][];
        rows = grid.length;
        cols = matrix[0].length();
        for(int i=0;i<matrix.length;i++){
            grid[i] = matrix[i].toCharArray();
            for(int j=0;j<grid[i].length;j++){
                if((i==0 && j==0) || grid[i][j] == '.' || grid[i][j] == '#') continue;
                if(!map.containsKey(grid[i][j])){
                    map.put(grid[i][j], new PriorityQueue<Pair>());
                }
                map.get(grid[i][j]).offer(new Pair(i, j));
            }
        }
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        int count = move(map);
        return count;
    }

    int move(HashMap<Character, PriorityQueue<Pair>> teleMap){
        if(grid[rows-1][cols-1] == '#'){
            return -1;
        }
        int moveCount = 0;
        PriorityQueue<Pair> neighbourPq = new PriorityQueue<>();
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(0,0));
        boolean hasReached = false;
        while(!q.isEmpty() && !hasReached){
            Pair p = q.poll();
            if(p.x == rows-1 && p.y == cols-1){
                hasReached = true;
            }
            visited[p.x][p.y] = true;
            if(grid[p.x][p.y] == '.'){
                addNeighboursToQueue(neighbourPq, p);
            } else {
                char gate = grid[p.x][p.y];
                PriorityQueue<Pair> gatePositions = teleMap.get(gate);
                if(gatePositions != null){
                    Pair teleportedPair = gatePositions.poll();
                    if(teleportedPair != null){
                        p = teleportedPair;
                    }
                }
                if(p.x == rows-1 && p.y == cols-1){
                    hasReached = true;
                }
                visited[p.x][p.y] = true;
                addNeighboursToQueue(neighbourPq, p);
            }
            if(q.isEmpty()){
                moveCount++;
                System.out.println(moveCount);
                q = neighbourPq;
                neighbourPq = new PriorityQueue<>();
            }
        }
        return hasReached? moveCount : -1;
    }

    void addNeighboursToQueue(PriorityQueue<Pair> q, Pair p){
        for(int i=0;i<4;i++){
            int r = p.x+dr[i];
            int c = p.y+dc[i];
            if(isValidPoint(r, c) && !visited[r][c] && grid[r][c] != '#'){
                q.offer(new Pair(r, c));
            }
        }
    }

    boolean isValidPoint(int r, int c){
        return !(r<0 || r>=rows || c<0 || c>=cols);
    }
}