package Segment_Tree;

import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        System.out.println("Lets begin");
        // SegmentTree tree = new SegmentTree(4, new int[]{8, 5, 2, 1, 4});
        // tree.addFollowers(3, 10);
        // // tree.removeFollowers(3, 3);
        // System.out.println(tree.calcDiff(1, 4));
        int[] nums = new int[]{7,8,3,2};
        System.out.println(Arrays.toString(nums));

        n = nums.length;
        segTree = new int[n*2];
        for(int i=0;i<n;i++){
            segTree[i+n] = nums[i];
        }
        for(int i=2*n-1;i>=2;i--){
            segTree[i/2] += segTree[i];
        }

        System.out.println(Arrays.toString(segTree));
        System.out.println(rangeSum(1,1));
    }

    static int[] segTree;
    static int n;
    public static int rangeSum(int left, int right) {
    
        return sumRange(1, 0, n-1, left, right);
    }
    
    public static int sumRange(int rangeSumIndex, int rangeL, int rangeR, int queryL, int queryR) {
        if(rangeL == queryL && rangeR == queryR){
            return segTree[rangeSumIndex];
        }
        if(rangeR < queryL || rangeL > queryR){
            return 0;
        }
        int rangeMid = rangeL + (rangeR - rangeL)/2;
        int leftSum = 0, rightSum = 0;
        if(rangeL < queryL){
            if(rangeMid <= queryL){
                rightSum = sumRange(rangeSumIndex*2 + 1, rangeMid+1, rangeR, queryL, queryR);
            }else{
                leftSum = sumRange(rangeSumIndex*2, rangeL, rangeMid, queryL, queryR);
                rightSum = sumRange(rangeSumIndex*2 + 1, rangeMid+1, rangeR, queryL, queryR);
            }
        } else {
            if(rangeMid <= queryR){
                leftSum = sumRange(rangeSumIndex*2, rangeL, rangeMid, queryL, queryR);
                rightSum = sumRange(rangeSumIndex*2 + 1, rangeMid+1, rangeR, queryL, queryR);
            }else{
                leftSum = sumRange(rangeSumIndex*2, rangeL, rangeMid, queryL, queryR);
            }
        }
        return leftSum + rightSum;
    }
}