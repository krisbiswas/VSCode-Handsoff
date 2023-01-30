#include<iostream>
#include<vector>
using namespace std;

// As Min Heap not working
class Heap{
    private:
        vector<int> hp;
        bool isMaxHeap;

        bool is_valid_index(int index){
            return (index >= 0 && index < hp.size());
        }
        void heapify(int index){
            int parent_index = (index-1)/2;
            while(is_valid_index(parent_index)){
                // For Max Heap
                if (isMaxHeap){
                    if (hp[parent_index] < hp[index]){
                        swap(hp[parent_index], hp[index]);
                        index = parent_index;
                    }else{
                        break;
                    }
                }
                // For Min Heap
                else{
                    if (hp[parent_index] > hp[index]){
                        swap(hp[parent_index], hp[index]);
                        index = parent_index;
                    }else{
                        break;
                    }
                }
                parent_index = (index-1/2);
            }
        }
        
    public:
        Heap(bool heapType=true){
            isMaxHeap = heapType;
        }

        void print(){
            for (int i : hp){
                cout<<i<<"-";
            }
            cout<<endl;
        }

        void insert(int val){
            hp.push_back(val);
            // cout<<"Before: "<<val<<"->";
            // print();
            heapify(hp.size()-1);
            // cout<<"After "<<val<<"->";
            // print();
        }

        void remove(){
            int index = 0;
            while(is_valid_index(index)){
                int lc = 2*index+1;
                int rc = 2*index+2;
                if (is_valid_index(lc) || is_valid_index(rc)){
                    if(is_valid_index(lc) && is_valid_index(rc)){// both are valid childs
                        // For Max Heap
                        if (isMaxHeap){
                            if (hp[lc] > hp[rc]){
                                swap(hp[index], hp[lc]);
                                index = lc;
                            }else{
                                swap(hp[index], hp[rc]);
                                index = rc;
                            }
                        }
                        // For Min Heap
                        else{
                            if (hp[lc] < hp[rc]){
                                swap(hp[index], hp[lc]);
                                index = lc;
                            }else{
                                swap(hp[index], hp[rc]);
                                index = rc;
                            }
                        }
                    }else{// either of lc | rc is false
                        if (is_valid_index(lc)){
                            swap(hp[index], hp[lc]);
                            index = lc;
                        }else{
                            swap(hp[index], hp[rc]);
                            index = rc;
                        }
                    }
                }else{// both childs doesn't exist
                    hp.erase(hp.begin()+index);
                    // cout<< "After Erase: ";
                    // print();
                    break;
                }
                // print();
            }
        }

        int getTop(){
            return hp[0];
        }

        int size(){
            return hp.size();
        }
        
        bool empty(){
            return hp.empty();
        }
};


int main(){
    int inp;
    // false specifies min Heap
    Heap hp(false);
    vector<int> v = {10, 8, 9, 5, 3, 6, 2};
    for (int inp: v){
        hp.insert(inp);
    }
    // hp.print();
    while(!hp.empty()){
        cout<<hp.getTop()<<", ";
        hp.remove();
    }

    return 0;
}