package Tree;

public class BST {
    class Node {
        int val;
        Node left, right;
        int height = 0;

        Node(int value){
            this.val = value;
        }

        @Override
        public String toString() {
            return left+"<-"+val+"->"+right;
        }
    }

    Node root;

    void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int element, Node node) {
        //check whether the node is null or not
        if (node == null)
            node = new Node(element);
        //insert a node in case when the given element is lesser than the element of the root node  
        else if (element < node.val) {
            node.left = insert(element, node.left);
            if(getHeight(node.left) - getHeight(node.right) == 2) {
                if(element < node.left.val) { 
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            }
        } else if( element > node.val) {
            node.right = insert(element, node.right);
            if(getHeight(node.right) - getHeight(node.left) == 2) {
                if(element > node.right.val) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        } else {
            // if the element is already present in the tree, we will do nothing 
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node;
    }

    void remove(int value) {
        if(root.val == value){
            // 
            // insert(root.right, root.left);
            root = root.right;
            return ;
        }
        Node pNode = find(value, root);
        if(pNode == null){
            // value not present in the tree
            return ;
        }
        if(pNode.left.val == value){
            if(pNode.left.right == null){
                pNode.left = pNode.left.left;
            } else {
                // insert(pNode.left.right, pNode.left.left);
                pNode.left = pNode.left.right;
            }
        } else {
            if(pNode.right.right == null){
                pNode.right = pNode.right.left;
            } else {
                // insert(pNode.right.right, pNode.right.left);
                pNode.right = pNode.right.right;   
            }
        }
    }

    private Node find(int value, Node root){
        if(root == null){
            return null;
        }
        if(value <= root.val){
            return find(value, root.left);
        } else {
            return find(value, root.right);
        }
    }

    // Node rotateRight(Node a){
    //     Node b = a.left;
    //     // int newBHeight = a.height + 1;
    //     a.left = b.right;
    //     a.height = a.height-1;
    //     // b.height = newBHeight;
    //     b.right = a;
    //     return b;
    // }

    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.left;
        node2.left = node1.right;
        node1.right = node2;
        node2.height = Math.max(getHeight(node2.left), getHeight(node2.right)) + 1;
        node1.height = Math.max(getHeight(node1.left), node2.height) + 1;
        return node1;  
    }  

    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.right;  
        node1.right = node2.left;  
        node2.left = node1;  
        node1.height = Math.max(getHeight(node1.left), getHeight(node1.right)) + 1;  
        node2.height = Math.max(getHeight(node2.right), node1.height) + 1;
        return node2;  
    }  
    
    private void inOrder(Node root, StringBuilder sb) {
        if(root == null){
            return ;
        }
        inOrder(root.left, sb);
        sb.append(root.val+", ");
        inOrder(root.right, sb);
    }

    private int getHeight(Node node ) {  
        return node == null ? -1 : node.height;  
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private Node doubleWithLeftChild(Node node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);  
    }  
  
    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child  
    private Node doubleWithRightChild(Node node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }
}
