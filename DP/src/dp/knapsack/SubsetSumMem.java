package dp.knapsack;
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

// Time Complexity: O(sum*n), where sum is the ‘target sum’ and ‘n’ is the size of array.
// Auxiliary Space: O(sum*n), as the size of 2-D array is sum*n.
public class SubsetSumMem {
	private static boolean subSetSum(int n, int[] arr, int sum, Boolean[][] dp) {
		if (sum == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}
		if (dp[n][sum] != null) {
			return dp[n][sum];
		}
		if (arr[n - 1] > sum) {
			dp[n][sum] = subSetSum(n - 1, arr, sum, dp);
			return dp[n][sum];
		} else {
			dp[n][sum] = subSetSum(n - 1, arr, sum, dp) || subSetSum(n - 1, arr, sum - arr[n - 1], dp);
			return dp[n][sum];
		}
	}

	static Boolean isSubsetSum(int N, int arr[], int sum) {
		Boolean[][] dp = new Boolean[N + 1][sum + 1];// cannot use boolean[][] here
		// because it defaults to false, and if (dp[n][sum] this check will only work
		// for true values, but we need to
		// get back false values which we have calculated earlier as well, so
		// effectively we always recalculate for the false values
		// with this.
		return subSetSum(N, arr, sum, dp);
	}

	public static void main(String[] args) {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		int sum = 9;
		System.out.println(isSubsetSum(set.length, set, sum));
	}
}
