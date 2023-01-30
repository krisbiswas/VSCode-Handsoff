#include<iostream>
#include <vector>
using namespace std;

int main(void){
    int n = 10;
    // cout<<"Enter n: ";
    // cin>>n;
    vector<vector<int>> pascal_triangle;
    pascal_triangle.emplace_back(vector<int>{1});
    pascal_triangle.emplace_back(vector<int>{1,1});
    for (int row=3;row<=n;row++){
        vector<int> prev_row = pascal_triangle[row-2];
        int prev_num = 0;
        vector<int> new_row;
        for(int val : prev_row){
            new_row.emplace_back(prev_num+val);
            prev_num = val;
        }
        new_row.emplace_back(1);
        pascal_triangle.emplace_back(new_row);
    }
    for (auto v : pascal_triangle){
        for (int i : v){
            cout<<i<<" ";
        }
        cout<<endl;
    }
}