package dp.knapsack;
// https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/

// Time Complexity: O(sum*n), where the sum is the ‘target sum’ and ‘n’ is the size of the array.
// Auxiliary Space: O(sum*n), as the size of the 2-D array, is sum*n. 
public class CountSubsetSumTab {

	static int subsetSum(int a[], int n, int sum) {

		// Initializing the matrix
		int dp[][] = new int[n + 1][sum + 1];

		for (int i = 0; i <= n; i++)
			dp[i][0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {

				// If the value is greater than the sum
				if (a[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - a[i - 1]];
				}
			}
		}

		return dp[n][sum];
	}

	// Driver Code
	public static void main(String[] args) {
		int n = 4;
		int a[] = { 3, 3, 3, 3 };
		int sum = 6;

		System.out.print(subsetSum(a, n, sum));
	}

}
