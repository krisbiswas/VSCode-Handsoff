#include<iostream>
using namespace std;

int main(){
    int size = 5;
    int c = size/2 + 1;
    int x = 0;
    for(int i=1;i<=size;i++){
        for(int j=0;j<=size;j++){
            if(j == c-x || j == c+x){
                cout<<"*";
            }else{
                cout<<" ";
            }
        }
        x+=1;
        cout<<endl;
    }
    x--;
    for(int i=1;i<=size;i++){
        for(int j=0;j<=size;j++){
            if(j == c-x || j == c+x){
                cout<<"*";
            }else{
                cout<<" ";
            }
        }
        x--;
        cout<<endl;
    }
}