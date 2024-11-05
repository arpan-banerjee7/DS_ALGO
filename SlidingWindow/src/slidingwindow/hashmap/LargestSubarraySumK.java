package slidingwindow.hashmap;

import java.util.*;

// https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1

public class LargestSubarraySumk {
    // Function for finding maximum and value pair
    public static int lenOfLongSubarr(int A[], int N, int k) {
        //Complete the function
        Map<Integer, Integer> map = new HashMap<>();
        int key = 0;
        int sum = 0;
        int maxLen = 0;
        map.put(0, -1);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (map.containsKey(sum - k))
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return maxLen;
    }

}
/*
Input: arr[] = [10, 5, 2, 7, 1, 9], k = 15
Output: 4
Explanation: The subarray [5, 2, 7, 1] has a sum of 15 and length 4.
 */