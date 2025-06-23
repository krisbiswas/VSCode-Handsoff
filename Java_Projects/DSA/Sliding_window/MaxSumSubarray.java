package Sliding_window;

import java.util.Arrays;

public class MaxSumSubarray {
    public static void main(String[] args) {
        int[] array = new int[]{4,5,8,2,4,9,12,-1,90,4,-23,10};
        int windowSize = 3;
        int[] subarray = findMaximumSumSubarrayOfSizeK(array, windowSize);
        System.out.println("Subarray: "+Arrays.toString(subarray));
    }
    
    static int[] findMaximumSumSubarrayOfSizeK(int[] array, int k){
        if(k > array.length){
            return -1;
        }
        int i=0;
        int sum = 0;
        int maxSum = 0;
        int[] window = new int[k];
        for(;i<k;i++){
            sum += array[i];
            // maxSum = Math.max(maxSum, sum);
            window[i] = array[i];
        }
        maxSum = sum;
        int maxSumIndex = 0;
        for(;i<array.length; i++){
            // i-k -> i
            sum = sum - array[i-k] + array[i];
            if(maxSum < sum){
                maxSumIndex = i-k;
                maxSum = sum;
            }
        }
        int[] ans = new int[k];
        for(int i=maxSumIndex;i<=k;i++){
            ans[i-maxSumIndex] = array[i];
        }
        return ans;
    }
}
