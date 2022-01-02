package assignment;
//Ref: https://www.youtube.com/watch?v=2bSS8rtFym4&list=PLEJXowNB4kPxiWkLPP7b4D9761SEhyEzm + Classwork Techdose

// https://leetcode.com/submissions/detail/601082284/

//Construct Operation : O(n) as there are 2*N - 1 nodes (N-1 Internal Nodes and N leaf Nodes)
//Query: O(logn) as we need to traverse the height of the tree
//Update: O(logn) as we need to traverse the height of the tree

/* Segment Tree is a Full Binary Tree Internal Nodes have 0 or 2 children. & all levels till 2nd last level is completely filled)
* It is useful only if updates are frequent, otherwise use array.
* 
* Comaparison:-
* Brute Force:  Q query time: O(N.Q)			U update time: O(U)
* 
* Array:  		 Q query time: O(Q)				U update time: O(N.U)
* 
* Segment Tree: Q query time: O(QlogN)			U update time: O(UlogN)
*/

public class ImplSegmentTree_RangeSumQuery {

	public static int[] st;

	public static void main(String[] args) {
		int n = 6;
		int[] arr = { 1, 3, 2, -2, 4, 5 };

		int size = 4 * n + 1;
		st = new int[size];

		int st_idx = 1;// start Index of SegTree
		int start = 0, end = n - 1;

		// Preprocess
		buildSegTree(st_idx, arr, start, end);

		System.out.println("The segment tree is: ");
		for (int i = 0; i <= 4 * n; i++) {
			System.out.print(st[i] + " ");
		}
		System.out.println();

		System.out.println("Query is 0-based indexed");
		System.out.println("RangeSum(2,4): " + query(2, 4, st_idx, start, end));
		System.out.println("RangeSum(1,4): " + query(1, 4, st_idx, start, end));
		System.out.println("RangeSum(3,3): " + query(3, 3, st_idx, start, end));

		arr[1] = 100;
		updateNode(st_idx, start, end, 1, 100);

		System.out.println("After update, the segment tree is: ");
		for (int i = 0; i <= 4 * n; i++) {
			System.out.print(st[i] + " ");
		}
		System.out.println();

		System.out.println("Query is 0-based indexed");
		System.out.println("Updated RangeSum(2,4): " + query(2, 4, st_idx, start, end));
		System.out.println("Updated RangeSum(1,4): " + query(1, 4, st_idx, start, end));
		System.out.println("Updated RangeSum(1,1): " + query(1, 1, st_idx, start, end));
		System.out.println("Updated RangeSum(2,2): " + query(2, 2, st_idx, start, end));
		System.out.println("Updated RangeSum(3,3): " + query(3, 3, st_idx, start, end));
		System.out.println("Updated RangeSum(4,4): " + query(4, 4, st_idx, start, end));

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
		st[st_idx] = st[2 * st_idx] + st[2 * st_idx + 1];
	}

	// getSum of a particular range
	private static int query(int qs, int qe, int st_idx, int start, int end) {
		if (qs > end || qe < start)// No overlap
			return 0;
		if (start >= qs && end <= qe)// Total Overlap
			return st[st_idx];

		// Partital Overlap Case
		int middle = start + (end - start) / 2;
		int leftSum = query(qs, qe, 2 * st_idx, start, middle);
		int rightSum = query(qs, qe, 2 * st_idx + 1, middle + 1, end);
		return leftSum + rightSum;
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
		st[st_idx] = st[2 * st_idx] + st[2 * st_idx + 1];
	}

}

/*
 * output : The segment tree is: 0 13 6 7 4 2 2 5 1 3 0 0 -2 4 0 0 0 0 0 0 0 0 0
 * 0 0 Query is 0-based indexed RangeSum(2,4): 4 RangeSum(1,4): 7 RangeSum(3,3):
 * -2 After update, the segment tree is: 0 110 103 7 101 2 2 5 1 100 0 0 -2 4 0
 * 0 0 0 0 0 0 0 0 0 0 Query is 0-based indexed Updated RangeSum(2,4): 4 Updated
 * RangeSum(1,4): 104 Updated RangeSum(1,1): 100 Updated RangeSum(2,2): 2
 * Updated RangeSum(3,3): -2 Updated RangeSum(4,4): 4
 */

// 303. Range Sum Query - Immutable
//TC-o(logn)
class NumArray {
	int tree[];
	int n;

	int build(int L, int R, int pos, int[] nums) {
		if (L == R) {
			tree[pos] = nums[L];
			return tree[pos];
		}
		int mid = (L + R) / 2;
		tree[pos] = build(L, mid, pos * 2, nums) + build(mid + 1, R, pos * 2 + 1, nums);
		return tree[pos];

	}

	// determing the size of segment tree- if power of 2 all levels will be filled
	// or else power of 2 just greater than 2n-1(total number of nodes)

	// n=length of input array, since we are using merge sort like partition,
	// at last level all the array elements will be there so leaves=n
	// and since segment tree is a full binary tree so internal nodes=n-1
	// therefore total nodes=2n-1

	// direct formule--
	// binary tree with n nodes min possible height= log(n+1)/ it will have min
	// possible height since it is a full binary tree
	// once u get the height--
	// max nodes where height is h= 2^h-1

	// power of 2
	public NumArray(int[] nums) {

		n = nums.length;
		if (n == 0)
			return;
		if ((n & (n - 1)) == 0) {
			tree = new int[2 * n];
		} else {
			int count = 0;
			int n1 = n;
			while (n1 > 0) {
				count++;
				n1 >>= 1;
			}
			tree = new int[2 * (1 << count)];

		}
		build(0, n - 1, 1, nums);
	}

	int find(int st, int end, int L, int R, int pos) {
		if (end < L || R < st)
			return 0;
		if (st <= L && R <= end)
			return tree[pos];
		int mid = (L + R) / 2;
		return find(st, end, L, mid, pos * 2) + find(st, end, mid + 1, R, pos * 2 + 1);
	}

	public int sumRange(int i, int j) {

		return find(i, j, 0, n - 1, 1);

	}
}
