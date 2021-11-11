package dp.mcm;

import java.util.Arrays;

// https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/
// https://www.youtube.com/watch?v=jkygQmOiCCI&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=44 Aditya Verma

public class EggDropWith2EggsandNFloors {
	int[][] dp;

	private int minTrials(int e, int f) {
		if (f == 0 || f == 1) {
			return f;
		}
		if (e == 1) {
			return f;
		}
		if (dp[e][f] != -1) {
			return dp[e][f];
		}

		int min = Integer.MAX_VALUE;
		int low = 1;
		int high = f;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int left = minTrials(e - 1, mid - 1);
			int right = minTrials(e, f - mid);
			int temp = 1 + Math.max(left, right);
			if (left < right) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

			min = Math.min(min, temp);

		}
		return dp[e][f] = min;
	}

	private int minTrials1(int e, int f) {
		if (f == 0 || f == 1) {
			return f;
		}
		if (e == 1) {
			return f;
		}
		if (dp[e][f] != -1) {
			return dp[e][f];
		}
		int min = Integer.MAX_VALUE;
		for (int k = 1; k <= f; k++) {
			int temp = 1 + Math.max(minTrials1(e - 1, k - 1), minTrials1(e, f - k));
			min = Math.min(min, temp);
		}
		return dp[e][f] = min;
	}

	public int twoEggDrop(int n) {
		dp = new int[2 + 1][n + 1];
		for (int i = 0; i <= 2; i++) {
			Arrays.fill(dp[i], -1);
		}
		return minTrials(2, n);
	}

	public static void main(String[] args) {
		int n = 2;
		EggDropWith2EggsandNFloors obj = new EggDropWith2EggsandNFloors();
		System.out.println(obj.twoEggDrop(n));// Output: 2
	}

}
