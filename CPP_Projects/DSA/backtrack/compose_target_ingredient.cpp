#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
//  _________________________________________
// |  TLE will occur, so try DP/Memoization  |
//  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
void print_ingreds(vector<vector<int>> ingreds){
    for(vector<int> v : ingreds){
        for(int c : v){
            cout<<c<<" ";
        }
        cout<<endl;
    }
}

void print(vector<int> V){
    for(int c : V){
        cout<<c<<" ";
    }
    cout<<endl;
}

vector<int> ans;

void mix_ingredient(bool add, vector<int>& ingredient, vector<int> &newMix){
    if(add){
        newMix[0] += ingredient[0];
        newMix[1] += ingredient[1];
        newMix[2] += ingredient[2];
    }else{
        newMix[0] -= ingredient[0];
        newMix[1] -= ingredient[1];
        newMix[2] -= ingredient[2];
    }
}

void match_target(vector<vector<int>> &ingreds, vector<int> &target, int idx, vector<int>& mix, 
vector<int> ing_indices){
    // cout<<idx<<" :- ";
    // print(ing_indices);
    if(idx >= ingreds.size()){
        if(mix == target){
            if(ans.empty() or ans.size() > ing_indices.size()){
                // cout<<"Found "<<endl;
                ans = ing_indices;
            }
        }
    }
    else{
        // --> taking idx ingredient
        mix_ingredient(true, ingreds[idx], mix);
        // cout<<"Add "<<idx<<"{}";
        // TO-DO make it 1s based index for ans
        ing_indices.push_back(idx);
        for(int i=idx;i<ingreds.size();i++){
            // cout<<"call: "<<idx<<" - ";
            match_target(ingreds, target, i+1, mix, ing_indices);
        }
        mix_ingredient(false, ingreds[idx], mix);
        ing_indices.pop_back();

        // --> skipping idx ingredient
        // cout<<"Skip "<<idx<<"{}";
        for(int i=idx;i<ingreds.size();i++){
            // cout<<"call: "<<idx<<" - ";
            match_target(ingreds, target, i+1, mix, ing_indices);
        }
    }
}

// return vector containing indices of ingredients to be added to match the target
vector<int> match_target_composition(vector<vector<int>> &ingreds, int k, vector<int> &target){
    ans.clear();
    vector<int> mix = {0,0,0};
    match_target(ingreds, target, 0, mix, vector<int>());
    return ans;
}

int main(){
    ifstream inputs("C:\\Users\\krisb\\VS_Workspace\\CPP_Projects\\DSA\\backtrack\\compose_target_inputs.txt");
    int t;int k;int buff;
    cout<<"Enter No. of test cases: ";
    inputs>>t;
    cout<<t<<endl;
    while(t-- > 0){
#pragma region 
        cout<<"Enter no. of ingredients: ";
        inputs>>k;
        cout<<k<<endl;
        vector<vector<int>> ingred_composition;
        for(int i=1;i<=k;i++){
            vector<int> composition;
            int compose_count = 0;
            // cout<<"Enter the composition of "<<i<<"th ingredient: ";
            while(compose_count < 3){
                inputs>>buff;
                composition.push_back(buff);
                compose_count++;
            }
            ingred_composition.push_back(composition);
        }
        vector<vector<int>> targets;
        int no_of_queries;
        cout<<"Enter no. of queries: ";
        inputs>>no_of_queries;
        cout<<no_of_queries<<endl;
        for(int i=1;i<=no_of_queries;i++){
            int compose_count = 0;
            vector<int> target;
            // cout<<"Enter target "<<i<<": ";
            while(compose_count < 3){
                inputs>>buff;
                target.push_back(buff);
                compose_count++;
            }
            targets.push_back(target);
        }
        print_ingreds(ingred_composition);
        // print_ingreds(targets);
#pragma endregion
        
        cout<<"Solution: "<<endl;
        for(vector<int> target : targets){
            print(target);
            vector<int> ans = match_target_composition(ingred_composition, k, target);
            if(ans.empty()){
                cout<<-1;
            }else{
                for(int ingred : ans){
                    cout<<ingred+1<<" ";
                }
            }
            cout<<endl;
        }
    }
    inputs.close();
}