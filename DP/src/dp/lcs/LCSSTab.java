package dp.lcs;

public class LCSSTab {
	private static int findLcss(int[] a, int[] b) {
		int max = 0;
		int l1 = a.length;
		int l2 = b.length;
		int[][] dp = new int[l1 + 1][l2 + 1];
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				if (a[i - 1] == b[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3, 2, 1 };
		int[] nums2 = { 3, 2, 1, 4, 7 };
		System.out.println(findLcss(nums1, nums2)); // Output-3
	}

}
