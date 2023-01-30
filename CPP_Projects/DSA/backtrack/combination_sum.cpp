////////f f sdf/ sdf
//  some issue on solving on gfg

#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

void comb_permute(vector<int>& s, int idx, int sum, vector<int>& combination, set<vector<int>>& ans){
    if (sum == 0){
        ans.insert(combination);
    }else if(sum > 0){
        for (int i=idx; i < s.size(); i++){
            combination.push_back(s[i]);
            if(sum-s[i] >= 0){
                comb_permute(s, idx, sum-s[i], combination, ans);
            }
            combination.pop_back();
        }
    }
}

vector<vector<int>> combination_sum(vector<int> &a, int sum){
    set<vector<int>> ans;
    vector<int> comb;
    // for(int i=0;i<a.size();i++){
        comb_permute(a, 0, sum, comb, ans);
    // }
    return vector<vector<int>>(ans.begin(), ans.end());
}

int main(){
    vector<int> v = {7,2,6,5};
    int sum = 16;
    vector<vector<int>> permutes = combination_sum(v, sum);
    // cout<<permutes.size()<<endl;
    for (auto nums : permutes){
        int count = 1;
        for(auto n : nums){
            cout<<n<<" ";
        }
        cout<<endl;
    }
    return 0;
}