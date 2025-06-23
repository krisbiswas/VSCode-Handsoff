package PRO_Problems.Post_Management;

public class Solution {
    public static void main(String[] args) {
        UserSolution sol = new UserSolution();
        String[][] hashTags = new String[5][5];
        hashTags[0] = new String[]{"train"};
        hashTags[1] = new String[]{"car"};
        hashTags[2] = new String[]{"train","car"};
        hashTags[3] = new String[]{"car"};
        hashTags[4] = new String[]{"bus", "car"};
        sol.init(5, new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 1, 2}, hashTags);
        sol.addPost(12, 3, new String[]{"car", "plane", "bus"});
        sol.addPost(11, 2, new String[]{"car", "bike"});
        int countOfMatchingPosts = sol.findPosts(2, new String[]{"car", "bus"}, 1, new int[10]);
        System.out.println(countOfMatchingPosts);
        // int kthvalue = sol.kth(new int[]{2,4,8,12,15,16,22}, 0, 6, new int[]{6,9,13,19,20}, 0, 4, 9);
        // System.out.println(kthvalue);
        // 2,4,6,8,9,12,13,15,16,19,20,22
    }
}
