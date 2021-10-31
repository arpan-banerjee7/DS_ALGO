package dp.lcs.problems;
// https://www.youtube.com/watch?v=Lj90FqNCIJE
// https://leetcode.com/problems/maximum-length-of-repeated-subarray/
// https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/413054/ALL-4-ways-Recursion-greater-Top-down-greaterBottom-Up-including-VIDEO-TUTORIAL

public class LCSSMem {
	// Method2A1()- recursive solution with memoization (Top-down approach caching
	// on method level)
	public static int findLcss(int[] X, int[] Y, int m, int n, int lcsCount, Integer[][][] dp) {
		if (m <= 0 || n <= 0)
			return lcsCount;

		if (dp[m][n][lcsCount] != null)
			return dp[m][n][lcsCount];

		int lcsCount1 = lcsCount;
		if (X[m - 1] == Y[n - 1])
			lcsCount1 = findLcss(X, Y, m - 1, n - 1, lcsCount + 1, dp);

		int lcsCount2 = findLcss(X, Y, m, n - 1, 0, dp);
		int lcsCount3 = findLcss(X, Y, m - 1, n, 0, dp);

		return dp[m][n][lcsCount] = Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));
	}

	public static void main(String[] args) {
		int[] nums1 = {1,2,3,2,1};
		int[] nums2 = {3,2,1,4,7};
		int n1 = nums1.length;
		int n2 = nums2.length;
		int count = Math.min(n1, n2);
		Integer[][][] dp = new Integer[nums1.length + 1][nums2.length + 1][count + 1];
		System.out.println(findLcss(nums1, nums2, n1, n2, 0, dp)); // Output-3
	}

}
