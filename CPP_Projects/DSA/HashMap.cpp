#include<iostream>
#include<vector>
using namespace std;

class HashMap{
    private:
        vector<vector<string>> values;
        int capacity = 5;

        int hash(int key){
            return key%capacity;
        }
    public:
        HashMap(){
            values.resize(capacity);
        }
        HashMap(int capacity){
            this->capacity = capacity;
            values.resize(capacity);
        }
        void put(int key, string val){
            int index = hash(key);
            values[index].push_back(val);
        }
        vector<string> get(int key){
            int index = hash(key);
            return values[index];
        }

        void print(){
            for (int i=0;i<values.size();i++){
                cout<<i<<" : "<<"[";
                for(auto j=0;j<values[i].size();j++){
                    cout<<values[i][j]<<", ";
                }
                cout<<"]"<<endl;
            }
        }       
};

int main(){
    HashMap map;
    int inp;
    vector<string> inputs = {"3-jfksd","6-poiu","16-pki","end"};
    for (string buffer : inputs)
    {
        if (stoi(to_string(buffer[0]-48)) > 10){
            break;
        }
        int space_index = buffer.find("-", 0);
        inp = stoi(buffer.substr(0,space_index));
        map.put(inp,buffer.substr(space_index+1));
    }
    
    map.print();
    return 0;
}