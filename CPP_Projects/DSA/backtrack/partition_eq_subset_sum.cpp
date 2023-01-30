// https://practice.geeksforgeeks.org/problems/subset-sum-problem2014/1

#include <bits/stdc++.h>
using namespace std;

class Solution{
    int req_sum=0;
    int found = 0;
    
    void test(int n, int arr[], int idx, int subSum){
        if (req_sum == subSum){
            found = 1;
        }else if(req_sum-subSum > 0){
            for(int i=idx+1;i<n;i++){
                if(found){
                    break;
                }
                test(n, arr, i, subSum+arr[i]);
            }
        }
    }
public:
    int equalPartition(int N, int arr[])
    {
        int total_sum = 0;
        for (int i=0;i<N;i++){
            total_sum+=arr[i];
        }
        if (total_sum & 1 == 1){
            return 0;
        }
        req_sum = total_sum/2;
        sort(arr, arr+N);
        for (int i=0;i<N;i++){
            if(found){
                break;
            }
            test(N, arr, 0, arr[i]);
        }
        return found;
    }
};

int main(){
    int t;
    // cin>>t;
    // while(t--){
        int N = 3;
        // cin>>N;
        int arr[N] = {1, 3, 5};
        // for(int i = 0;i < N;i++)
            // cin>>arr[i];
        
        Solution ob;
        if(ob.equalPartition(N, arr))
            cout<<"YES\n";
        else
            cout<<"NO\n";
    // }
    return 0;
}