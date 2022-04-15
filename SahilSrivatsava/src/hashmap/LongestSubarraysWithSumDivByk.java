package hashmap;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarraysWithSumDivByk {
	int longSubarrWthSumDivByK(int nums[], int n, int k) {
		// Complete the function
		int sum = 0;
		int ans = 0;
		int key = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(key, -1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			key = ((sum % k) + k) % k; // for ngeative modulouses. -1%2=-1 actually it should be 1
			// so ((-1%2)+2)%2
			if (map.containsKey(key)) {
				ans = Math.max(ans, i - map.get(key));
			} else {
				map.put(key, i);
			}
		}
		return ans;

	}

	public static void main(String[] args) {
		/*
		 * Input: A[] = {2, 7, 6, 1, 4, 5} K = 3 Output: 4 Explanation:The subarray is
		 * {7, 6, 1, 4} with sum 18, which is divisible by 3.
		 */

	}

}
