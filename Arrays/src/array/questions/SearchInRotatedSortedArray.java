package array.questions;

// [5,1,3]
// 5

// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/

public class SearchInRotatedSortedArray {
	private int findPivot(int[] nums, int low, int high) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (mid + 1 < nums.length && (nums[mid] > nums[mid + 1])) {
				return mid;
			}
			if (mid - 1 > 0 && nums[mid] < nums[mid - 1]) {
				return mid - 1;
			} else if (nums[mid] < nums[0]) {
				return findPivot(nums, low, mid - 1);
			}
			return findPivot(nums, mid + 1, high);
		}
		return -1;
	}

	private int binarySearch(int[] nums, int low, int high, int target) {
		if (low > high) {
			return -1;
		}
		int mid = low + (high - low) / 2;
		if (nums[mid] == target) {
			return mid;
		}
		if (target < nums[mid]) {
			return binarySearch(nums, low, mid - 1, target);
		}
		return binarySearch(nums, mid + 1, high, target);
	}

	public int search(int[] nums, int target) {
		int n = nums.length;
		int pivot = findPivot(nums, 0, n - 1);
		if (pivot == -1) {
			return binarySearch(nums, 0, n - 1, target);
		}
		if (target == nums[pivot]) {
			return pivot;
		}

		else if (target < nums[0]) {
			return binarySearch(nums, pivot + 1, n - 1, target);
		}
		return binarySearch(nums, 0, pivot - 1, target);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
