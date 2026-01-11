package DSA.ContainVirus;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        // int[][] infectedGrid = new int[][]{
        //     {1,1,1,0,0,0,0,0,0},
        //     {1,0,1,0,1,1,1,1,1},
        //     {1,1,1,0,0,0,0,0,0}
        // };
        // int[][] infectedGrid = new int[][]{
        //     {0,1,0,0,0,0,0,1},
        //     {0,1,0,0,0,0,0,1},
        //     {0,0,0,0,0,0,0,1},
        //     {0,0,0,0,0,0,0,0}
        // };
        int[][] infectedGrid = new int[][]{
            {0,1,0,1,1,1,1,1,1,0},
            {0,0,0,1,0,0,0,0,0,0},
            {0,0,1,1,1,0,0,0,1,0},
            {0,0,0,1,1,0,0,1,1,0},
            {0,1,0,0,1,0,1,1,0,1},
            {0,0,0,1,0,1,0,1,1,1},
            {0,1,0,0,1,0,0,1,1,0},
            {0,1,0,1,0,0,0,1,1,0},
            {0,1,1,0,0,1,1,0,0,1},
            {1,0,1,1,0,1,0,1,0,1}
        };
        Solution s = new Solution();
        System.out.println("Total walls installed = "+s.containVirus(infectedGrid));
    }

    int[][] d = new int[][]{
        new int[]{-1, 0},
        new int[]{0, -1},
        new int[]{1, 0},
        new int[]{0, 1},
    };
    int rows, cols;
    int[][] grid;

    public int containVirus(int[][] isInfected) {
        rows = isInfected.length;
        cols = isInfected[0].length;
        int numerOfWallsUsed = 0;
        grid = new int[rows][];
        for (int i = 0; i < rows; i++) {
            grid[i] = Arrays.copyOf(isInfected[i], isInfected[i].length);
        }
        int maxSpan = -1;
        while(maxSpan != 0){
            // Identify all the virus regions 
            // store in PQ by the order of their (span=list of points)
            int maxSpanRow=0, maxSpanCol=0;
            maxSpan = 0;
            boolean[][] visited = new boolean[rows][cols];
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(grid[i][j] != 1 || visited[i][j]) continue;
                    int span = traverse(i, j, visited);
                    if(span > maxSpan){
                        maxSpanRow = i;
                        maxSpanCol = j;
                        maxSpan = span;
                    }
                }
            }
            if(maxSpan == 0){
                break;
            }
            numerOfWallsUsed += maxSpan;
            // install walls around the region with highest span (marking the whole region with 2) i.e.
            // fill 2 in grid for all the cell part of maxSpan
            fill2(maxSpanRow, maxSpanCol, visited);

            // updateVirusSpread in the grid from all other regions
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(grid[i][j] != 1) continue ;
                    expand(i, j, visited);
                }
            }
        }
        return numerOfWallsUsed;
    }

    int traverse(int R, int C, boolean[][] visited){
        if(!isValid(R, C) || visited[R][C] || grid[R][C] == 2) return 0;
        if(grid[R][C] == 0) return 1;
        visited[R][C] = true;
        
        int count = 0;
        for(int i=0;i<4;i++){
            int r = R+d[i][0];
            int c = C+d[i][1];
            count += traverse(r, c, visited);
        }
        return count;
    }

    void fill2(int R, int C, boolean[][] visited){
        if(!isValid(R, C) || !visited[R][C] || grid[R][C] == 0) return ;
        visited[R][C] = false;
        for(int i=0;i<4;i++){
            int r = R+d[i][0];
            int c = C+d[i][1];
            fill2(r, c, visited);
        }
        if(grid[R][C] == 1){
            grid[R][C] = 2;
        }
    }

    void expand(int R, int C, boolean[][] visited){
        if(!isValid(R, C)) return ;
        if(grid[R][C] == 0) {
            grid[R][C] = 1; return ;
        }
        if(!visited[R][C]) return ;
        visited[R][C] = false;
        for(int i=0;i<4;i++){
            int r = R+d[i][0];
            int c = C+d[i][1];
            expand(r, c, visited);
        }
    }

    boolean isValid(int r, int c){
        return !(r < 0 || r >= rows || c < 0 || c >= cols);
    }
}
