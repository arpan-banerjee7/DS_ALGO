package dp.knapsack;
// https://www.geeksforgeeks.org/partition-problem-dp-18/

// Exactly the same as subset sum prob, here the target sum=arraySum/2;
public class EqualSumPartition {
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

	private static int equalPartition(int N, int arr[]) {
		// code here
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}

		// Declare the table dynamically
		Boolean dp[][] = new Boolean[N + 1][(sum / 2) + 1];

		if (sum % 2 == 0) {
			if (subSetSum(N, arr, sum / 2, dp)) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		// code here
		int[] arr = { 1, 5, 11, 5 };
		int N = 4;
		System.out.println(equalPartition(N, arr));
	}

}
