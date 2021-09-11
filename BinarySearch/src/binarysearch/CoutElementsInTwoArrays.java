package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.geeksforgeeks.org/element-1st-array-count-elements-less-equal-2nd-array/

//TC--- O(mlogn + nlogn)
public class CoutElementsInTwoArrays {

	private static int firstOccurance_floor(int[] arr, int low, int high, int target) {
		int ans = -1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (target == arr[mid]) {
				// potential ans-- need to find the rightmost occurance of it
				ans = mid;
				low = mid + 1;
			} else if (target < arr[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		if (ans == -1) {
			return high;
		}
		return ans;

	}

	public static ArrayList<Integer> countEleLessThanOrEqual(int arr1[], int arr2[], int m, int n) {
		// add your code here
		ArrayList<Integer> res = new ArrayList<>();
		Arrays.sort(arr2);
		for (int num : arr1) {
			res.add(firstOccurance_floor(arr2, 0, n - 1, num) + 1);
		}

		return res;
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 7, 9 };
		int[] B = { 0, 1, 2, 1, 1, 4 };
		List<Integer> res = countEleLessThanOrEqual(A, B, A.length, B.length);
		res.forEach(e -> System.out.print(e + " "));

	}

}
