package array.questions;

/*
https://practice.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1#
https://www.youtube.com/watch?v=hVl2b3bLzBw
https://www.techiedelight.com/inplace-merge-two-sorted-arrays/
*/

public class MergeTwoSortedArraysWithoutExtraSpace {
	// Function to merge the arrays.
	public static void merge(long arr1[], long arr2[], int n, int m) {
		// code here
		int p1 = 0;
		while (p1 < n) {
			if (arr1[p1] < arr2[0]) {
				p1++;
			} else if (arr1[p1] > arr2[0]) {
				long temp = arr1[p1];
				arr1[p1] = arr2[0];
				arr2[0] = temp;

				long key = arr2[0];
				// only arr2[0] is breaking the sorted order
				// so we will doonly one pass of the insertion sort algo
				// and put arr2[0] in its correct place
				int k = 0;
				for (k = 1; k < m && arr2[k] < key; k++) {
					arr2[k - 1] = arr2[k];// shift elements towards left to
					// make space for the larger element at the right
				}
				// put the key at its correct pos
				arr2[k - 1] = key;
			}
		}
	}

	public static void main(String[] args) {
		/* Input: n = 4, arr1[] = [1 3 5 7] 
				m = 5, arr2[] = [0 2 6 8 9]
				Output: 
				arr1[] = [0 1 2 3]
				arr2[] = [5 6 7 8 9]
				Explanation:
				After merging the two 
				non-decreasing arrays, we get, 
				0 1 2 3 5 6 7 8 9.*/

	}

}
