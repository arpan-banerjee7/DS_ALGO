package array.questions;

import java.util.Arrays;

// Count inversions-- gave me a hard time
//https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1#
//https://www.techiedelight.com/inversion-count-array/

public class CountInversions {
	// arr[]: Input Array
	// N : Size of the Array arr[]
	// Function to count inversions in the array.
	public static long merge(long[] arr, long[] aux, int low, int mid, int high) {
		int k = low, i = low, j = mid + 1;
		long inversionCount = 0;

		// while there are elements in the left and right runs
		while (i <= mid && j <= high) {
			if (arr[i] <= arr[j]) {
				aux[k++] = arr[i++];
			} else {
				aux[k++] = arr[j++];
				inversionCount += (mid - i + 1); // NOTE
			}
		}

		// copy remaining elements
		while (i <= mid) {
			aux[k++] = arr[i++];
		}

		/*
		 * no need to copy the second half (since the remaining items are already in
		 * their correct position in the temporary array)
		 */

		// copy back to the original array to reflect sorted order
		for (i = low; i <= high; i++) {
			arr[i] = aux[i];
		}

		return inversionCount;
	}

	// Sort array `arr[low…high]` using auxiliary array `aux`
	public static long mergesort(long[] arr, long[] aux, int low, int high) {
		// base case
		if (high <= low) { // if run size <= 1
			return 0;
		}

		// find midpoint
		int mid = (low + ((high - low) >> 1));
		long inversionCount = 0;

		// recursively split runs into two halves until run size <= 1,
		// then merges them and return up the call chain

		// split/merge left half
		inversionCount += mergesort(arr, aux, low, mid);

		// split/merge right half
		inversionCount += mergesort(arr, aux, mid + 1, high);

		// merge the two half runs
		inversionCount += merge(arr, aux, low, mid, high);

		return inversionCount;
	}

	static long inversionCount(long arr[], long N) {
		// Your Code Here
		long[] aux = Arrays.copyOf(arr, arr.length);
		long inversions = mergesort(arr, aux, 0, arr.length - 1);
		return inversions;

	}

	public static void main(String[] args) {
		/*
		 * Input: N = 5, arr[] = {2, 4, 1, 3, 5} Output: 3 Explanation: The sequence 2,
		 * 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
		 */

	}

}
