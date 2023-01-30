#include <iostream>
#include <vector>
using namespace std;

vector<int> col;

void placeStudents(vector<vector<int> > &mat, int row, int count, int& ans){
    ans = max(ans, count);
    if(row >= mat.size()){
        // cout<<count<<endl;
        ans = max(ans, count);
        return ;
    }else{
        // for (int v : col){
        //     cout<<v<<" ";
        // }
        // cout<<endl;
        for(int i=0;i<mat[row].size();i++){
            if (mat[row][i] == 1 and col[i] != 1){
                col[i] = 1;
                placeStudents(mat, row+1, count+1, ans);
                col[i] = 0;
            }
        }
    }
}
int maxMatch(vector<vector<int> > &mat) 
{
    col.assign(mat[0].size(), 0);
    int ans = 0;
    placeStudents(mat, 0, 0, ans);
    return ans;
}

int main(){
    vector<vector<int>> mat{
            {1, 0}, 
            {0, 1}, 
            {0, 0}
        };
    // for(int i = 0;i<mat.size();i++){
    //     for(int j=0;j<mat[0].size();j++){
    //         cout<<mat[i][j]<< " ";
    //     }
    //     cout<<endl;
    // }
    cout<<maxMatch(mat)<<endl;
    return 0;
}