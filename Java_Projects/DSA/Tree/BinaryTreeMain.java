package Tree;

public class BinaryTreeMain {
    public static void main(String[] args) {
        // BinaryTree tree = new BinaryTree();
        BST tree = new BST();
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(9);
        tree.insert(6);
        System.out.println(tree);
        tree.remove(1);
        // tree.remove(3);
        // tree.remove(5);
        System.out.println(tree);
    }
}
