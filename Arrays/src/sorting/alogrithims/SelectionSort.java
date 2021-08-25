package sorting.alogrithims;

/*
 * The good thing about selection sort is it never makes more than O(n) swaps and can be useful 
 * when memory write is a costly operation. 
 * 
 * Does not make use of the property that the input can be sorted. Any input performs same number of operations.
 */

// TC - WC/BC/Avg Case -o(n^2)
public class SelectionSort {

	// Java program for implementation of Selection Sort
	void sort(int arr[]) {
		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[min_idx])
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
	}

	// Recursive implementation
	private void recursiveSelectionSort(int[] arr, int i) {
		if (i == arr.length - 1) {
			return;
		}
		int min_idx = i;
		for (int j = i + 1; j < arr.length; j++) {
			if (arr[j] < arr[min_idx]) { // for descending change here
				min_idx = j;
			}
		}
		// Swap the found minimum element with the first
		// element
		int temp = arr[min_idx];
		arr[min_idx] = arr[i];
		arr[i] = temp;

		recursiveSelectionSort(arr, i + 1);

	}

	// Prints the array
	void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver code to test above
	public static void main(String args[]) {
		SelectionSort ob = new SelectionSort();
		int arr[] = { 64, 25, 12, 22, 11 };

		// duplicates
		int arr1[] = { 4, 25, 25, 12, 22, 12 };

		int[] recurArray = { 34, 67, 8, 12, 23, 8, 3 };

		ob.sort(arr);
		System.out.println("Sorted array");
		ob.printArray(arr);
		ob.sort(arr1);
		System.out.println("Sorted array");
		ob.printArray(arr1);
		ob.recursiveSelectionSort(recurArray, 0);
		System.out.println("Recursively Sorted array");
		ob.printArray(recurArray);

	}

}
