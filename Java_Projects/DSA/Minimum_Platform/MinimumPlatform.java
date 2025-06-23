package Minimum_Platform;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumPlatform {
    public static void main(String[] args) {
        int[] arr = new int[]{900, 940, 950, 1100, 1500, 1800};
        int[] dep = new int[]{910, 1200, 1120, 1130, 1900, 2000};
        int ans = findPlatform(arr, dep);
        System.out.println("ans: "+ans);

        arr = new int[]{900, 1235, 1100};
        dep = new int[]{1000, 1240, 1200};
        ans = findPlatform(arr, dep);
        System.out.println("ans: "+ans);

        arr = new int[]{1114, 825, 357, 1415, 54};
        dep = new int[]{1740, 1110, 2238, 1535, 2323};
        ans = findPlatform(arr, dep);
        System.out.println("ans: "+ans);
    }
    
    static class TrainTime implements Comparable<TrainTime> {
        int arrival, departure;
        TrainTime(int arr, int dep){
            arrival = arr;
            departure = dep;
        }
        
        public int compareTo(TrainTime t){
            return arrival - t.arrival;
        }
        @Override
        public String toString() {
            return String.format("{%d, %d}", arrival, departure);
        }
    }

    static boolean isOverLapping(TrainTime a, TrainTime b){
        return (a.departure >= b.arrival);
    }

    static int findPlatform(int arr[], int dep[]) {
        if(arr.length <= 1){
            return arr.length;
        }
        TrainTime[] trainTimes = new TrainTime[arr.length];
        for(int i=0;i<arr.length;i++){
            trainTimes[i] = new TrainTime(arr[i], dep[i]);
        }
        Arrays.sort(trainTimes);
        // System.out.println(Arrays.toString(trainTimes));
        PriorityQueue<TrainTime> q = new PriorityQueue<TrainTime>(new Comparator<TrainTime>() {
            public int compare(TrainTime t1, TrainTime t2) {
                return t1.departure - t2.departure;
            };
        });
        q.offer(trainTimes[0]);
        int i = 1;
        int maxPlatforms = 0;
        while (i < trainTimes.length){
            while (!q.isEmpty() && !isOverLapping(q.peek(), trainTimes[i])){
                q.poll();
            }
            q.offer(trainTimes[i]);
            maxPlatforms = Math.max(maxPlatforms, q.size());
            i++;
        }
        return maxPlatforms;
    }
}
