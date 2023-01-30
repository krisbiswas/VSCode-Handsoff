import java.util.*;

public class NQueenMaxSum{
    public static void main(String[] args) {
        int[][] board = new int[][]{
            {1 ,2 ,3 ,4 ,5 , 6, 7, 8},
            {9 ,10,11,12,13,14,15,16},
            {17,18,19,20,21,22,23,24},
            {25,26,27,28,29,30,31,32},
            {33,34,35,36,37,38,39,40},
            {41,42,43,44,45,46,47,48},
            {49,50,51,52,53,54,55,56},
            {57,58,59,60,61,62,63,64}
        };
        nQueen(board);
    }

    static int maxSum = 0;
    private static int[][] boardPlaced = new int[][]{
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0}
    };

    private static void nQueen(int[][] board){
        nQueen(board, 7, 7, 0);
    }

    private static boolean isValid(int i,int j){
        return (i>=0 && i<8) && (j>=0 && j<8);
    }

    private static void place(int r, int c, int val){
        for(int i=r; i<8; i++){
            boardPlaced[i][c] = val;
        }
        for(int i=c; i<8; i++){
            boardPlaced[r][i] = val;
        }
        for(int i=r,j=c;i<8 && j<8;i++,j++){
            boardPlaced[i][j] = val;
        }
        for(int i=r,j=c;i>=0 && j>=0;i--,j--){
            boardPlaced[i][j] = val;
        }
    }

    private static boolean isSafe(int r, int c){
        return boardPlaced[r][c] == 0;
    }

    private static void nQueen(int[][] board, int r, int c, int sum) {
        if(r<0){
            maxSum = Math.max(sum, maxSum);
            System.out.println(maxSum);
        }else{
            for(int j=7;j>=0;j--){
                if(isSafe(r,j)){
                    place(r,j,1);
                    nQueen(board, r-1, j, sum+board[r][j]);
                    place(r,j,0);
                }
            }
        }
    }
}