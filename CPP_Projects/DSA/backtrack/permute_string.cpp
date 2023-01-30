#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

void permutation(string& s, int idx, set<string>& ans){
    if(idx >= s.size()){
        ans.emplace(s);
    }else{
        for (int i=idx;i<s.size();i++){
            swap(s[idx], s[i]);
            if(ans.count(s) == 0){
                permutation(s, idx+1, ans);
            }
            swap(s[idx], s[i]);
        }
    }
}

vector<string> permutation(string &s){
    set<string> ans;
    for(int i=0;i<s.size();i++){
        permutation(s, i, ans);
    }
    vector<string> v = vector<string>(ans.begin(), ans.end());
    sort(v.begin(), v.end());
    return v;
}

int main(){
    string s = "ABC";
    vector<string> permutes = permutation(s);
    for (string p : permutes){
        cout<<p<<endl;
    }
    return 0;
}