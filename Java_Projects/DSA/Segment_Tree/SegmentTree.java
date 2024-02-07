package Segment_Tree;

public class SegmentTree {
    
    class Range{
        int start,end;
        int min,max,sum;
        Range left, right;

        Range(int start, int end, int[] followers){
            this.start = start;
            this.end = end;
            build(start, end, followers);
            // System.out.println(this);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d) => max: %d, min: %d, sum: %d", start, end, max, min, sum);
        }

        void build(int s, int e, int[] followers){
            if(s == e){
                sum = max = min = followers[s];
                return ;
            }
            int mid = s+(e-s)/2;
            this.left = new Range(s, mid, followers);
            this.right = new Range(mid+1, e, followers);
            min = Math.min(left.min, right.min);
            max = Math.max(left.max, right.max);
            sum = left.sum + right.sum;
        }

        Range updateIndex(int index, int value){
            if(index == start && index == end){
                sum = max = min = value;
                return this;
            }
            if(index < start || index > end){
                return this;
            }
            left.updateIndex(index, value);
            right.updateIndex(index, value);
            min = Math.min(left.min, right.min);
            max = Math.max(left.max, right.max);
            sum = left.sum + right.sum;
            return this;
        }

        Range add(int index, int value){
            if(index == start && index == end){
                sum += value;
                min = sum;
                max = sum;
                return this;
            }
            if(index < start || index > end){
                return this;
            }
            left.add(index, value);
            right.add(index, value);
            min = Math.min(left.min, right.min);
            max = Math.max(left.max, right.max);
            sum = left.sum + right.sum;
            return this;
        }

        Range remove(int index, int value){
            if(index == start && index == end){
                sum -= value;
                min = sum;
                max = sum;
                return this;
            }
            if(index < start || index > end){
                return this;
            }
            left.remove(index, value);
            right.remove(index, value);
            min = Math.min(left.min, right.min);
            max = Math.max(left.max, right.max);
            sum = left.sum + right.sum;
            return this;
        }

        public int getMax(int s, int e) {
            if(start == s && end == e){
                return max;
            }
            if(e < start || s > end){
                return -1;
            }
            int lMax = left.getMax(s, e);
            int rMax = right.getMax(s, e);
            return Math.max(lMax, rMax);
        }

        public int getMin(int s, int e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getMin'");
        }
    }

    Range tree;

    SegmentTree(int numOfArtists, int[] followers){
        tree = new Range(0, followers.length-1, followers);
    }
    
    int updateIndex(int index, int value){
        return tree.updateIndex(index, value).sum;
    }

    int addFollowers(int id, int value){
        return tree.add(id, value).sum;
    }

    int removeFollowers(int id, int value){
        return tree.remove(id, value).sum;
    }

    int calcDiff(int s, int e){
        int max = tree.getMax(s,e);
        int min = tree.getMin(s,e);
        return max - min;
    }
}
