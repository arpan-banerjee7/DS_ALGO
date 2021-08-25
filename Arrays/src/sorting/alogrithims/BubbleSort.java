package sorting.alogrithims;

/*
 * Uses the property that the input is sorted, if the input is fully sorted then TC- o(n), flag will be false if no swaps are done.
 * 
 */

// TC- WC- o(n^2)
//     BC- o(n)-- when array is sorted, comapare with all adjacent elements, detect no
//     swap, so no break out of the loop
//     Avg case- o(n^2)
// Auxiliary Space: O(1)
public class BubbleSort {

	// Optimized java implementation
	// of Bubble sort

	// An optimized version of Bubble Sort
	static void bubbleSort(int arr[], int n) {
		int i, j, temp;
		boolean swapped;
		for (i = 0; i < n - 1; i++) {
			swapped = false;
			for (j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) { // for descending change here
					// swap arr[j] and arr[j+1]
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}

			// IF no two elements were
			// swapped by inner loop, then break
			if (swapped == false)
				break;
		}
	}

	// Function to print an array
	static void printArray(int arr[], int size) {
		int i;
		for (i = 0; i < size; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[]) {
		int arr[] = { 64, 34, 25, 12, 22, 11, 90 };
		// duplicates
		int arr1[] = { 4, 34, 34, 12, 22, 11, 22 };
		int n = arr.length;
		bubbleSort(arr, n);
		System.out.println("Sorted array: ");
		printArray(arr, n);

		int n1 = arr1.length;
		bubbleSort(arr1, n1);
		System.out.println("Sorted array: ");
		printArray(arr1, n1);
	}
	
	// recursive bubble sort
	private void recursiveBubbleSort(int arr[], int n){
	    // Base case
	    if (n == 1)
	    return;

	    // One pass of bubble sort. After
	    // this pass, the largest element
	    // is moved (or bubbled) to end.
	    for(int i=0; i<n-1; i++){
	        if(arr[i] > arr[i+1])
	        {
	         //swap(arr[i], arr[i+1]);
	        }
	    }

	    // recursion for remaining elements in array
	    recursiveBubbleSort(arr, n-1);
	}
}


