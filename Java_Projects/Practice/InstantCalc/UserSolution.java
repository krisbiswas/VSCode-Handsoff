package Practise.InstantCalc;

import java.util.Arrays;
import java.util.Stack;

public class UserSolution {
    int MAX_LENGTH;
    StringBuilder expression;
    void init(int mLen, char[] exp){
        MAX_LENGTH = mLen;
        expression = new StringBuilder(String.valueOf(exp));
        // System.out.println("init:> "+calcTest('+', exp));
    }

    int appendTest(char link, char[] exp){
        // exp length = mLen
        expression.append(link).append(String.valueOf(exp));
        int ans = calcTest(link, expression.toString().toCharArray());
        return ans;
    }
    
    int append(char link, char[] exp){
        // exp length = mLen
        expression.append(link).append(String.valueOf(exp));
        int ans = calcInorder(expression.toString().toCharArray());
        return ans;
    }

    int eraseTest(int from){
        // to = from+mLen
        expression.replace(from, from+MAX_LENGTH+1,"");
        System.out.println(expression);
        // int ans = calcTest('+', expression.toString().toCharArray());
        int ans = calcInorder(expression.toString().toCharArray());
        return ans;
    }

    int erase(int from){
        // to = from+mLen
        expression.replace(from, from+MAX_LENGTH+1,"");
        System.out.println(expression);
        // int ans = calcTest('+', expression.toString().toCharArray());
        int ans = calcInorder(expression.toString().toCharArray());
        return ans;
    }

    int select(int from, int to){
        
        return -1;

    }

    int calcTest(char link, char[] exp){
        StringBuilder exp2;
        if(isOperator(exp[exp.length-1])){
            exp2 = new StringBuilder(String.valueOf(Arrays.copyOfRange(exp, 0, exp.length-1)).trim());
        }else{
            exp2 = new StringBuilder(String.valueOf(exp).trim());
        }

        if(link == '-'){
            if(exp[0] == '-'){
                exp2.replace(0, 1, "+");
            }else{
                exp2.insert(0, "-");
            }
        }else{
            if(exp[0] != '-'){
                exp2.insert(0, "+");
            }
        }

        String[] plusMinusExps = exp2.toString().split("[*]");
        StringBuilder newExpression = new StringBuilder(plusMinusExps[0]);
        for(int i = 1;i<plusMinusExps.length;i++){
            int num = getNumber('l', newExpression);
            StringBuilder right = new StringBuilder(plusMinusExps[i]);
            int multiplier = getNumber('r', right);
            int ans = num*multiplier;
            newExpression.append(ans).append(right);
        }
        // System.out.println(newExpression.toString());
        int ans = linearCompute(newExpression);
        return ans;
    }

    private int linearCompute(StringBuilder expression) {
        String[] operands = expression.toString().split("[+-]");
        // System.out.println(Arrays.toString(operands));
        if(expression.charAt(0) == '-'){
            operands[1] = "-"+operands[1];
        }
        int j = 1;
        int ans = Integer.parseInt(operands[j++]);
        for(int i=1;i<expression.length();i++){
            if(expression.charAt(i) == '+'){
                // System.out.println(ans);
                ans += Integer.parseInt(operands[j++]);
            }else if(expression.charAt(i) == '-'){
                // System.out.println(ans);
                ans -= Integer.parseInt(operands[j++]);
            }
        }
        // System.out.println(ans);

        return ans;
    }

    private int getNumber(char c, StringBuilder string) {
        int num = 0;
        if(c == 'l'){
            int i=string.length()-1;
            while(i>=0 && !isOperator(string.charAt(i))){
                num = (num*10)+(string.charAt(i)-'0');
                i--;
            }
            string.replace(i+1, string.length(), "");
        }else if(c == 'r'){
            int i=0;
            while(i<string.length() && !isOperator(string.charAt(i))){
                num = (num*10)+(string.charAt(i)-'0');
                i++;
            }
            string.replace(0, i, "");
        }
        return num;
    }

    boolean isOperator(char e){
        return (e == '+' || e == '-' || e == '*');
    }

    int calcInorder(char[] exp){
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for(int i=0;i<exp.length; i++){
            if(isOperator(exp[i])){
                if(operators.empty()){
                    operators.push(exp[i]);
                }else if(isHigherPrecedence(operators.peek(), exp[i]) == 1) {
                    while (!operators.empty() && isHigherPrecedence(operators.peek(), exp[i]) == 1){
                        char operator = operators.pop();
                        int right = operands.pop();
                        int left;
                        if(operands.empty()){
                            left = 0;
                        }else{
                            left = operands.pop();
                        }
                        int result = 0;
                        switch (operator){
                            case '*':result = left * right;break;
                            case '+':result = left + right;break;
                            case '-':result = left - right;break;
                        }
                        operands.push(result);
                    }
                    operators.push(exp[i]);
                }else {
                    // +,-
                    operators.push(exp[i]);
                }
            }else{
                String numString = String.valueOf(exp[i]);
                if(i+1 < exp.length && !isOperator(exp[i+1])){
                    numString+=exp[++i];
                }
                int num = Integer.parseInt(numString);
                operands.push(num);
            }
        }
        
        while(!operators.empty()){
            int result = 0;
            char operator = operators.pop();
            int right = operands.pop();
            int left = operands.pop();
            if(operator == '*'){
                result = left * right;
            } else if (operator == '+') {
                result = left + right;
            } else {
                result = left - right;
            }
            operands.push(result);
        }
        return operands.pop();
    }

    int isHigherPrecedence(char inStack, char current){
        // 1 : stack top item has higher precedence
        // -1: current operator has higher precedence
        if(inStack == '*'){
            if(current == '*'){
                return -1;
            }else{
                return 1;
            }
        } else if (inStack == '-') {
            if(current == '*'){
                return -1;
            } else if(current == '+'){
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
