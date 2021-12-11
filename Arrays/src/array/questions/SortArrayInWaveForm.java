package array.questions;

import java.util.Arrays;

/*Sort an array in wave form
https://www.geeksforgeeks.org/sort-array-wave-form-2/
https://practice.geeksforgeeks.org/problems/wave-array-1587115621/1#
*/
public class SortArrayInWaveForm {
	// Java implementation of naive method for sorting
	// an array in wave form.
	// A utility method to swap two numbers.
	void swap(int arr[], int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	// This function sorts arr[0..n-1] in wave form, i.e.,
	// arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4]..
	void sortInWave(int arr[], int n) {
		// Sort the input array
		Arrays.sort(arr);

		// Swap adjacent elements
		for (int i = 0; i < n - 1; i += 2)
			swap(arr, i, i + 1);
	}

	// A O(n) Java program to sort an input array in wave form
	// A utility method to swap two numbers.
	void swap1(int arr[], int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	// This function sorts arr[0..n-1] in wave form, i.e.,
	// arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4]....
	void sortInWave1(int arr[], int n) {
		// Traverse all even elements
		for (int i = 0; i < n; i += 2) {
			// If current even element is smaller
			// than previous
			if (i > 0 && arr[i - 1] > arr[i])
				swap(arr, i - 1, i);

			// If current even element is smaller
			// than next
			if (i < n - 1 && arr[i] < arr[i + 1])
				swap(arr, i, i + 1);
		}
	}


	// Driver method
	public static void main(String args[]) {
		SortArrayInWaveForm ob = new SortArrayInWaveForm();
		int arr[] = { 10, 90, 49, 2, 1, 5, 23 };
		int n = arr.length;
		ob.sortInWave(arr, n);
		for (int i : arr)
			System.out.print(i + " ");
	}

}
