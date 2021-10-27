package dp.knapsack.problems;
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

// Time Complexity: O(sum*n), where sum is the ‘target sum’ and ‘n’ is the size of array.
// Auxiliary Space: O(sum*n), as the size of 2-D array is sum*n.
public class SubsetSumTab {

	private static Boolean isSubsetSum(int N, int arr[], int sum) {
		boolean dp[][] = new boolean[N + 1][sum + 1];
		// sum 0 ans true
		for (int i = 0; i <= N; i++) {
			dp[i][0] = true;
		}
		// Build table K[][] in bottom up manner
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= sum; j++) {
				if (arr[i - 1] <= j)
					dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j];
			}
		}

		return dp[N][sum];
	}

	public static void main(String[] args) {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		int sum = 9;
		System.out.println(isSubsetSum(set.length, set, sum));

	}

}
