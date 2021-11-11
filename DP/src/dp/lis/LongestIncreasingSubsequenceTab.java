package dp.lis;
//https://leetcode.com/problems/longest-increasing-subsequence/

//https://www.youtube.com/watch?v=mouCn3CFpgg Techdose 
//https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/  

//TC: O(n^2)  SC: O(n)
public class LongestIncreasingSubsequenceTab {
	public static int lengthOfLIS(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int[] r = new int[nums.length];
			int k = 0;
			r[k++] = nums[i];
			int com = nums[i];
			for (int j = i; j < nums.length - 1; j++) {

				if (nums[j + 1] > com) {
					com = nums[j + 1];
					r[k++] = nums[j + 1];
				}
			}
			max = Math.max(k, max);

		}
		if (max == Integer.MIN_VALUE) {
			return -1;
		} else {
			return max;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(lengthOfLIS(nums));// Output: 4
	}

}
