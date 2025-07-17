package Ipml;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Team {
    String name = null;
    int score = -1;
}

class pq <T> {
    List<T> vals;
    pq(){
        vals = new ArrayList<>();
    }
    public void add(T newVal){
        vals.add(newVal);
    }

    public T front(){
        if(vals.isEmpty()){
            return null;
        }
        T v = vals.get(0);
        vals.remove(0);
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

public class TestPQ {
    public static void main(String[] args) {
        pq<Integer> s = new pq<>();
        Random r = new Random();
        for (int i = 0;i<10;i++){
            s.add(r.nextInt(100));
        }
        System.out.println(s);
        for (int i = 0;i<10;i++){
            System.out.println(s.front());
        }
    }
}
