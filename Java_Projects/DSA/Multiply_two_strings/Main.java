package Multiply_two_strings;

public class Main {
    public static void main(String[] args) {
        // String s1 = "0033", s2 = "021";
        String s1 = "-8463473661618", s2 = "0000";
        // String s1 = "-24", s2 = "98";

        Main question = new Main();
        String ans = question.multiplyStrings(s1, s2);
        System.out.println("Multiple = "+ans);
    }

    public String multiplyStrings(String s1, String s2) {
        boolean isS1Negative = s1.charAt(0) == '-';
        boolean isS2Negative = s2.charAt(0) == '-';
        s1 = isS1Negative ? s1.substring(1) : s1;
        s2 = isS2Negative ? s2.substring(1) : s2;

        if(s1.isEmpty() || s2.isEmpty()){
            return "0";
        }

        String multiple = multiply(s1, s2);
        if(multiple.isEmpty()){
            return "0";
        } else if(isS1Negative ^ isS2Negative == true){
            return "-"+multiple;
        }
        return multiple;
    }

    private String multiply(String s1, String s2) {
        int[] product = new int[s1.length() + s2.length()];
        for(int i=s2.length()-1;i >= 0 ;i--){
            for(int j=s1.length()-1;j >= 0;j--){
                product[i+j+1] += valueOf(s2.charAt(i)) * valueOf(s1.charAt(j));
                product[i+j] += product[i+j+1]/10;
                product[i+j+1] %= 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < product.length && product[i] == 0) i++;
        for(;i<product.length;i++){
            sb.append(product[i]);
        }
        return sb.toString();
    }

    int valueOf(char c){
        return c-'0';
    }
}
