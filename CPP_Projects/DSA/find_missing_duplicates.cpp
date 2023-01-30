#include<iostream>
#include<vector>
using namespace std;

pair<int,int> find_missing_dup(vector<int>& a){
    int dup = -1;
    int i = 0;
    int XOR = 0;
    for (;i<a.size();i++){
        if (a[abs(a[i])-1] < 0){
            dup = abs(a[i]);
            break;
        }
        XOR ^= i+1;
        XOR ^= abs(a[i]);
        a[abs(a[i])-1] = -a[abs(a[i])-1];
    }
    // find missig
    while (i < a.size()){
        XOR ^= i+1;
        XOR ^= abs(a[i]);
        i++;
    }
    int mis = XOR ^ dup;
    return pair<int,int>(dup, mis);
}

int main(){
    vector<int> a = {5,6,4,1,4,2};
    auto p = find_missing_dup(a);
    cout<<p.first<<" <=> "<<p.second<<endl;
    return 0;
}