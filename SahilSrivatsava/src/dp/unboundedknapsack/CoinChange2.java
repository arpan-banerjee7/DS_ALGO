package dp.unboundedknapsack;

// 518. Coin Change 2
// https://leetcode.com/problems/coin-change-2/

public class CoinChange2 {
	private int coinChange(int[] coins, int idx, int amount, Integer[][] dp) {
		if (amount == 0)
			return 1;
		if (idx == coins.length)
			return 0;

		if (dp[idx][amount] != null)
			return dp[idx][amount];

		int c1 = 0;
		if (coins[idx] <= amount)
			c1 = coinChange(coins, idx, amount - coins[idx], dp);
		int c2 = coinChange(coins, idx + 1, amount, dp);

		return dp[idx][amount] = c1 + c2;
	}

	public int change(int amount, int[] coins) {
		int n = coins.length;
		Integer[][] dp = new Integer[n][amount + 1];
		return coinChange(coins, 0, amount, dp);
	}

	public static void main(String[] args) {
		/*
		 * Input: amount = 5, coins = [1,2,5] Output: 4 Explanation: there are four ways
		 * to make up the amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1
		 */
	}

}
