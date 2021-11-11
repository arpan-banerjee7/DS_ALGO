package dp.lis;

import java.util.Arrays;
// https://leetcode.com/problems/number-of-longest-increasing-subsequence/
// https://www.youtube.com/watch?v=WcQ_y9TWhhw

/*
 [1,2,4,3,5,4,7,2]

 count[i]+=count[j];
 nums  1 2 4 3 5 4 7 2
 lis        1 2 3 3 4 4 4 5
 count   1 1 1 1 2 1 3 1
 */
public class NumberofLongestIncreasingSubsequence {

	public static int findNumberOfLIS(int[] nums) {
		int n = nums.length;
		int[] lis = new int[n];
		int[] count = new int[n];

		Arrays.fill(lis, 1);
		Arrays.fill(count, 1);

		// iitialize with 1 as worst case each element can
		// be an increasing subsequence
		int max = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					if (lis[i] <= lis[j]) {
						lis[i] = 1 + lis[j];
						count[i] = count[j];
					} else if (lis[i] == lis[j] + 1) {
						count[i] += count[j];// [1,2,4,3,5,4,7,2]
					}
					max = Math.max(max, lis[i]);
				}
			}
		}

		// sum of all the counts of max lis values
		int ans = 0;
		for (int k = 0; k < n; k++) {
			if (lis[k] == max) {
				ans += count[k];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5, 4, 7 };
		System.out.println(findNumberOfLIS(nums));// Output: 2
	}

}
