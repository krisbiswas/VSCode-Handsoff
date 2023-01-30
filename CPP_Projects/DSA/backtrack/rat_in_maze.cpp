// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
// BFS+Backtrack

#include <iostream>
#include <vector>
using namespace std;

/*
vector<pair<int,int>> minDistancePath;
vector<vector<int>> maze_blue_print;
pair<int,int> maze_size;

void printPath(vector<pair<int,int>> path){
    for(auto pt : path){
        cout<<"("<<pt.first<<","<<pt.second<<")->";
    }
    cout<<endl;
}

void printMaze(vector<vector<int>> & maze){
    for(auto it : maze){
        for(auto it2: it){
            cout<<it2<<" ";
        }
        cout<<endl;
    }
}

bool isValidPoint(pair<int,int> pt, pair<int,int> maxPt){
    return (pt.first >= 0 and pt.first < maxPt.first) && (pt.second >= 0 and pt.second < maxPt.second);
}

void findPath(vector<vector<int>>& maze, pair<int,int> &source, pair<int,int> &dest, 
pair<int,int> point, vector<pair<int,int>> &foundPath){
    if(point == dest){
        foundPath.push_back(point);
        maze_blue_print[point.first][point.second] = 1;
        if(minDistancePath.empty() or minDistancePath.size() > foundPath.size()){
            // try copy this found path
            minDistancePath = foundPath;
            printMaze(maze_blue_print);
        }
    }

    if (maze_blue_print[point.first][point.second] > 0){
        return ;
    }

    foundPath.push_back(point);
    maze_blue_print[point.first][point.second] = 1;
    // possible moves only right or down
    // try moving right
    pair<int,int> nextPoint = pair<int,int>(point.first+1,point.second);
    if (isValidPoint(nextPoint, maze_size) and maze[nextPoint.first][nextPoint.second] == 1){
        findPath(maze,source,dest,nextPoint,foundPath);
    }
    // try moving down
    nextPoint.first-=1;
    nextPoint.second+=1;
    if (isValidPoint(nextPoint, maze_size) and maze[nextPoint.first][nextPoint.second] == 1){
        findPath(maze, source, dest, nextPoint, foundPath);
    }
    foundPath.pop_back();
    maze_blue_print[point.first][point.second] = 0;
}

vector<pair<int,int>> findPath(vector<vector<int>>& maze, pair<int,int> source, pair<int,int> dest){
    vector<pair<int,int>> v;
    maze_size = pair<int,int>(maze.size(),maze[0].size());
    maze_blue_print.operator=(vector<vector<int>>(maze_size.first, vector<int>(maze_size.second, 0)));
    findPath(maze, source, dest, source, v);
    return minDistancePath;
} */

vector<vector<int>> maze_blue_print;
pair<int,int> maze_size;
vector<string> ans;

bool isValidPoint(int i, int j, pair<int,int> maxPt){
    return (i >= 0 and i <= maxPt.first) && (j >= 0 and j <= maxPt.second);
}

void findPath(vector<vector<int>>& maze, int pti, int ptj, string &foundPath){
    if(pair<int,int>(pti,ptj) == maze_size){
        ans.push_back(string(foundPath));return;
    }
    // cout<<foundPath<<endl;
    if (isValidPoint(pti-1,ptj, maze_size) and maze[pti-1][ptj] == 1 and maze_blue_print[pti-1][ptj] == 0){
        foundPath.push_back('U');
        maze_blue_print[pti-1][ptj] = 1;
        findPath(maze, pti-1,ptj, foundPath);
        foundPath.pop_back();
        maze_blue_print[pti-1][ptj] = 0;
    }
    if (isValidPoint(pti,ptj-1, maze_size) and maze[pti][ptj-1] == 1 and maze_blue_print[pti][ptj-1] == 0){
        foundPath.push_back('L');
        maze_blue_print[pti][ptj-1] = 1;
        findPath(maze, pti,ptj-1, foundPath);
        foundPath.pop_back();
        maze_blue_print[pti][ptj-1] = 0;
    }
    if (isValidPoint(pti+1,ptj, maze_size) and maze[pti+1][ptj] == 1 and maze_blue_print[pti+1][ptj] == 0){
        foundPath.push_back('D');
        maze_blue_print[pti+1][ptj] = 1;
        findPath(maze, pti+1,ptj, foundPath);
        foundPath.pop_back();
        maze_blue_print[pti+1][ptj] = 0;
    }
    if (isValidPoint(pti,ptj+1, maze_size) and maze[pti][ptj+1] == 1 and maze_blue_print[pti][ptj+1] == 0){
        foundPath.push_back('R');
        maze_blue_print[pti][ptj+1]  = 1;
        findPath(maze, pti,ptj+1, foundPath);
        foundPath.pop_back();
        maze_blue_print[pti][ptj+1]  = 0;
    }
}

vector<string> findPath(vector<vector<int>> &maze, int n) {
    ans.clear();
    maze_blue_print.operator=(vector<vector<int>>(n,vector<int>(n,0)));
    maze_blue_print[0][0] = 1;
    maze_size = pair<int,int>(n-1,n-1);
    string path;
    findPath(maze, 0, 0, path);
    return ans;
}

void printMaze(vector<string> & maze){
    for(auto it : maze){
        cout<<it<<endl;
    }
}

int main(){
    vector<vector<int>> maze = {{0, 1, 1, 1},
{1, 1, 1, 0},
{1, 0, 1, 1},
{0, 0, 1, 1}};
    
    vector<string> v = findPath(maze, 4);
    printMaze(v);
    return 0;
}