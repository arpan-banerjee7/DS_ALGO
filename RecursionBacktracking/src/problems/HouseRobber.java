package problems;

// https://leetcode.com/problems/house-robber/

public class HouseRobber {
	class Solution {
		public int rob(int[] nums) {
			int n = nums.length;
			int[] dp = new int[n];

			dp[0] = nums[0];
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 1; i < n; i++) {
				sum1 = nums[i];
				if (i > 1) {
					sum1 += dp[i - 2];
				}
				sum2 = dp[i - 1];
				dp[i] = Math.max(sum1, sum2);
			}
			return dp[n - 1];
		}
	}

	class Solution1 {
		Integer[] dp;

		private int maxSum(int[] arr, int idx) {
			if (idx == 0)
				return arr[0];
			if (idx < 0)
				return 0;

			if (dp[idx] != null) {
				return dp[idx];
			}

			int sum1 = arr[idx] + maxSum(arr, idx - 2);
			int sum2 = maxSum(arr, idx - 1);

			return dp[idx] = Math.max(sum1, sum2);
		}

		public int rob(int[] nums) {
			int n = nums.length;
			dp = new Integer[n];
			return maxSum(nums, n - 1);
		}
	}
}