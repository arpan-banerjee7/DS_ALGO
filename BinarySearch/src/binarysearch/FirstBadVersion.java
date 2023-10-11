package binarysearch;
// 278. First Bad Version

// https://leetcode.com/problems/first-bad-version/description/

public class FirstBadVersion {

	/*
	 * The isBadVersion API is defined in the parent class VersionControl. boolean
	 * isBadVersion(int version);
	 */

	public int firstBadVersion(int n) {
		int low = 0;
		int high = n;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (firstBadVersion(mid)) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
	
	public static void main(String[] args) {
		/*
		 * Input: n = 5, bad = 4 Output: 4 Explanation: call isBadVersion(3) -> false
		 * call isBadVersion(5) -> true call isBadVersion(4) -> true Then 4 is the first
		 * bad version.
		 */
	}
}
