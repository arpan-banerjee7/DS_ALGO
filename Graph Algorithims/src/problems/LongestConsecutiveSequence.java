package problems;

import java.util.*;

// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/description/
// HashSet, simple, check for the first element of the series, currently iterating over x, check in set if x-1 is present, when not present then current element is the starting
// Union find solution is also there bt its tricky
public class LongestConsecutiveSequence {

    // Sorting solution, easiest
    // TC O(N) + O(NlogN)
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int max = 1;
        int count = 1;
        int lastSmaller = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == lastSmaller) continue;
            if (nums[i] - lastSmaller == 1) {
                count++;
            } else {
                count = 1;
            }
            max = Math.max(count, max);
            lastSmaller = nums[i];
        }
        return max;
    }

    // Hashing solution
    // TC O(2N)
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int max = 1;
        int count = 1;
        for (int n : set) {
            // we will try to find the begining of the sequence, if set contans value n-1, means current elemebt is not
            // the starting point of the sequence, hence we move to the next iteration
            if (!set.contains(n - 1)) {
                while (set.contains(n + 1)) {
                    count++;
                    n++;
                }
                max = Math.max(max, count);
                count = 1;
            }
        }
        return max;
    }


    // Union Find Solution
    // TC O(N)
    public int longestConsecutive3(int[] nums) {
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
}
