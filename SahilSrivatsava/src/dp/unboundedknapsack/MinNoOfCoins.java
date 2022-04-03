package dp.unboundedknapsack;

// 322. Coin Change
// https://leetcode.com/problems/coin-change/
// https://practice.geeksforgeeks.org/problems/number-of-coins1824/1/#

public class MinNoOfCoins {
	public int coinChange(int[] coins, int amount) {
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
		/*
		 * Input: coins = [1,2,5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1
		 */
	}

}
