package striver.BT;

/*
Check Children sum property - https://www.geeksforgeeks.org/check-for-children-sum-property-in-a-binary-tree/
https://practice.geeksforgeeks.org/problems/children-sum-parent/1#
 */

public class CheckChildrenSumPropertyOfBT {

	// Function to check whether all nodes of a tree have the value
	// equal to the sum of their child nodes.
	private static int helper(Node root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return root.data;
		}

		int left = helper(root.left);
		if (left == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		int right = helper(root.right);
		if (right == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		if (left + right == root.data) {
			return root.data;
		}
		return Integer.MAX_VALUE;
	}

	public static int isSumProperty(Node root) {
		// add your code here
		if (helper(root) == root.data) {
			return 1;
		} else {
			return 0;
		}

	}
}
