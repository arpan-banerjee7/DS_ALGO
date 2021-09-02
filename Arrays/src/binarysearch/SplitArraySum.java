package binarysearch;

// https://leetcode.com/problems/split-array-largest-sum/
// https://www.geeksforgeeks.org/painters-partition-problem-set-2/
// https://www.geeksforgeeks.org/allocate-minimum-number-pages/

// TC--  O(N * log (sum (arr[]))  
public class SplitArraySum {
	public static int splitArray(int[] nums, int m) {
		int start = 0;
		int end = 0;

		for (int i = 0; i < nums.length; i++) {
			start = Math.max(start, nums[i]); // in the end of the loop this will contain the max item fro the array
			end += nums[i];
		}

		// binary search
		while (start < end) {
			// try for the middle as potential ans
			int mid = start + (end - start) / 2;

			// calculate how many pieces you can divide this in with this max sum
			int sum = 0;
			int pieces = 1;
			for (int num : nums) {
				if (sum + num > mid) {
					// you cannot add this in this subarray, make new one
					// say you add this num in new subarray, then sum = num
					sum = num;
					pieces++;
				} else {
					sum += num;
				}
			}

			if (pieces > m) {
				start = mid + 1;
			} else {
				end = mid;
			}

		}
		return end; // here start == end
	}

	public int splitArray2(int[] nums, int m) {
		int start = 0;
		int end = 0;

		for (int i = 0; i < nums.length; i++) {
			start = Math.max(start, nums[i]); // in the end of the loop this will contain the max item fro the array
			end += nums[i];
		}
		int ans = -1;
		// binary search
		while (start <= end) {
			// try for the middle as potential ans
			int mid = start + (end - start) / 2;

			// calculate how many pieces you can divide this in with this max sum
			int sum = 0;
			int pieces = 1;

			for (int num : nums) {
				if (sum + num > mid) {
					// you cannot add this in this subarray, make new one
					// say you add this num in new subarray, then sum = num
					sum = num;
					pieces++;
				} else {
					sum += num;
				}
			}
			if (pieces <= m) {
				ans = mid;
				end = mid - 1;
			}

			else {
				start = mid + 1;
			}

		}
		return ans; // here start == end
	}

	public int splitArray_best(int[] nums, int m) {
		int start = 0;
		int end = 0;
		int res = -1;

		for (int i = 0; i < nums.length; i++) {
			start = Math.max(start, nums[i]); // maximum element in the array
			end += nums[i]; // sum of all elements
		}

		// binary search
		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (isValid(nums, nums.length, m, mid) == true) {
				res = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return res;
	}

	private static boolean isValid(int[] nums, int l, int m, int mx) {
		int sum = 0;
		int pieces = 1;

		for (int i = 0; i < l; i++) {
			sum += nums[i];

			if (sum > mx) {
				sum = nums[i];
				pieces++;
			}
			if (pieces > m) {
				return false;
			}
		}
		return true; // <= both are possible answers--> if we have more pieces
		// we can break that down logically into more number of pieces, but our code
		// never actually breaks that to required number of pieces all the time.
	}

	public static void main(String[] args) {
		int[] arr = { 7, 2, 5, 10, 8 };
		int m = 2;
		System.out.println(splitArray(arr, m));
	}

}
