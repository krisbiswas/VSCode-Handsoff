package Count_Sub_Islands;

public class Main {
    public static void main(String[] args) {
        int[][] grid1 = new int[][] {
            {1,1,1,1,0,0},
            {1,1,0,1,0,0},
            {1,0,0,1,1,1},
            {1,1,1,0,0,1},
            {1,1,1,1,1,0},
            {1,0,1,0,1,0},
            {0,1,1,1,0,1},
            {1,0,0,0,1,1},
            {1,0,0,0,1,0},
            {1,1,1,1,1,0}
        };
        int[][] grid2 = new int[][] {
            {1,1,1,1,0,1},
            {0,0,1,0,1,0},
            {1,1,1,1,1,1},
            {0,1,1,1,1,1},
            {1,1,1,0,1,0},
            {0,1,1,1,1,1},
            {1,1,0,1,1,1},
            {1,0,0,1,0,1},
            {1,1,1,1,1,1},
            {1,0,0,1,0,0}
        };
        Solution sol = new Solution();
        int numOfsubIslands = sol.countSubIslands(grid1, grid2);
        System.out.println("number of sub-island: "+numOfsubIslands);
    }
}

class Solution {
    int[][] grid1;
    int[][] grid2;
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, 1, 0, -1};
    boolean[][] visited;
    boolean isMatched = false;
    
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        visited = new boolean[grid1.length][grid1[0].length];
        // BFS/DFS grid2 tracking grid1 alongside
        // checking that the cell in both grids are 1 (land)
        int islandCount=0;
        for(int i=0;i<grid2.length;i++){
            for(int j=0;j<grid2[0].length;j++){
                if(grid2[i][j] == 0 || visited[i][j]) continue;
                isMatched = true;
                dfs(i, j);
                if(isMatched) islandCount++;
            }
        }
        return islandCount;
    }

    private void dfs(int r, int c){
        if(!isValid(r,c) || visited[r][c] || grid2[r][c] == 0){
            return ;
        }
        if(1 != grid1[r][c]){
            isMatched = false;
        }
        visited[r][c] = true;
        for(int i=0;i<4;i++){
            int R = r+dr[i];
            int C = c+dc[i];
            dfs(R,C);
        }
    }

    private boolean isValid(int r, int c){
        return !(r < 0 || r >= grid2.length || c < 0 || c >= grid2[0].length);
    }
}

