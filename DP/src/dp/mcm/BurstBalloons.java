package dp.mcm;

import java.util.Arrays;

// https://leetcode.com/problems/burst-balloons/
// https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
// intuition-- https://www.youtube.com/watch?v=uG_MtaCJIrM

public class BurstBalloons {
	int[][] dp;

	private int maxCoins(int[] arr, int i, int j) {
		if (i + 1 == j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int max = 0;
		for (int k = i + 1; k < j; k++) {
			// at any given window, max coins= coins gained from the ballon to burst(at
			// last)+
			// max coins by bursting left window(using all combinations, donw with the for
			// loop)+
			// max coins by bursting right window similarly
			int temp = arr[i] * arr[k] * arr[j] + maxCoins(arr, i, k) + maxCoins(arr, k, j);
			max = Math.max(max, temp);
		}

		return dp[i][j] = max;
	}

	public int maxCoins(int[] nums) {
		int[] arr = new int[nums.length + 2];
		int n = 1;
		for (int x : nums)
			if (x > 0)
				arr[n++] = x;

		arr[0] = arr[n++] = 1;
		int len = arr.length;
		dp = new int[len + 1][len + 1];

		for (int i = 0; i <= len; i++) {
			Arrays.fill(dp[i], -1);
		}
		return maxCoins(arr, 0, len - 1);
	}
	public static void main(String[] args) {
		int[] nums = {3,1,5,8};
		BurstBalloons bb=new BurstBalloons();
		System.out.println(bb.maxCoins(nums));// Output: 167
	}
}
