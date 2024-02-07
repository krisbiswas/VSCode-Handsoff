package Segment_Tree;

public class Main{
    public static void main(String[] args) {
        System.out.println("Lets begin");
        SegmentTree tree = new SegmentTree(4, new int[]{8, 5, 2, 1, 4});
        tree.addFollowers(3, 10);
        tree.removeFollowers(3, 3);
    }
}