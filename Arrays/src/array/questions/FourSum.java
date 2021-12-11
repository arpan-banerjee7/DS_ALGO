package array.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Optimization, replace the last two loops with two pointer technique
// TC-o(n^3)logn

/*https://www.youtube.com/watch?v=8ViERnSgPKs
	 // using 4 loops and HashSet - TC- o(n^4(logn))
	 // can be optimized by sorting and putting break points
	    
	 // 2nd level of optimization, replace the last two loops wih two
	 // pointe technique - TC- o(n^3(logn))
			
Two solutions- 1. With 4 nested loops 2.Replace last two loops with Two pointer technique*/

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
						start++;
						// to avoid duplicates
						while (start < end && nums[start] == nums[start - 1]) {
							start++;
						}
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
		/*
		 * Input: nums = [1,0,-1,0,-2,2], target = 0 Output:
		 * [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
		 */
	}

}
