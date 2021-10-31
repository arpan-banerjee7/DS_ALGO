package dp.bitmasking;
// https://leetcode.com/problems/matchsticks-to-square/

// TC- o(n*2^n)
public class MatchSticksToSquare {
	public static boolean makesquare(int[] matchsticks) {
		int n = matchsticks.length;
		int max = Integer.MIN_VALUE;

		// find sum of the array, to find the target sum(i.e what will be the sum of
		// each partition)
		// and also check for invalid conditions
		int sum = 0;
		for (int i : matchsticks) {
			sum += i;
			max = Math.max(max, i);
		}
		// invalid cases
		if (sum % 4 != 0 || max > sum / 4) {
			return false;
		}

		int target = sum / 4;

		int[] dp = new int[1 << n];
		for (int i = 0; i < (1 << n); i++) {
			dp[i] = -1;
		}
		dp[0] = 0;
		// Try all combinations
		for (int mask = 0; mask < (1 << n); mask++) {
			if (dp[mask] == -1) {
				continue;
			}
			for (int j = 0; j < n; j++) {
				if (((mask) & (1 << j)) == 0 && dp[mask] + matchsticks[j] <= target) {
					dp[mask | (1 << j)] = (dp[mask] + matchsticks[j]) % target;
				}
			}
		}
		return dp[(1 << n) - 1] == 0 ? true : false;
	}

	public static void main(String[] args) {
		int[] matchsticks = { 1, 1, 2, 2, 2 };
		System.out.println(makesquare(matchsticks));

		/*
		 * Output: true Explanation: You can form a square with length 2, one side of
		 * the square came two sticks with length 1.
		 */

	}

}
