package hashmap;

import java.util.HashMap;
import java.util.Map;

// 974. Subarray Sums Divisible by K
// https://leetcode.com/problems/subarray-sums-divisible-by-k/

public class CountSubarraysWithSumDivByk {
	public int subarraysDivByK(int[] nums, int k) {
		int sum = 0;
		int ans = 0;
		int key = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(key, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			key = ((sum % k) + k) % k; // for ngeative modulouses. -1%2=-1 actually it should be 1
			// so ((-1%2)+2)%2
			if (map.containsKey(key)) {
				ans += map.get(key);
			}
			map.put(key, map.getOrDefault(key, 0) + 1);
		}
		return ans;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [4,5,0,-2,-3,1], k = 5 Output: 7 Explanation: There are 7
		 * subarrays with a sum divisible by k = 5: [4, 5, 0, -2, -3, 1], [5], [5, 0],
		 * [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
		 */
	}

}
