package Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BinaryTree
 */
public class BinaryTree {
    class Node {
        int val;
        Node left, right;

        Node(int value){
            val = value;
        }

        @Override
        public String toString() {
            return left+"<-"+val+"->"+right;
        }
    }
    
    Node root;

    void insert(int value){
        Node nNode = new Node(value);
        if(root == null){
            root = nNode;
            return ;
        }
        insert(root, nNode);
    }

    protected void insert(Node root, Node nNode){
        if(root == null){
            return ;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            if(n.left == null && n.right == null){
                n.left = nNode;
                q.clear();
            } else if (n.left != null && n.right != null){
                q.add(n.left);
                q.add(n.right);
            } else if(n.left != null && n.right == null){
                n.right = nNode;
                q.clear();
            } else {
                n.left = nNode;
                q.clear();
            }
        }
    }

    private void inOrder(Node root, StringBuilder sb){
        if(root == null){
            return ;
        }
        inOrder(root.left, sb);
        sb.append(root.val+", ");
        inOrder(root.right, sb);
    }

    void remove(int value){
        if(root.val == value){
            insert(root.right, root.left);
            root = root.right;
            return ;
        }
        Node pNode = getParent(root, value);
        if(pNode == null){
            // value not present in the tree
            return ;
        }
        if(pNode.left.val == value){
            if(pNode.left.right == null){
                pNode.left = pNode.left.left;
            } else {
                insert(pNode.left.right, pNode.left.left);
                pNode.left = pNode.left.right;
            }
        } else {
            if(pNode.right.right == null){
                pNode.right = pNode.right.left;
            } else {
                insert(pNode.right.right, pNode.right.left);
                pNode.right = pNode.right.right;   
            }
        }
    }

    protected Node getParent(Node node, int value) {
        if(node == null){
            return null;
        }
        Node parent = null;
        if(node.left != null){
            if(node.left.val == value){
                return node;
            } else {
                parent = getParent(node.left, value);
            }
        }
        if(parent == null && node.right != null){
            if(node.right.val == value){
                return node;
            } else {
                parent = getParent(node.right, value);
            }
        }
        return parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }
}