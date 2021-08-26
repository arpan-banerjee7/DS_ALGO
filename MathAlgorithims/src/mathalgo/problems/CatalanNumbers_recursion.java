package mathalgo.problems;

public class CatalanNumbers_recursion {

	private static int findCatalan(int n) {
		int res = 0;
		if (n <= 1) {
			return 1;
		}
		for (int i = 0; i < n; i++) {
			res = res + findCatalan(i) * findCatalan(n - i - 1);
		}
		return res;
	}

	private static int findCatalan_memoized(int n, int[] dp) {
		int res = 0;
		if (n <= 1) {
			return 1;
		}
		if (dp[n] != 0) {
			return dp[n];
		}
		for (int i = 0; i < n; i++) {
			res = res + findCatalan_memoized(i, dp) * findCatalan_memoized(n - i - 1, dp);
		}
		dp[n] = res;
		return dp[n];
	}

	public static void main(String[] args) {
		int n = 10;
		int[] dp = new int[n];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 0; i < n; i++) {
			System.out.print(findCatalan(i) + " ");
		}

		System.out.println();

		System.out.print(findCatalan_memoized(4, dp) + " ");

	}
}
