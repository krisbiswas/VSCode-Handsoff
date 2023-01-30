#include<iostream>
#include<vector>
using namespace std;

vector<vector<int>> board;
/* 
bool isValid(int i,int j){
    return (i>=0 and i<board.size()) and (j>=0 and j<board[0].size());
}
 */
bool is_safe(int r,int c){
    int i,j;
    // Check in the row c
    for (i = 0; i < r; i++)
        if (board[i][c])
            return false;

    /* Check upper diagonal on left side */
    for (i = r, j = c; i >= 0 && j >= 0; i--, j--)
        if (board[i][j])
            return false;

    /* Check lower diagonal on left side */
    for (i = r, j = c; i >= 0 && j < board.size(); i--, j++)
        if (board[i][j])
            return false;

    return true;
}

bool n_queen(int n){
    if (n == 0){
        return true;
    }
    int row = board.size()-n;
    for(int c=0;c<board.size();c++){
        if (is_safe(row,c)){
            board[row][c] = row+1;
            if(n_queen(n-1)){
                return true;
            }
            board[row][c] = 0;
        }
    }
    return false;
}

void printSolution()
{
    int N = board.size();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            printf(" %d ", board[i][j]);
        printf("\n");
    }
}

int main(){
    int n = 5;
    board.operator=(vector<vector<int>>(n,vector<int>(n)));
    n_queen(n);
    printSolution();
    return 0;
}

