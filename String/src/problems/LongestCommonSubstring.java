package problems;

// 718. Maximum Length of Repeated Subarray -DP easier than top down recursion
// https://leetcode.com/problems/maximum-length-of-repeated-subarray/

/*https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/413054/ALL-4-ways-Recursion-greater-Top-down-greaterBottom-Up-including-VIDEO-TUTORIAL

https://www.youtube.com/watch?v=UZRkpGk943Q

[0,1,0,0]
[0,0,1,0]*/

public class LongestCommonSubstring {

	Integer dp[][][];

	private int findLCSS(int[] nums1, int[] nums2, int n1, int n2, int lcsCount) {
		if (n1 == 0 || n2 == 0) {
			return lcsCount;
		}
		if (dp[n1][n2][lcsCount] != null) {
			return dp[n1][n2][lcsCount];
		}

		int lcsCount1 = lcsCount; // very imp
		if (nums1[n1 - 1] == nums2[n2 - 1]) {
			lcsCount1 = findLCSS(nums1, nums2, n1 - 1, n2 - 1, lcsCount + 1);
		}
		int lcsCount2 = findLCSS(nums1, nums2, n1 - 1, n2, 0);
		int lcsCount3 = findLCSS(nums1, nums2, n1, n2 - 1, 0);

		return dp[n1][n2][lcsCount] = Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));

	}


	public int findLength(int[] nums1, int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		int[][] dp = new int[n1 + 1][n2 + 1];

		int max = 0;
		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
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
		// TODO Auto-generated method stub

	}

}
