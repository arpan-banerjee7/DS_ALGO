package microsoft;

// 213. House Robber II
// https://leetcode.com/problems/house-robber-ii/
public class HouseRobberII {
	Integer dp[][];

	private int maxSum(int[] arr, int start, int n, int idx) {
		if (n == start)
			return arr[start];
		if (n < start)
			return 0;

		if (dp[n][idx] != null) {
			return dp[n][idx];
		}

		int sum1 = arr[n] + maxSum(arr, start, n - 2, idx);
		int sum2 = maxSum(arr, start, n - 1, idx);

		return dp[n][idx] = Math.max(sum1, sum2);
	}

	public int rob(int[] nums) {
		int n = nums.length;
		if (n == 1)
			return nums[0];
		dp = new Integer[n + 1][2];

		// including the first house, we have to skip the last house
		int one = maxSum(nums, 0, n - 2, 0);
		int two = maxSum(nums, 1, n - 1, 1);
		return Math.max(one, two);
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [2,3,2] Output: 3 Explanation: You cannot rob house 1 (money =
		 * 2) and then rob house 3 (money = 2), because they are adjacent houses.
		 */
	}

}
