#include<iostream>
#include<vector>
#include<unordered_map>
using namespace std;

unordered_map<int, int> memo;
int funcCall = 0;

int lengthOfLISRecurr(vector<int> nums, int idx) {
    funcCall++;
    if(memo.find(idx) != memo.end()){
        return memo[idx];
    }
    int longestFromIdx = 1;
    for(int i=idx+1;i<nums.size();i++){
        if(nums[i] > nums[idx]){
            int len = lengthOfLISRecurr(nums, i)+1;
            longestFromIdx = max(len, longestFromIdx);
        }
    }
    memo[idx] = longestFromIdx;
    return memo[idx];
}

int lengthOfLISRecurr(vector<int> &nums){
    memo[nums.size()-1] = 1;
    int longestIncSubSeq = 1;
    for(int i = 0;i<nums.size();i++){
        funcCall++;
        int l = lengthOfLISRecurr(nums, i);
        longestIncSubSeq = max(l, longestIncSubSeq);
    }
    return longestIncSubSeq;
}

int lengthOfLISIter(vector<int> &nums){
    int i = 1;
    vector<int> len_of_inc_subseq(nums.size(), 1);
    while(i<nums.size()){
        int j = 0;
        while(j<i){
            if(nums[i] > nums[j] and len_of_inc_subseq[i] <= len_of_inc_subseq[j]){
                len_of_inc_subseq[i] = len_of_inc_subseq[j]+1;
            }
            j++;
        }
        i++;
    }
    int len_of_longest_inc_subseq = 0;
    for(int val : len_of_inc_subseq){
        // cout<<val<<" ";
        len_of_longest_inc_subseq = max(len_of_longest_inc_subseq, val);
    }
    cout<<endl;
    return len_of_longest_inc_subseq;
}

int main(){
    vector<int> seq;// = {0,1,0,3,2,3};
    for(int i=1;i<=1000;i++){
        seq.push_back(i);
    }
    // cout<<lengthOfLISIter(seq)<<endl;
    cout<<lengthOfLISRecurr(seq)<<endl;
    return 0;
}