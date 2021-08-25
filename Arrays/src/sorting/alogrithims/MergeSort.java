package sorting.alogrithims;

/*Merge Sort is useful for sorting linked lists in O(nLogn) time. 
In the case of linked lists, the case is different mainly due to the 
difference in memory allocation of arrays and linked lists. Unlike arrays, 
linked list nodes may not be adjacent in memory. Unlike an array, in the linked list, 
we can insert items in the middle in O(1) extra space and O(1) time. Therefore, 
the merge operation of merge sort can be implemented without extra space for linked lists.
*/

/*
 * QuickSort is faster in practice, because its inner loop can be efficiently implemented on most architectures,
 * and in most real-world data. QuickSort can be implemented in different ways by changing the choice of pivot, 
 * so that the worst case rarely occurs for a given type of data. However, merge sort is generally considered 
 * better when data is huge and stored in external storage. 
 */

// TC - BC/WC/AVgC-- o(nlogn)
// space- o(n)
public class MergeSort {

	static void merge(int arr[], int l, int m, int r) {
		// Your code here
		int[] result = new int[arr.length];
		int k = l;
		int i = l;
		int j = m + 1;
		while (i <= m && j <= r) {
			if (arr[i] <= arr[j]) { // for descending change here
				result[k++] = arr[i++];
			} else {
				result[k++] = arr[j++];
			}
		}
		while (i <= m) {
			result[k++] = arr[i++];
		}
		while (j <= r) {
			result[k++] = arr[j++];
		}
		for (i = l; i <= r; i++) {
			arr[i] = result[i];
		}
	}

	static void mergeSort(int arr[], int l, int r) {
		// code here
		if (l == r) {
			return;
		}
		int mid = l + (r - l) / 2;
		mergeSort(arr, l, mid);
		mergeSort(arr, mid + 1, r);
		merge(arr, l, mid, r);

	}

	public static void main(String[] args) {
		int[] arr = { 12, 3, 18, 24, 0, 5, 8 };
		mergeSort(arr, 0, arr.length - 1);
		for (int i : arr) {
			System.out.print(i + " ");
		}

	}
}
