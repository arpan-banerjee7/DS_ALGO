package microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 18. 4Sum
// https://leetcode.com/problems/4sum/

//Optimization, replace the last two loops with two pointer technique
// TC-o(n^3)logn

public class FourSum {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		// very imp step
		Arrays.sort(nums);
		for (int i = 0; i < n - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1; j < n - 2; j++) {
				if (j - 1 > i && nums[j] == nums[j - 1])
					continue;
				int start = j + 1, end = n - 1;
				while (start < end) {
					if (nums[i] + nums[j] + nums[start] + nums[end] == target) {
						res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
						// to avoid duplicates
						while (start < end && nums[start] == nums[start + 1])
							start++;
						while (start < end && nums[end] == nums[end - 1])
							end--;
						start++;
						end--;
					} else if (nums[i] + nums[j] + nums[start] + nums[end] < target) {
						start++;
					} else {
						end--;
					}
				}

			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
