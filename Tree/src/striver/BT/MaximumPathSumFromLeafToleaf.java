package striver.BT;

// https://www.youtube.com/watch?v=ArNyupe-XH0 Aditya Verma
// https://www.youtube.com/watch?v=FxgpgxH2k8o - Partially Correct Solution
// https://practice.geeksforgeeks.org/problems/maximum-path-sum/1

// To DO- fully correct sol not found, few edge cases failing]

public class MaximumPathSumFromLeafToleaf {

	Node setTree(Node root) {

		Node temp = new Node(0);
		// if tree is left most
		if (root.right == null) {
			root.right = temp;
		} else { // if tree is right most
			root.left = temp;
		}

		return root;
	}

	int maxSum = Integer.MIN_VALUE;

	private int getMaxSum(Node root) {
		if (root == null)
			return 0;

		int left = getMaxSum(root.left);
		int right = getMaxSum(root.right);

		if (root.left == null && root.right != null)
			left = Integer.MIN_VALUE;
		if (root.right == null && root.left != null)
			right = Integer.MIN_VALUE;

		int temp = Math.max(left, right) + root.data;

		if (root.left != null && root.right != null)
			maxSum = Math.max(maxSum, (left + right + root.data));
		return temp;

	}

	int maxPathSum(Node root) {
		// this is in case the head node is leaf, like there is no left leaf only right
		// leaf is there
		if (root.left == null || root.right == null) {
			root = setTree(root);
		}
		getMaxSum(root);
		return maxSum;
	}
}
