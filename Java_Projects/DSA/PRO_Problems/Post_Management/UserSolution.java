package PRO_Problems.Post_Management;

import java.util.HashMap;
import java.util.TreeSet;

public class UserSolution {
    class Post implements Comparable<Post> {
        int id, timeStamp;
        String[] hashTags;

        Post(int id, int hashTagCount, int time){
            this.id = id;
            hashTags = new String[hashTagCount];
            timeStamp = time;
        }

        @Override
        public int compareTo(Post o) {
            return o.timeStamp - timeStamp;
        }
    }

    class Bucket {
        Post[] posts = new Post[1000];
        int sizeOccupied;

        void add(Post p){
            sizeOccupied++;
            posts[p.timeStamp % numberOfBuckets] = p;
        }

        void remove(Post p ){
            sizeOccupied--;
            posts[p.timeStamp % numberOfBuckets] = null;
        }
    }

    final int MAX_POSTS = 1_000_000;
    int TIME = 0;
    HashMap<Integer, Post> posts;
    HashMap<String, Bucket[]> hashTagPosts;
    int numberOfBuckets = 1000;

    void init(int N, int[] postIds, int[] hashTagNum, String[][] hashTags){
        posts = new HashMap<>();
        // buckets = new Bucket[numberOfBuckets];
        hashTagPosts = new HashMap<>();
        TIME = N;
        for(int i=0;i<N;i++){
            Post p = new Post(postIds[i], hashTagNum[i], TIME--);
            for(int j=0;j<hashTagNum[j];j++){
                p.hashTags[j] = hashTags[i][j];
                hashTagPosts.compute(hashTags[i][j], (tag, set)->{
                    if(set == null){
                        set = new Bucket[1000];
                    }
                    set[(TIME+1)/numberOfBuckets].add(p);
                    return set;
                });
            }
            posts.computeIfAbsent(postIds[i], k -> p);
            // buckets[(TIME+1)/numberOfBuckets].posts[(TIME+1)%numberOfBuckets] = p;
        }
        TIME = N;
    }

    void addPost(int postId, int hashTagNum, String[] hashTags){
        Post p = new Post(postId, hashTagNum, ++TIME);
        for(int j=0;j<hashTagNum;j++){
            p.hashTags[j] = hashTags[j];
            hashTagPosts.compute(hashTags[j], (tag, set)->{
                if(set == null){
                    set = new Bucket[1000];
                }
                set[(TIME+1)/numberOfBuckets].add(p);
                return set;
            });
        }
        posts.computeIfAbsent(postId, k -> p);
        // buckets[TIME/numberOfBuckets].posts[TIME%numberOfBuckets] = p;
    }

    void removePost(int postId){
        Post p = posts.remove(postId);
        for(String hashTag : p.hashTags){
            hashTagPosts.get(hashTag)[p.timeStamp/numberOfBuckets].remove(p);
        }
        // buckets[p.timeStamp/numberOfBuckets].posts[p.timeStamp%numberOfBuckets] = null;
    }

    int getBucket(Bucket[] buckets, int n){
        int sum = 0;
        int i=buckets.length-1;
        for(;i>=0;i--){
            sum += buckets[i].sizeOccupied;
            if(sum >= n){
                break;
            }
        }
        return i;
    }

    int[] getPosts(Bucket[] firstBuckets, Bucket[] secondBuckets, int endIndex){
        int bucketId1 = getBucket(firstBuckets, endIndex);
        int bucketId2 = getBucket(secondBuckets, endIndex);

        int fBucketIter = bucketId1;
        int sBucketIter = bucketId2;
        int[] foundPosts = new int[10];
        int foundPostsIterator = 0;
        int fPostsIter = 0;
        int sPostsIter = 0;
        int foundInF = 0;
        int foundInS = 0;
        while (fBucketIter < firstBuckets.length && sBucketIter < secondBuckets.length) {
            while(foundInF < firstBuckets[fBucketIter].sizeOccupied && fPostsIter < firstBuckets[fBucketIter].posts.length && 
                firstBuckets[fBucketIter].posts[fPostsIter] == null){
                    foundInF++;
                    fPostsIter++;
            }
            if(fPostsIter >= firstBuckets[fBucketIter].posts.length){
                foundInF = 0;
                fPostsIter = 0;
                fBucketIter++;
                continue;
            }
            while(foundInS < secondBuckets[sBucketIter].sizeOccupied && 
                sPostsIter < secondBuckets[sBucketIter].posts.length && 
                secondBuckets[sBucketIter].posts[sPostsIter] == null){
                    foundInS++;
                    sPostsIter++;
            }
            if(sPostsIter >= secondBuckets[sBucketIter].posts.length){
                foundInS = 0;
                sPostsIter = 0;
                sBucketIter++;
                continue;
            }
            
            if(firstBuckets[fBucketIter].posts[fPostsIter].timeStamp > secondBuckets[sBucketIter].posts[sPostsIter].timeStamp){
                foundPosts[foundPostsIterator++] = firstBuckets[fBucketIter].posts[fPostsIter].id;
                fPostsIter++;
            } else {
                foundPosts[foundPostsIterator++] = secondBuckets[sBucketIter].posts[sPostsIter].id;
                sPostsIter++;
            }

            if(foundPostsIterator == 10){
                break;
            }
        }
        return foundPosts;
    }

    int[] getPosts(Bucket[] buckets, int endIndex){
        int bucketId = getBucket(buckets, endIndex);
        int[] foundPosts = new int[10];
        int foundPostsIterator = 0;
        for(int i=bucketId;i>=0;i++){
            for(int j = 0;j<buckets[i].posts.length && foundPostsIterator<10;j++){
                if(buckets[i].posts[j] != null){
                    foundPosts[foundPostsIterator++] = buckets[i].posts[j].id;
                }
            }
            if(foundPostsIterator == 10){
                break;
            }
        }
        return foundPosts;
    }

    int findPosts(int hashTagNum, String[] hashTags, int pageNumber, int[] ansIds){
        int indexOfFirstPostInPage = (pageNumber-1)*10+1;
        int indexOfLastPostInPage = pageNumber*10;
        if(hashTagNum > 1){
            if(!hashTagPosts.containsKey(hashTags[0]) && !hashTagPosts.containsKey(hashTags[1])){
                return 0;
            }
            /// using posts2 bucket searching in sqrt(N) time
            // int bucketIndex = getBucket(hashTagPosts.get(hashTags[0]), indexOfFirstPostInPage);
            // int bucketIndex2 = getBucket(hashTagPosts.get(hashTags[1]), indexOfFirstPostInPage);
            int[] matchedPosts = getPosts(hashTagPosts.get(hashTags[0]), hashTagPosts.get(hashTags[1]), indexOfLastPostInPage);

            // if(indexOfFirstPostInPage >= arr1.length + arr2.length){
            //     return 0;
            // }
            /// Return posts in given page number
            
            return 0;
        } else {
            if(!hashTagPosts.containsKey(hashTags[0])){
                return 0;
            }
            int[] matchedPosts = getPosts(hashTagPosts.get(hashTags[0]), indexOfLastPostInPage);
            // if(indexOfFirstPostInPage >= matchedPosts.length){
            //     return 0;
            // }
            int i = 0;
            for(;i < matchedPosts.length && i<10;i++){
                ansIds[i] = matchedPosts[i];
            }
            return i;
        }
    }
}
