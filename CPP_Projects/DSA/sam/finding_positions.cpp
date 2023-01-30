#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
using namespace std;

class Node {
    public:
        int val;
        unordered_set<Node*> greater;
        unordered_set<Node*> smaller;
        Node(int v){
            val = v;
        }
};

class Graph {
    public:
        vector<Node*> list;
        Node* find(int node){
            for(Node* v : list){
                if (v->val == node){
                    return v;
                }
            }
            return NULL;
        }
};

unordered_map<Node*, bool> visited;

void dfs_greater(Node* root, Node* greater){
    for (Node* gNode : greater->greater){
        if(visited[gNode] == false){
            visited[gNode] = true;
            root->greater.insert(gNode);
            dfs_greater(root, gNode);
        }
    }
}

void dfs_smaller(Node* root, Node* smaller){
    for (Node* sNode : smaller->smaller){
        if(visited[sNode] == false){
            visited[sNode] = true;
            root->smaller.insert(sNode);
            dfs_smaller(root, sNode);
        }
    }
}

int knows_position(vector<vector<int>> &comparisons){
    // creating graph from comparisons given
    Graph* g = new Graph();
    for(vector<int> comp: comparisons){
        Node* node = g->find(comp[0]);
        Node* node2 = g->find(comp[1]);
        if(node == NULL){
            node = new Node(comp[0]);
            g->list.push_back(node);
        }
        if (node2 == NULL){
            node2 = new Node(comp[1]);
            g->list.push_back(node2);
        }
        node->greater.insert(node2);
        node2->smaller.insert(node);
    }

    // update greaters and smallers of all nodes
    vector<int> ans;
    int count = 0;
    for(Node* n : g->list){
        for(Node* greater : n->greater){
            dfs_greater(n, greater);
        }
        // visited.clear();
        for(Node* smaller : n->smaller){
            dfs_smaller(n, smaller);
        }
        visited.clear();
/* 
        cout<<n->val<<" : ";
        for(Node* node : n->greater){
            cout<<node->val<<" ";
        }
        cout<<endl;
        cout<<n->val<<" : ";
        for(Node* node : n->smaller){
            cout<<node->val<<" ";
        }
        cout<<endl; */
        int size = n->greater.size();
        size += n->smaller.size();
        if( size == g->list.size()-1){
            count++;
        }
    }
    return count;
}

int main(){
    int n = 6;
    // cin>>n;
    // while(no of comparisons given){
        // 1<5
        // 5<4
        // 5<2
        // 4<6
        // 3<4
        // 4<2
    // }
    vector<vector<int>> comparisons;
    comparisons.push_back(vector<int>({1,5}));
    comparisons.push_back(vector<int>({5,4}));
    comparisons.push_back(vector<int>({5,2}));
    comparisons.push_back(vector<int>({4,6}));
    comparisons.push_back(vector<int>({3,4}));
    comparisons.push_back(vector<int>({4,2}));
    
    cout<<knows_position(comparisons);

    return 0;
}