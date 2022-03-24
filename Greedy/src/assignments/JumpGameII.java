package assignments;

import java.util.HashMap;
import java.util.Map;

// 45. Jump Game II
// https://leetcode.com/problems/jump-game-ii/
// same as min jumps, and min taps to water a garden
// https://www.youtube.com/watch?v=Pk128gC_sdw Same as leetcode 1326

public class JumpGameII {
	public int jump(int[] nums) {
		int min = 0;
		int max = 0;
		int jumps = 0;
		int index = 0;

		while (max < nums.length - 1) {
			for (int i = index; i < nums.length; i++) {
				if (i <= min && i + nums[i] >= max) {
					max = i + nums[i];
				}
			}

			if (min == max)
				return -1;
			jumps++;
			min = max;
		}
		return jumps;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps
		 * to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to
		 * the last index.
		 */
	}

	// DP approach Sahil
	// TC - o(n) * max(nums[i])
	class Solution {
		private int isPossible(int[] nums, int idx, Map<Integer, Integer> map) {
			if (idx >= nums.length - 1)
				return 0;
			if (nums[idx] == 0)
				return Integer.MAX_VALUE - 1000;

			if (map.containsKey(idx))
				return map.get(idx);

			int ans = Integer.MAX_VALUE;
			for (int i = 1; i <= nums[idx]; i++) {
				int tempAns = 1 + isPossible(nums, idx + i, map);
				ans = Math.min(tempAns, ans);
			}

			map.put(idx, ans);
			return ans;
		}

		public int jump(int[] nums) {
			Map<Integer, Integer> map = new HashMap<>();
			return isPossible(nums, 0, map);
		}
	}

}
