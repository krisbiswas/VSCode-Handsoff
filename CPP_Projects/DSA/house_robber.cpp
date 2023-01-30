#include<iostream>
#include<vector>
#include<unordered_map>
using namespace std;

unordered_map<int, int> memo;
int max_rob_money(vector<int> nums, int start_house){
    if (start_house >= nums.size()){
        cout<<"Never Runs"<<endl;
        return 0;
    }
    if (start_house == nums.size()-1){
        memo[start_house] = nums[start_house];
        return memo[start_house];
    }
    if (memo.find(start_house) == memo.end()){
        int money = 0;
        for (int i=2;start_house+i < nums.size();i++){
            money = max(max_rob_money(nums, start_house+i), money);
        }
        memo[start_house] = money+nums[start_house];
    }
    return memo[start_house];
}
int main(void){
    int input;
    vector<int> nums = {1,2,3,1};
    // cout<<"Enter nums: ";
    // while(cin>>input){
    //     nums.push_back(input);
    // }

    for (auto i=0;i<nums.size();i++){
        cout<<"Max Money Robbed from "<<i<<" index: "<<max_rob_money(nums, i)<<endl;
    }
    return 0;
}