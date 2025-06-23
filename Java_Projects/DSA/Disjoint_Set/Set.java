package Disjoint_Set;

import java.util.HashMap;

public class Set {
    class Node{
        int id, parent;
        int size = 1;
        Node(int id){
            this.id = id;
            parent = id;
        }
        @Override
        public String toString() {
            return String.format("%d->%d", id, parent);
        }
    }
    HashMap<Integer, Node> nodes = new HashMap<>();

    Set(int[] values){
        for(int val : values){
            nodes.put(val, new Node(val));
        }
        System.out.println(nodes);
    }

    int union(int node1, int node2){
        Node n1 = find(node1);
        Node n2 = find(node2);
        if(n1.parent == n2.parent){
            System.out.println("Both nodes are part of same tree");
            return 0;
        }
        if(n1.size > n2.size){
            n1.parent = n2.parent;
            n2.size += n1.size;
            return n1.size;
        } else {
            n2.parent = n1.parent;
            n1.size += n2.size;
            return n2.size;
        }
    }

    // return the value of parent of "node"
    Node find(int node){
        if(!nodes.containsKey(node)){
            return null;
        }
        Node n = nodes.get(node);
        if(n.parent != n.id){
            Node parentNode = find(n.parent);
            n.parent = parentNode.id;
            return parentNode;
        }else {
            return n;
        }
    }
}
