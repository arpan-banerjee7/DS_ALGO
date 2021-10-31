package dp.knapsack.unbounded;
// https://www.geeksforgeeks.org/cutting-a-rod-dp-13/

// same as 0/1 knapsack only diff is it is unbounded= when we choose that element it is still available for next subsequent choices.
public class RodCuttingProb {

	private static int knapSackRec(int W, int wt[], int val[], int n, int[][] dp) {

		// Base condition
		if (n == 0 || W == 0)
			return 0;

		if (dp[n][W] != -1)
			return dp[n][W];

		if (wt[n - 1] > W)

			// Store the value of function call
			// stack in table before return
			return dp[n][W] = knapSackRec(W, wt, val, n - 1, dp);

		else

			// Return value of table after storing
			return dp[n][W] = Math.max((val[n - 1] + knapSackRec(W - wt[n - 1], wt, val, n, dp)),
					knapSackRec(W, wt, val, n - 1, dp));
	}

	private static int cutRod(int price[], int n) {
		// code here
		int[] wt = new int[n];
		for (int i = 0; i < n; i++) {
			wt[i] = i + 1;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < n + 1; j++)
				dp[i][j] = -1;
		return knapSackRec(n, wt, price, n, dp);
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
		int n = arr.length;
		System.out.println(cutRod(arr, n)); // output: 22
	}

}
