package Number_of_island;

class Main{
    public static void main(String[] args) {
        char[][] grid = new char[][]{
            new char[]{'L', 'L', 'W', 'W', 'W'},
            new char[]{'W', 'L', 'W', 'W', 'L'},
            new char[]{'L', 'W', 'W', 'L', 'L'},
            new char[]{'W', 'W', 'W', 'W', 'W'},
            new char[]{'L', 'W', 'L', 'L', 'W'}, 
        };

        Main question = new Main();
        int numberOfIsland = question.countIslands(grid);
        System.out.println("Number of Island found : "+numberOfIsland);
    }

    int[] dr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dc = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    boolean[][] visited;
    int count = 0;

    public int countIslands(char[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        // default visited is filled with false
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == 'W' || visited[i][j]){
                    continue;
                }
                count(grid, i, j);
                count++;
            }
        }

        return count;
    }

    private void count(char[][] grid, int r, int c) {
        visited[r][c] = true;
        for(int i=0;i<dr.length;i++){
            int R = r+dr[i];
            int C = c+dc[i];
            if(!isValidPosition(R, C) || grid[R][C] == 'W' || visited[R][C]){
                continue;
            }
            count(grid, R, C);
        }
    }

    private boolean isValidPosition(int r, int c){
        return !(r < 0 || r >= visited.length || c < 0 || c >= visited[0].length);
    }
}