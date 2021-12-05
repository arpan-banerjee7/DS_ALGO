package assignments;

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

}
