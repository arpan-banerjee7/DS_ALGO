package dp.knapsack.problems;
// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/

// derived from equal sum partition problem
// use the last row of the dp table, iterate till sum/2 check which sums are possible
// find the min out of them

// Time Complexity = O(n*sum) where n is the number of elements and sum is the sum of all elements.
public class MinSubsetSumDiffTab {

	private static int minDifference(int arr[], int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}
		boolean[][] dp = new boolean[n + 1][sum + 1];
		// when sum is 0 always true
		for (int i = 0; i <= n; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				if (arr[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
				}
			}
		}

		int currMin = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= sum / 2; i++) {
			if (dp[n][i]) {
				currMin = sum - 2 * i;
				min = Math.min(min, currMin);
			}
		}
		return min;
	}

	public static void main(String[] args) {
		int N = 4;
		int[] arr = { 1, 6, 11, 5 };
		System.out.println(minDifference(arr, N));
		/*
		 * Output: 1 Explanation: Subset1 = {1, 5, 6}, sum of Subset1 = 12 Subset2 =
		 * {11}, sum of Subset2 = 11
		 */
	}

}
