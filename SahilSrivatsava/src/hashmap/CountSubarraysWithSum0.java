package hashmap;

import java.util.HashMap;
import java.util.Map;

// Subarrays With Zero Sum
// https://www.codingninjas.com/codestudio/problems/subarrays-with-zero-sum_3161876?leftPanelTab=0

public class CountSubarraysWithSum0 {
	public static int countSubarrays(int n, int[] arr) {
		// Write your code here.
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		int sum = 0;
		int count = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if (map.containsKey(sum)) {
				count += map.get(sum);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		/*
		 * Let ‘ARR’ be: [1, 4, -5] The subarray [1, 4, -5] has a sum equal to 0. So the
		 * count is 1.
		 */
	}

}
