package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/4sum/
// https://www.youtube.com/watch?v=8ViERnSgPKs 

public class FourSum {

	// using 4 loops and HashSet - TC- o(n^4(logn))
	// can be optimized by sorting and putting break points

	// 2nd level of optimization, replace the last two loops wih two
	// pointer technique - TC- o(n^3(logn))
	public static List<List<Integer>> fourSum(int[] nums, int target) {

		int n = nums.length;
		int start = 0;
		int end = 0;
		int sum = 0;
		Set<List<Integer>> seen = new HashSet<>();
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();

		Arrays.sort(nums);

		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				start = j + 1;
				end = n - 1;
				while (start < end) {
					sum = nums[i] + nums[j] + nums[start] + nums[end];
					if (sum == target) {
						temp = Arrays.asList(nums[i], nums[j], nums[start], nums[end]);
						seen.add(temp);
					}
					if (sum < target) {
						start++;
					} else {
						end--;
					}
				}
			}

		}
		for (List<Integer> l : seen) {
			res.add(l);
		}
		return res;
	}

	public static void main(String[] args) {
		int arr[] = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		System.out.println(fourSum(arr, target));

	}

}
