package dp.lcs;
// 718. Maximum Length of Repeated Subarray
// https://leetcode.com/problems/maximum-length-of-repeated-subarray/

public class MaximumLengthRepeatedSubarray {
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
		int count = Math.min(n1, n2);
		dp = new Integer[n1 + 1][n2 + 1][count + 1];
		return findLCSS(nums1, nums2, n1, n2, 0);
	}

	public static void main(String[] args) {
		/*
		 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7] Output: 3 Explanation: The
		 * repeated subarray with maximum length is [3,2,1].
		 */

	}

}
