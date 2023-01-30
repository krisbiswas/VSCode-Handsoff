class Solution:

    dr = [0,-1,0,1]
    dc = [-1,0,1,0]
    startIndices = {}

    def isValidIndex(self, i:int,j:int,x:int,y:int):
        return (i>=0 and i<x) and (j>=0 and j<y)

    def findWord(self, board: list[list[str]], word: str, nextCharIndex:int, currentCharIndex:list[int], subWord: str, result):
        subWord = subWord.__add__(board[currentCharIndex[0]][currentCharIndex[1]])
        print(subWord+"="+word)
        self.visited[currentCharIndex[0]][currentCharIndex[1]] = True

        if subWord == word:
            result.add(word)
            return
        
        if board[index[0]][index[1]] == word[wordPtr]:
            for x in range(4):
                if self.isValidIndex(index[0]+self.dr[x], index[1]+self.dc[x], len(board), len(board[0])):
                    # print(f"{index[0]+self.dr[x]}, {index[1]+self.dc[x]}= {not self.visited[index[0]+self.dr[x]][index[1]+self.dc[x]]}")
                    # if not self.visited[index[0]+self.dr[x]][index[1]+self.dc[x]]:
                        self.findWord(board, word, wordPtr+1, [index[0]+self.dr[x], index[1]+self.dc[x]], subWord, result)

        self.visited[index[0]][index[1]] = False

    def findWords(self, board: list[list[str]], words: list[str]) -> list[str]:
        for i in range(len(board)):
            for j in range(len(board[i])):
                if board[i][j] in self.startIndices:
                    self.startIndices[board[i][j]].append([i,j])
                else:
                    self.startIndices[board[i][j]] = [[i,j]]
        
        self.visited = [[False]*len(board[i])]*len(board)

        result = set()
        for word in words:
            if word[0] in self.startIndices:
                for index in self.startIndices[word[0]]:
                    # print(index)
                    self.visited[index[0]][index[1]] = True
                    self.findWord(board, word, 0, index, "", result)
                    self.visited[index[0]][index[1]] = False
            
            # for i in range(len(self.visited)):
            #     print(self.visited[i])
        
        return list(result)

if __name__ == "__main__":
    # board = [["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a"]]

    # words = ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
    words = ["oath","pea","eat","rain"]
    print(Solution().findWords(board,words))
    