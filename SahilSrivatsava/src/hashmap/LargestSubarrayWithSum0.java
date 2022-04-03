package hashmap;

import java.util.HashMap;
import java.util.Map;

// Largest subarray with 0 sum 
// https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1/

public class LargestSubarrayWithSum0 {
	private static int maxLen(int arr[], int n) {
		// Your code here
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int sum = 0;
		int maxLen = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if (map.containsKey(sum)) {
				maxLen = Math.max(maxLen, i - map.get(sum));
			} else {
				map.put(sum, i);
			}

		}
		return maxLen;
	}

	public static void main(String[] args) {
		/*
		 * Input: N = 8 A[] = {15,-2,2,-8,1,7,10,23} Output: 5 Explanation: The largest
		 * subarray with sum 0 will be -2 2 -8 1 7.
		 */
	}

}
