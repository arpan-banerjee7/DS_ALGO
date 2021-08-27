package mathalgo.problems;

// https://www.geeksforgeeks.org/program-nth-catalan-number/
// https://www.youtube.com/watch?v=CMaZ69P1bAc
// https://www.youtube.com/watch?v=0pTN0qzpt-Y
// https://www.geeksforgeeks.org/applications-of-catalan-numbers/

/*
 * Catalan number questions
 * 1) https://www.geeksforgeeks.org/find-number-valid-parentheses-expressions-given-length/ Cn/2
 * 2) How many binary trees are there with n-internal nodes? Cn
 * 3) N-non intersecting chords(2n points/ even pair-- Cn) / (n even number of points/ not pairs-- Cn/2)
 * 4) Number of ways in which n+1 factors can be completely paranthesised-- Cn
 * 5) https://www.geeksforgeeks.org/number-of-unique-bst-with-a-given-key-dynamic-programming/-- Cn
 * https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/-- construct the BSTs
 * 6) The number of paths with 2n steps on a rectangular grid from bottom left, i.e., (n-1, 0) to top 
 * right (0, n-1) that do not cross above the main diagonal-- Cn
 * 7) https://www.geeksforgeeks.org/enumeration-of-binary-trees/
 */
public class CatalanNumbers_recursion {

	// TC-- exponential
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

	// A dynamic programming based function to find nth
	// Catalan number
	// TC-- o(n^2)
	static int catalanDP(int n) {
		// Table to store results of subproblems
		int catalan[] = new int[n + 2];

		// Initialize first two values in table
		catalan[0] = 1;
		catalan[1] = 1;

		// Fill entries in catalan[]
		// using recursive formula
		for (int i = 2; i <= n; i++) {
			catalan[i] = 0;
			for (int j = 0; j < i; j++) {
				catalan[i] += catalan[j] * catalan[i - j - 1];
			}
		}

		// Return last entry
		return catalan[n];
	}

	// Returns value of Binomial Coefficient C(n, k)
	static long binomialCoeff(int n, int k) {
		long res = 1;

		// Since C(n, k) = C(n, n-k)
		if (k > n - k) {
			k = n - k;
		}

		// Calculate value of [n*(n-1)*---*(n-k+1)] /
		// [k*(k-1)*---*1]
		for (int i = 0; i < k; ++i) {
			res *= (n - i);
			res /= (i + 1);
		}

		return res;
	}

	// A Binomial coefficient based function
	// TC-- O(n)
	static long catalan_binomail(int n) {
		// Calculate value of 2nCn
		long c = binomialCoeff(2 * n, n);

		// return 2nCn/(n+1)
		return c / (n + 1);
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

		System.out.println();

		for (int i = 0; i < n; i++) {
			System.out.print(catalanDP(i) + " ");
		}

		System.out.println();

		for (int i = 0; i < n; i++) {
			System.out.print(catalan_binomail(i) + " ");
		}

	}
}
