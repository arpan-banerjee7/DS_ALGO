package dp.knapsack.unbounded;
// Min number of coins

// https://leetcode.com/problems/coin-change/

public class CoinChangeProb {

	private static int coinChange(int[] coins, int amount) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		for (int j = 0; j <= amount; j++)
			dp[0][j] = Integer.MAX_VALUE - 1;
		for (int i = 1; i <= coins.length; i++)
			dp[i][0] = 0;

		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				if (coins[i - 1] <= j) {
					dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[coins.length][amount] >= Integer.MAX_VALUE - 1 ? -1 : dp[coins.length][amount];
	}

	public static void main(String[] args) {
		int[] coins = { 1, 2, 5 };
		int amount = 11;
		System.out.println(coinChange(coins, amount));
		/*
		 * Output: 3 Explanation: 11 = 5 + 5 + 1
		 */
	}

	class Solution {

		private int countSubsetSum(int[] nums, int sum, int n, Integer[][] dp) {
			if (sum == 0) {
				return 0;
			}

			if (n == 0) {
				return Integer.MAX_VALUE - 1; // tricky if return 0 here ans =0
			}
			if (dp[n][sum] != null) {
				return dp[n][sum];
			}

			// if greater do not inlcude in the subet
			if (nums[n - 1] > sum) {
				return dp[n][sum] = countSubsetSum(nums, sum, n - 1, dp);
			}

			return dp[n][sum] = Math.min(1 + countSubsetSum(nums, sum - nums[n - 1], n, dp),
					countSubsetSum(nums, sum, n - 1, dp));

		}

		public int coinChange(int[] coins, int amount) {
			Integer[][] dp = new Integer[coins.length + 1][amount + 1];
			int ret = countSubsetSum(coins, amount, coins.length, dp);
			return ret == Integer.MAX_VALUE - 1 ? -1 : ret;
		}
	}

}
