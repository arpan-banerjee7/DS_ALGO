package sorting.alogrithims;

import java.util.Arrays;

/*
 * used in data streams
 * 1) we do not know future data
 * 2) always keep the array sorted
 * 
 * eg. Find median in data stream
 */

/*
 * The idea is that insertion sort works very fast on nearly-sorted arrays, while for a 
 * straightforward implementation of quick sort sorted input leads to the worst-case behavior.
 * Therefore the combined algorithm first applies a quicksort-like algorithm to partially sort 
 * the input, and then finished off with a call to insertion sort.
 */

/*
 * TC- WC- o(n^2)
 *     BC- o(1)- when the stream is sorted(just compare with the last element)
 */
public class InsertionSort {
	// Java program for implementation of Insertion Sort
	/* Function to sort array using insertion sort */
	void sort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && arr[j] > key) {// for descending change here
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");

		System.out.println();
	}

	// Driver Code
	public void sort_binarySearch(int array[]) {
		for (int i = 1; i < array.length; i++) {
			int x = array[i];

			// Find location to insert
			// using binary search
			int j = Math.abs(Arrays.binarySearch(array, 0, i, x) + 1);

			// Shifting array to one
			// location right
			System.arraycopy(array, j, array, j + 1, i - j);

			// Placing element at its
			// correct location
			array[j] = x;
		}
	}

	// Driver method
	public static void main(String args[]) {
		int arr[] = { 12, 11, 13, 5, 6 };

		// duplicates

		int arr1[] = { 12, 5, 12, 5, 6, 11 };

		InsertionSort ob = new InsertionSort();
		ob.sort(arr);
		printArray(arr);

		ob.sort(arr1);
		printArray(arr1);
		Arrays.binarySearch(arr1, 0);

		// Java Program implementing
		// binary insertion sort

		final int[] arr2 = { 12, 11, 13, 5, 6 };
		ob.sort_binarySearch(arr2);
		for (int i = 0; i < arr2.length; i++)
			System.out.print(arr2[i] + " ");

	}
}
