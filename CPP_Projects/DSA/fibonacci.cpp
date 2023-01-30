#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

unordered_map<int,int> map;
long long call;

void printSeries(unordered_map<int,int>::iterator it){
    while(it != map.end()){
        cout<<it->first<<" = "<<it->second<<endl;
        it++;
    }
}

int fib_memoized(int n){
    call++;
    if(map.count(n) == 0){
        if (n <= 1){
            map.emplace(n,n);
        }else{
            int thisVal = fib_memoized(n-1)+fib_memoized(n-2);
            map.emplace(n,thisVal);
        }
    }
    return map[n];
}

int fib_recursive(int n){
    call++;
    if (n <= 1){
        if(map.count(n) == 0){
            map.emplace(n,n);
        }
        return n;
    }
    int thisVal = fib_recursive(n-1)+fib_recursive(n-2);
    if(map.count(n) == 0){
        map.emplace(n,thisVal);
    }
    return thisVal;
}

int main(){
    int n = 3;
    while(cin>>n && n>=0){
        call = 0;
        cout<<"Recursive "<<fib_recursive(n)<<endl;
        // printSeries(map.begin());
        cout<<"Function Call = "<<call<<endl;
        map.clear();
        call = 0;
        cout<<"Memo "<<fib_memoized(n)<<endl;
        // printSeries(map.begin());
        cout<<"Function Call = "<<call<<endl;
    }
    return 0;
    
}