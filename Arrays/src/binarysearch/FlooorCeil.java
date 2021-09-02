package binarysearch;

// https://www.geeksforgeeks.org/ceiling-in-a-sorted-array/
// https://leetcode.com/problems/find-smallest-letter-greater-than-target/

// TC-- o(logn)
// Space-- o(1)
public class FlooorCeil {

	private static int findCeil(int[] arr, int low, int high, int target) {
		if (low > high) {
			return low < arr.length - 1 ? arr[low] : low; // --> ceil
			// return high >=0 ? arr[high] : high; //--->floor
			// return arr[low%arr.length]; //--> for leetcode question
		}
		int mid = low + (high - low) / 2;

		if (arr[mid] == target) {
			return arr[mid];
		}

		if (target < arr[mid]) {
			return findCeil(arr, low, mid - 1, target);
		} else {
			return findCeil(arr, mid + 1, high, target);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 8, 10, 10, 12, 19 };
		int target = 11;
		System.out.println(findCeil(arr, 0, arr.length - 1, target));

	}

}
