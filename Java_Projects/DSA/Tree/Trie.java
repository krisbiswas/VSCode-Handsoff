package Tree;

import java.util.Arrays;

public class Trie {
    class Node {
        public Node(char c) {
            value = c;
        }
        char value;
        Node[] subNodes = new Node[26];
        boolean isEnd = true;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    Node root = new Node('$');
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addString("flow" );
        trie.addString("flower");
        print(trie.root);
    }

    private static void print(Node n) {
        if(n == null){
            return;
        }
        System.out.println(n+"->"+Arrays.toString(n.subNodes));
        for(Node n2 : n.subNodes){
            print(n2); 
        }
    }

    void addString(String str){
        Node n = root;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(n.subNodes[c-'a'] == null){
                n.subNodes[c-'a'] = new Node(c);
            } else {
                n.isEnd = false;
            }
            n = n.subNodes[c-'a'];
        }
        n.isEnd = true;
    }

    void check(String s){
        Node n = root;
        for(Node n2 : n.subNodes){
            print(n2);
        }
    }
}
