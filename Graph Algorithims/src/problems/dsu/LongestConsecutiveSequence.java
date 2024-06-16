package graph.dsu;

import java.util.*;

// https://leetcode.com/problems/longest-consecutive-sequence/description/?envType=problem-list-v2&envId=p4wxv0w3
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        UF uf = new UF(nums.length);
        // Map val to index in nums
        Map<Integer, Integer> valToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (valToIndex.containsKey(nums[i])) {
                continue;
            }

            if (valToIndex.containsKey(nums[i] - 1)) {
                uf.union(i, valToIndex.get(nums[i] - 1));
            }

            if (valToIndex.containsKey(nums[i] + 1)) {
                uf.union(i, valToIndex.get(nums[i] + 1));
            }

            valToIndex.put(nums[i], i);
        }

        return uf.getLargetComponentSize();
    }

    class UF {
        private int[] parent;
        private int[] size;

        public UF(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) { // connected if consecutive
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (rootX < rootY) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }

        ;

        private int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        ;

        public int getLargetComponentSize() {
            int maxSize = 0;
            for (int i = 0; i < parent.length; i++) {
                if (size[i] > maxSize) {
                    maxSize = size[i];
                }
            }

            return maxSize;
        }
    }

    /*
    Example 1:
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

    Example 2:
    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9
     */
}
/*
[100,4,200,1,3,2]
[0,3,7,2,5,8,4,6,0,1]
[1,2,0,1]
[0]
[0,0]
[]

We cannot do the simple rank algo right in union, this is imp-
size[x] += size[y];
generally if we connect a component with smaller rank to a larger rank then
we do not increase the rank at all. only if both the components are of same then
we increase rank by 1. But here we are keeping track of the size. Lets say we have a connected component like this-
3->4->5->6->7
We have got another connected component
1->2
Now, we want to connect these two components-
2->3
parent of 3 is 7, parent of 2 is 2.
Hence if we only keep track of rank, rank of 7 will not increase, we will simply make 7 as the parent of 3.
However, for this problem we keep an additional array called size, where we keep track of size, here whenever we do union, we add both of their sizes.
So, now new size of 7 would be 7.

Hope this helps someone, who was confused like me, why we are keeping track of this size variable
BDW, I think we can only keep track of size and make the comparison by size and ignore rank altogether, because when we do path compression,
 rank anyways gets distorted.

 */