package sorting.alogrithims;

// https://www.youtube.com/watch?v=Il45xNUHGp0
// https://www.geeksforgeeks.org/radix-sort/

/*
 * TC - BC--- o(n) special case for high base number , base= n - used for high base high frequency
 * used when counting sort is not feasible for range n^2
 * 
 * for high range and varying n, other sorting algos will give o(nlogn), but this will give o(n)
 * 
 * TC--  WC--- o(digit*(n+range)
 * 
 * Space-- o(n+range)
 */

public class RadixSort {

	private static int findMax(int[] arr) {
		int max = arr[0];
		for (int i : arr) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	private static void countSort(int[] arr, int n, int digit) {

		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];

		// count the frequency of the numbers as per the digits
		for (i = 0; i < n; i++) {
			count[(int) (arr[i] / (Math.pow(10, digit - 1))) % 10]++;
		}

		// find cumulative count
		for (i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		// fill the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[(int) (arr[i] / (Math.pow(10, digit - 1))) % 10] - 1] = arr[i];
			count[(int) (arr[i] / (Math.pow(10, digit - 1))) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to current digit
		for (i = 0; i < n; i++)
			arr[i] = output[i];
	}

	private static void radixSort(int[] arr, int n) {

		// find the maximum number in the array
		int max = findMax(arr);

		// find the number of digits
		int digits = (int) Math.floor(Math.log10(max) + 1);

		for (int i = 1; i <= digits; i++) {
			countSort(arr, n, i);
		}
	}

	// A utility function to print an array
	static void print(int arr[], int n) {
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	public static void main(String[] args) {

		int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
		int n = arr.length;

		// Function Call
		radixSort(arr, n);
		print(arr, n);

	}

}
