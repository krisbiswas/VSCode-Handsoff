package Practise.CLI;

class sol{
    public static void main(String[] args) {
        user_sol sol = new user_sol();
        sol.init();
        System.out.println(sol.addCommand("B,C=4,5"));
        System.out.println(sol.addCommand("A,F=3,8"));
        System.out.println(sol.addCommand("F,B,Z=A,66+(B-5),10"));
    }
}