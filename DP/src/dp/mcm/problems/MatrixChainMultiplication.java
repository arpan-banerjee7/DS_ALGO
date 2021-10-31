package dp.mcm.problems;

import java.util.Arrays;
// https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/\

public class MatrixChainMultiplication {
	private static int mcm(int[] arr, int i, int j, int[][] dp) {
		if (i >= j) {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int min = Integer.MAX_VALUE;

		for (int k = i; k < j; k++) {
			int curr = mcm(arr, i, k, dp) + mcm(arr, k + 1, j, dp) + (arr[i - 1] * arr[k] * arr[j]);
			min = Math.min(curr, min);
		}
		return dp[i][j] = min;
	}

	public static void main(String[] args) {
		// code here
		int arr[] = new int[] { 1, 2, 3, 4, 3 };
		int N = arr.length;
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(mcm(arr, 1, N - 1, dp));// Output-30

	}

}
