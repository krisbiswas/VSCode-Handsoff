package Practise.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class user_sol {

    ArrayList<Command> commandList;
    int[] glbVarState;
    int cursorPos = 0;

    void init(){
        commandList = new ArrayList<>();
        glbVarState = new int[26];
    }

    int addCommand(String cmd){
        String func = "addCmd";
        Command newCommand = new Command();
        newCommand.cmd = cmd;
        String outputs = newCommand.compute();
        // System.out.println(func+"->"+outputs);
        commandList.add(cursorPos, newCommand);
        updateGlobalVar(newCommand);
        cursorPos++;
        return cursorPos-1;
    }

    private void updateGlobalVar(Command cmd) {
        for(int i=0;i<cmd.varState.length;i++){
            glbVarState[i] = cmd.varState[i];
        }
    }

    void moveCursor(int steps){
        String func = "moveCursor";

    }

    void removeCommand(){
        String func = "removeCommand";
    }

    private void rollingCompute(){

    }

    class Command{
        int pos;
        String cmd;
        int[] varState;

        Command(){
            cmd = "";
            // varState = new int[26];
        }

        String compute(){
            String[] cmdSplit = cmd.split("=");
            String[] vars = cmdSplit[0].split(",");
            String[] expStrings = cmdSplit[1].split(",");
            varState = Arrays.copyOf(glbVarState, 26);
            StringBuilder results = new StringBuilder();
            for(int i=0;i<vars.length;i++){
                int result = compute(expStrings[i], expStrings[i].length()-1);
                results.append(result).append(',');
                varState[vars[i].charAt(0)-'A'] = result;
                // System.out.println(Arrays.toString(varState));
            }
            return results.toString();
        }

        private int compute(String exp, int idx){

            String[] vars = exp.split("[-+*/]");
            System.out.println(Arrays.toString(vars));
            Stack<Integer> stack = new Stack<>();
            for(int i=idx;i<exp.length();i++){
                char op = exp.charAt(i);
                if(isOperator(op)){
                    // exp.charAt(i-1) may be '(' or ')' or variable
                    char v = exp.charAt(i+1);
                    int oprand;
                    if(v >= '0' && v<='9'){
                        oprand = exp.charAt(i+1)-'0';        
                    }else if(v == ')'){
                        oprand = compute(exp, i+1);
                    }else{
                        // var
                        oprand = glbVarState[v-'A'];
                    }
                    Integer operand2 = stack.pop();
                    // System.out.println(calc(oprand, operand2, op));
                    stack.push(calc(oprand, operand2, op));
                    i++;
                }else if(op == '('){
                    break;
                }else if(op == ')'){
                    compute(exp, i+1);
                }else{
                    if(op >= '0' && op <= '9'){
                        stack.push(op-'0');
                    }else{
                        stack.push(glbVarState[op-'A']);
                    }
                }
            }
            if(stack.size() == 1){
                return stack.pop();
            }else{
                // System.out.println("stk: "+stack);
                return -1;
            }
        }

        private Integer calc(int operand, Integer operand2, char op) {
            int res = 0;
            switch(op){
                case '+':
                    res = operand + operand2;
                    break;
                case '-':
                    if(operand2 <= operand){
                        res = operand - operand2;
                    }
                    break;
                case '*':
                    res = operand * operand2;
                    break;
                case '/':
                    if(operand2 != 0){
                        res = operand/operand2;
                    }
                    break;
            }
            return res%10000;
        }

        private boolean isOperator(char c){
            return (c=='+')||(c=='-')||(c=='*')||(c=='/');
        }
    }
}
