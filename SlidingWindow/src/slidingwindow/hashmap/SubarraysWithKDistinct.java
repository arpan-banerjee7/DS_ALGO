package slidingwindow.hashmap;

import java.util.*;

// Whenever we have to find count of subarrays with k distict values or similar think of this approach
// similar questions-
// 930. Binary Subarrays With Sum
// 1248. Count Number of Nice Subarrays
public class SubarraysWithKDistinct {
    public static int atmostK(int arr[], int k) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            while (map.size() > k) {
                map.put(arr[j], map.get(arr[j]) - 1);
                if (map.get(arr[j]) == 0) {
                    map.remove(arr[j]);
                }
                j++;
            }
            ans += (i - j + 1);
        }
        return ans;

    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atmostK(nums, k) - atmostK(nums, k - 1);
    }
}
/*
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 */