package assignments;
// 55. Jump Game

import java.util.HashMap;

// https://leetcode.com/problems/jump-game/
// https://www.youtube.com/watch?v=Pk128gC_sdw Same as leetcode 1326

import java.util.Map;

/*[2,0,0]
same as min taps to water garden
https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
	*/
public class JumpGame {
	public boolean canJump(int[] nums) {
		int min = 0;
		int max = 0;
		int index = 0;

		while (max < nums.length - 1) {
			for (int i = index; i < nums.length; i++) {
				if (i <= min && i + nums[i] >= max) {
					max = i + nums[i];
					index++;
				}

			}
			if (max == min)
				return false;
			min = max;
		}
		return true;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
		 * to 1, then 3 steps to the last index.
		 */

	}

	// DP appproach Sahil
	// TC - o(n) * max(nums[i])
	class Solution {
		private boolean isPossible(int[] nums, int idx, Map<Integer, Boolean> map) {
			if (idx >= nums.length - 1)
				return true;
			if (nums[idx] == 0)
				return false;

			if (map.containsKey(idx))
				return map.get(idx);

			for (int i = 1; i <= nums[idx]; i++) {
				if (isPossible(nums, idx + i, map)) {
					return true;
				}
			}

			map.put(idx, false);
			return false;
		}

		public boolean canJump(int[] nums) {
			Map<Integer, Boolean> map = new HashMap<>();
			return isPossible(nums, 0, map);
		}
	}
}
