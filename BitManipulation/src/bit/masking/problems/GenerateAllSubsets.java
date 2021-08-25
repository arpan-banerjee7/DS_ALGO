package bit.masking.problems;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllSubsets {

	// TC O(2^nlogn)
	// using bit manipulation
	private static List<List<Integer>> subsets(int[] nums) {
		int n = nums.length;
		
		// total no of sequences 2^n --- 0 to 2^n-1
		List<List<Integer>> res = new ArrayList<>();

		// generate all possible combinations of 0 and 1
		// 1 means include corresponding array element in the subset
		for (int mask = 0; mask < (1 << n); mask++) {
			List<Integer> currList = new ArrayList<>();
			// check which places have 1 = check i th set bit
			for (int j = 0; j < n; j++) {
				if ((mask & (1 << j)) > 0) {
					currList.add(nums[j]);
				}
			}
			res.add(currList);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] n = { 1, 2, 3 };
		System.out.println(subsets(n));

	}

}
