package array.questions.binarysearch;

// https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
// https://leetcode.com/problems/search-in-rotated-sorted-array/

// TC-- o(logn)
// Space-- 0(1)
public class SearchInRotatedSortedArray {

	public static int search(int[] nums, int target) {
		int low = 0;
		int high = nums.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			// Now, mid can lie either in part 1 or in part 2
			// [4,5,6,7,0,1,2] --> [4,5,6,7]-p1 [0,1,2]-p2
			// depending upon mid, nums[0] and target we will decide weather to do a
			// binary search on p1 or p2
			// think of the part of the array after pivot while deciding if mid
			// lies in p1 or p2
			if (nums[0] > nums[mid]) {
				// mid lies in part 2
				if (nums[0] <= target || target < nums[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}

			} else {
				// mid lies in part 1
				if (nums[0] > target || target > nums[mid]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		int arr[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };
		int n = 9;
		System.out.println(search(arr, n));

	}

}
