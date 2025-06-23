package PRO_Problems.Max_Puddles;

public class Solution {
    public static void main(String[] args) {
        UserSolution usersolution = new UserSolution();
        int[] valleyHeights = {150,1,1,2,3,1,2,4,5,7,8,9,9,9,11,12,150};
        usersolution.init(valleyHeights.length, valleyHeights);
        int counts = usersolution.countPositions(3, new int[]{3,2,1});
        System.out.println("Count tank possible positions: "+counts);
        int maxPuddleSize = usersolution.buildPuddleAndPourWater(3, new int[]{3,2,1}, 4);
        System.out.println("Puddle Size: "+maxPuddleSize);
    }
}
