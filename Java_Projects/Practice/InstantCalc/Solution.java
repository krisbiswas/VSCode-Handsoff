package Practice.InstantCalc;

public class Solution {

    static String[] eqs = new String[]{"-3+5-9*9","21-4+8*3", "11-4*3-5"};
    public static void main(String[] args) {
        UserSolution sol = new UserSolution();
        sol.init(8, eqs[0].toCharArray());
        long start, end;

        start = System.nanoTime();
        System.out.println(sol.appendTest('+', eqs[1].toCharArray()));
        end = System.nanoTime();
        System.out.println("Time Taken: "+(end-start)/1000);
        
        start = System.nanoTime();
        System.out.println(sol.eraseTest(3));
        end = System.nanoTime();
        System.out.println("Time Taken: "+(end-start)/1000);

//////////////////////////////////////////////
        sol.init(8, eqs[0].toCharArray());

        start = System.nanoTime();
        System.out.println(sol.append('+', eqs[1].toCharArray()));
        end = System.nanoTime();
        System.out.println("Time Taken: "+(end-start)/1000);
        
        start = System.nanoTime();
        System.out.println(sol.erase(3));
        end = System.nanoTime();
        System.out.println("Time Taken: "+(end-start)/1000);
    }
}
