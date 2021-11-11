package dp.lis;
// https://leetcode.com/problems/longest-increasing-subsequence/

// https://www.youtube.com/watch?v=mouCn3CFpgg Techdose 
// https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/  

// TC: O(n^2)  SC: O(n)
public class LongestIncreasingSubsequence {
	public static int LIS(int[] arr, int i, int n, int prev) {
		// Base case: nothing is remaining
		if (i == n) {
			return 0;
		}

		// case 1: exclude the current element and process the
		// remaining elements
		int excl = LIS(arr, i + 1, n, prev);

		// case 2: include the current element if it is greater
		// than the previous element in LIS
		int incl = 0;
		if (arr[i] > prev) {
			incl = 1 + LIS(arr, i + 1, n, arr[i]);
		}

		// return the maximum of the above two choices
		return Integer.max(incl, excl);
	}

	public static void main(String[] args) {
		int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(LIS(nums, 0, nums.length, Integer.MIN_VALUE));// Output: 4
	}

}
