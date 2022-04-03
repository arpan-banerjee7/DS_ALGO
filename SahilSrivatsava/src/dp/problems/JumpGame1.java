package dp.problems;

import java.util.HashMap;
import java.util.Map;

// 55. Jump Game
// https://leetcode.com/problems/jump-game/
// TC - o(n) * max(nums[i])

public class JumpGame1 {

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

	public static void main(String[] args) {
		/*
		 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
		 * to 1, then 3 steps to the last index.
		 */
	}
}
