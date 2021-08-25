package sorting.alogrithims;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// find max and min- https://afteracademy.com/blog/find-the-minimum-and-maximum-value
// bucket sort-- https://www.javatpoint.com/bucket-sort-in-java
// https://www.geeksforgeeks.org/bucket-sort-2/
// https://www.programiz.com/dsa/bucket-sort
// https://www.youtube.com/watch?v=rNdTWHQMvOk

/*
 * tc- wc----     o(n^2)
 *     BC----     o(n)-- It occurs when the elements are uniformly distributed in the buckets with 
 *     					a nearly equal number of elements in each bucket. 
 *     					The complexity becomes even better if the elements inside the buckets are already sorted.
 *     Avg Case-- o(n)
 *     
 *     Space--- o(n+k) --??
 */
public class BucketSort {

	private static void bucketSort(int[] arr, int bktSize) {
		// creating a list of buckets for storing lists
		List<Integer>[] buckets = new List[bktSize];

		// Linked list with each bucket array index
		// as there may be hash collision
		for (int i = 0; i < bktSize; i++) {
			buckets[i] = new LinkedList<Integer>();
		}

		// calculate the hash and assigns elements to the proper bucket
		for (int num : arr) {
			buckets[hash(num, bktSize)].add(num);
		}

		// iterate over the buckets and sorts the elements
		for (List<Integer> bucket : buckets) {
			// sorts the bucket
			Collections.sort(bucket);
		}

		// gather the buckets after sorting
		int index = 0;
		for (List<Integer> bucket : buckets) {
			for (int num : bucket) {
				arr[index++] = num;
			}
		}
	}

	// distributing elements
	private static int hash(int num, int bucketSize) {
		return num / bucketSize;
	}

	public static void main(String[] args) {
		{
			// array to be sort
			int[] array = { 22, 45, 12, 8, 10, 6, 72, 81, 33, 18, 50, 14, 55, 0, 12, 55 };
			System.out.println("Unsorted Array: " + Arrays.toString(array));
			// calling the user-defined method to sort the array

			bucketSort(array, 10);
			System.out.println("Sorted Array: " + Arrays.toString(array));
		}
	}
}
