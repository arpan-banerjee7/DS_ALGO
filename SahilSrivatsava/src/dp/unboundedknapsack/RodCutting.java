package dp.unboundedknapsack;

// Rod Cutting 
// https://practice.geeksforgeeks.org/problems/rod-cutting0840/1/#

public class RodCutting {
	private int maxValue(int[] price, int idx, int n, Integer[][] dp) {
		// when the rod is entirely cut,
		// or when we exhaust all the possible pieces and cannot cut the rod anymore
		if (n == 0 || idx == price.length)
			return 0;
		if (dp[idx][n] != null)
			return dp[idx][n];

		int c1 = 0;
		if (idx + 1 <= n)
			c1 = price[idx] + maxValue(price, idx, n - (idx + 1), dp);
		int c2 = maxValue(price, idx + 1, n, dp);

		return dp[idx][n] = Math.max(c1, c2);

	}

	public int cutRod(int price[], int n) {
		// code here
		Integer[][] dp = new Integer[n + 1][n + 1];
		return maxValue(price, 0, n, dp);
	}

	public static void main(String[] args) {
		/*
		 * Input: N = 8 Price[] = {1, 5, 8, 9, 10, 17, 17, 20} Output: 22 Explanation:
		 * The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and
		 * 6, i.e., 5+17=22.
		 */
	}

}
