package Java_Projects.Ipml;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MyStack<T> {
    List<T> vals;
    private int top;

    MyStack(){
        top = -1;
        vals = new ArrayList<T>();
    }

    public void push(T newVal){
        vals.add(newVal);
        top++;
    }

    public T pop(){
        if (top < 0){
            return null;
        }
        T v = vals.get(top);
        vals.remove(top);
        top--;
        return v;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(T val : vals){
            sb.append(val).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

}

public class TestStack {
    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<>();
        Random r = new Random();
        for (int i = 0;i<10;i++) {
            s.push(r.nextInt(100));
        }
        System.out.println(s);
        for (int i = 0;i<10;i++){
            System.out.println(s.pop());
        }
    }
}