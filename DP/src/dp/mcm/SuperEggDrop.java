package dp.mcm;

import java.util.Arrays;
// https://leetcode.com/problems/super-egg-drop/
// https://www.youtube.com/watch?v=S49zeUjeUL0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=42
// https://leetcode.com/problems/super-egg-drop/discuss/1209313/Super-Egg-Drop-CPP-Recursive-Memoization-Optimised-Memoization-Memoization-%2B-Binary-Search
// LC test case pas- apply binary search

public class SuperEggDrop {

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

		// there is no critical floor, from whichever floor the egg does not break,
		// in the worst case with min no of attempts is the critical floor
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

	public int superEggDrop(int k, int n) {
		dp = new int[k + 1][n + 1];
		for (int i = 0; i <= k; i++) {
			Arrays.fill(dp[i], -1);
		}
		return minTrials(k, n);
	}

	public static void main(String[] args) {
		int  k = 2, n = 6;
		SuperEggDrop eggDrop=new SuperEggDrop();
		System.out.println(eggDrop.superEggDrop(k, n));// Output-3
	}
}
