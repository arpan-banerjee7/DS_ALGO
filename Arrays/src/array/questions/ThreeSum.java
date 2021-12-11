package array.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Diff bw arrays.as list -- https://stackoverflow.com/questions/16748030/difference-between-arrays-aslistarray-and-new-arraylistintegerarrays-aslist

https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java#:~:text=the%20other%20answers-,expect,-.

Imp Test cases--
[-1,0,1,2,-1,-4]-- ans [[-1,-1,2],[-1,0,1]]
[-2,0,0,2,2]-- ans [[-2,0,2]]

https://www.youtube.com/watch?v=jzZsG8n2R9A   Neetcode
*/
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < n - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int low = i + 1, high = n - 1;
			while (low < high) {
				int threeSum = nums[i] + nums[low] + nums[high];
				if (threeSum == 0) {
					res.add(Arrays.asList(nums[i], nums[low], nums[high]));
					// [-2,0,0,2,2]
					low++;
					while (low < high && nums[low] == nums[low - 1]) {
						low++;
					}
				} else if (threeSum > 0) {
					high--;
				} else {
					low++;
				}
			}
		}
		return res;
	}

	// TLE
	public List<List<Integer>> threeSum1(int[] nums) {
		int n = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < n - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1; j < n - 1; j++) {
				if (j - 1 > i && nums[j] == nums[j - 1])
					continue;
				for (int k = j + 1; k < n; k++) {
					if (k - 1 > j && nums[k] == nums[k - 1])
						continue;
					Integer[] arr = { nums[i], nums[j], nums[k] };
					List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(arr));
					if ((nums[i] + nums[j] + nums[k] == 0)) {
						res.add(list1);
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]]
		 */

	}

}
