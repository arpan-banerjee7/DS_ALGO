package array.questions.binarysearch;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
public class FirstLastOccurance {

	// naive TC--o(n)+o(logn)=o(n)
	private static int[] firstLast1(int[] nums, int low, int high, int target) {
		if (low > high) {
			return new int[] { -1, -1 };
		}
		int mid = low + (high - low) / 2;

		if (target == nums[mid]) {
			int[] res = new int[2];
			for (int i = mid; i < nums.length; i++) {
				if (nums[i] == target) {
					res[1] = i;
				} else {
					break;
				}
			}
			for (int i = mid; i >= 0; i--) {
				if (nums[i] == target) {
					res[0] = i;
				} else {
					break;
				}
			}
			return res;
		}

		if (target < nums[mid]) {
			return firstLast1(nums, low, mid - 1, target);
		} else {
			return firstLast1(nums, mid + 1, high, target);
		}
	}

	// TC-- o(logn)
	private static int firstLast2(int[] nums, int target, boolean findStartIndex) {
		int low = 0;
		int high = nums.length - 1;
		int ans = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (target == nums[mid]) {
				// found potential ans
				ans = mid;
				// look in the left side for first occurrence
				if (findStartIndex) {
					high = mid - 1;
					// look in the left side for first occurrence
				} else {
					low = mid + 1;
				}

			} else if (target < nums[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 7, 7, 8, 8, 10 };
		int target = 8;
		int[] res1 = firstLast1(arr, 0, arr.length - 1, target);
		for (int i : res1) {
			System.out.print(i + " ");
		}
		System.out.println();
		int[] res2 = { -1, -1 };
		res2[0] = firstLast2(arr, target, true);
		if (res2[0] != -1) {
			res2[1] = firstLast2(arr, target, false);
		}
		for (int i : res2) {
			System.out.print(i + " ");
		}
	}

}
