package dp.knapsack.unbounded;
// https://www.geeksforgeeks.org/cutting-a-rod-dp-13/

public class RodCuttingProbTab {

	public static int cutRod(int price[], int n) {
		// code here
		int[] wt = new int[n];
		for (int i = 0; i < n; i++) {
			wt[i] = i + 1;
		}
		int[][] dp = new int[n + 1][n + 1];

		// Build table K[][] in bottom up manner
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					dp[i][j] = 0;
				else if (wt[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				}

				else {
					dp[i][j] = Math.max(price[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
				}

			}
		}

		return dp[n][n];
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
		int n = arr.length;
		System.out.println(cutRod(arr, n)); // output: 22

	}

}
