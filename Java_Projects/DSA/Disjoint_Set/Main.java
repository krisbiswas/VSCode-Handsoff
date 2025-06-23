package Disjoint_Set;

public class Main {

    public static void main(String[] args) {
        Set set = new Set(new int[]{1, 3, 4, 2, 6, 7, 5, 9});
        System.out.println(set.union(1, 4));
        System.out.println(set.union(4, 9));
        System.out.println(set.union(1, 9));
        System.out.println(set.union(4, 3));
        System.out.println(set.find(7));
        System.out.println(set.find(9));
        System.out.println(set.find(1));
        System.out.println(set.find(1));
    }
}