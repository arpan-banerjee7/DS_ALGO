package assignment;

//Ref: https://www.youtube.com/watch?v=2bSS8rtFym4&list=PLEJXowNB4kPxiWkLPP7b4D9761SEhyEzm + Classwork Techdose (Range Sum Query)

//Range Min Query: https://www.youtube.com/watch?v=DpSYj7t1sbQ Techdose
//https://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/ GFG

/* Segment Tree is a Full Binary Tree Internal Nodes have 0 or 2 children. & all levels till 2nd last level is completely filled)
* It is useful only if updates are frequent, otherwise use array.
* 
* Comaparison:-
* Brute Force:  				Q query time: O(N.Q)				U update time: O(1)
* 
* 2-D Array Efficient:  		Q query time: O(N^2 + Q)			U update time: O(N to N^2)
* 
* Segment Tree: 				Q query time: O(N + QlogN)			U update time: O(logN)
*/
public class RangeMinQuery {

	public static int[] st;

	public static void main(String[] args) {
		int[] arr = { 5, 2, 7, 1, 3 };
		int n = arr.length;

		// int size = 4 * n + 1;

		// Height of segment tree
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

		// Maximum size of segment tree
		int max_size = 2 * (int) Math.pow(2, x) - 1; // Max_Size = 2*2^(logN) -1 where base = 2. // GFG Reference
		st = new int[max_size]; // allocate memory

		int st_idx = 1;// start Index of SegTree
		int start = 0, end = n - 1;

		// Preprocess
		buildSegTree(st_idx, arr, start, end);

		System.out.println("The segment tree is: ");
		for (int i = 0; i < max_size; i++) {
			System.out.print(st[i] + " ");
		}
		System.out.println();

		System.out.println("Query is 0-based indexed");
		System.out.println("RangeMin(2,4): " + query(2, 4, st_idx, start, end));
		System.out.println("RangeMin(1,4): " + query(1, 4, st_idx, start, end));
		System.out.println("RangeMin(3,3): " + query(3, 3, st_idx, start, end));
		System.out.println("RangeMin(0,2): " + query(0, 2, st_idx, start, end));

		arr[3] = 4;
		// arr[] = { 5, 2, 7, 4, 3 };
		updateNode(st_idx, start, end, 3, 4);

		System.out.println("After update, the segment tree is: ");
		for (int i = 0; i < max_size; i++) {
			System.out.print(st[i] + " ");
		}
		System.out.println();

		System.out.println("Query is 0-based indexed");
		System.out.println("Updated RangeMin(2,4): " + query(2, 4, st_idx, start, end));
		System.out.println("Updated RangeMin(1,4): " + query(1, 4, st_idx, start, end));
		System.out.println("Updated RangeMin(1,1): " + query(1, 1, st_idx, start, end));
		System.out.println("Updated RangeMin(2,2): " + query(2, 2, st_idx, start, end));
		System.out.println("Updated RangeMin(3,3): " + query(3, 3, st_idx, start, end));
		System.out.println("Updated RangeMin(4,4): " + query(4, 4, st_idx, start, end));

	}

	private static void updateNode(int st_idx, int start, int end, int pos, int newValue) {
		if (start > pos || end < pos) // Invalid Case (No Overlap)
			return;
		if (start == end) {// LeafNode Case (Total Overlap)
			st[st_idx] = newValue;
			return;
		}

		// InternalNode Case (Partial Overlap)
		int middle = start + (end - start) / 2;
		updateNode(2 * st_idx, start, middle, pos, newValue);
		updateNode(2 * st_idx + 1, middle + 1, end, pos, newValue);
		st[st_idx] = Math.min(st[2 * st_idx], st[2 * st_idx + 1]);
	}

	// getMinimum of a particular range
	private static int query(int qs, int qe, int st_idx, int start, int end) {
		if (qs > end || qe < start)// No overlap
			return Integer.MAX_VALUE;
		if (start >= qs && end <= qe)// Total Overlap
			return st[st_idx];

		// Partital Overlap Case
		int middle = start + (end - start) / 2;
		int leftSum = query(qs, qe, 2 * st_idx, start, middle);
		int rightSum = query(qs, qe, 2 * st_idx + 1, middle + 1, end);
		return Math.min(leftSum, rightSum);
	}

	// constructing the segment tree
	private static void buildSegTree(int st_idx, int[] arr, int start, int end) {
		if (start > end)// Invalid Case
			return;
		if (start == end) {// LeafNode Case
			st[st_idx] = arr[start];
			return;
		}

		// InternalNode Case: 1 based indexing used
		int middle = start + (end - start) / 2;
		buildSegTree(2 * st_idx, arr, start, middle);
		buildSegTree(2 * st_idx + 1, arr, middle + 1, end);
		st[st_idx] = Math.min(st[2 * st_idx], st[2 * st_idx + 1]);
	}

}

/*
 * output :
 */