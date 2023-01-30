#include<iostream>
#include<stack>
#include<unordered_map>
using namespace std;

unordered_map<char, char> bracket_map(
        {{'[',']'}, {'{','}'}, {'(',')'}}
    );

void check_brackets(string& s){
    stack<char> open_brackets;
    for (char br : s){
        if (br == '(' || br == '{' || br == '['){
            open_brackets.push(br);
        }else{
            if (!open_brackets.empty()){
                char last_brac = open_brackets.top();
                open_brackets.pop();
                if (bracket_map[last_brac] != br){
                    cout<<"F"<<endl;
                }
            }else{
                cout<<"False"<<endl;
                return ;
            }
        }
    }
    if(open_brackets.empty()){
        cout<<"TRue"<<endl;
    }else{
        cout<<"FAlse"<<endl;
    }
    
}

int main(void){
    string bracket_str;
    cout<<"Input Brackets : ";
    cin>>bracket_str;
    check_brackets(bracket_str);
    return 0;
}