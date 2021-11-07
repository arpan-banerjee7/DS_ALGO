package dp.tree;
// https://www.youtube.com/watch?v=ArNyupe-XH0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=49

// https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
// TO DO
class Node {

	int data;
	Node left, right;

	Node(int item) {
		data = item;
		left = right = null;
	}
}

// An object of Res is passed around so that the
// same value can be used by multiple recursive calls.
class Res {
	int val;
}

public class BinaryTreeMaxPathSumLeafToLeaf {
	// Java program to find maximum path sum between two leaves
	// of a binary tree

	static Node root;

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

	// A utility function to find the maximum sum between any
	// two leaves.This function calculates two values:
	// 1) Maximum path sum between two leaves which is stored
	// in res.
	// 2) The maximum root to leaf path sum which is returned.
	// If one side of root is empty, then it returns INT_MIN
	int maxPathSumUtil(Node node, Res res) {

		// Base cases
		if (node == null)
			return 0;
		if (node.left == null && node.right == null)
			return node.data;

		// Find maximum sum in left and right subtree. Also
		// find maximum root to leaf sums in left and right
		// subtrees and store them in ls and rs
		int ls = maxPathSumUtil(node.left, res);
		int rs = maxPathSumUtil(node.right, res);

		// If both left and right children exist
		if (node.left != null && node.right != null) {

			// Update result if needed
			res.val = Math.max(res.val, ls + rs + node.data);

			// Return maximum possible value for root being
			// on one side
			return Math.max(ls, rs) + node.data;
		}

		// If any of the two children is empty, return
		// root sum for root being on one side
		return (node.left == null) ? rs + node.data : ls + node.data;
	}

	// The main function which returns sum of the maximum
	// sum path between two leaves. This function mainly
	// uses maxPathSumUtil()
	int maxPathSum(Node node) {
		Res res = new Res();
		res.val = Integer.MIN_VALUE;

		if (root.left == null || root.right == null) {
			root = setTree(root);
		}
		// if tree is left most or right most
		// call setTree() method to adjust tree first
		maxPathSumUtil(root, res);
		return res.val;
	}

	// Driver program to test above functions
	public static void main(String args[]) {
		BinaryTreeMaxPathSumLeafToLeaf tree = new BinaryTreeMaxPathSumLeafToLeaf();
		tree.root = new Node(-15);
		tree.root.left = new Node(5);
		tree.root.right = new Node(6);
		tree.root.left.left = new Node(-8);
		tree.root.left.right = new Node(1);
		tree.root.left.left.left = new Node(2);
		tree.root.left.left.right = new Node(6);
		tree.root.right.left = new Node(3);
		tree.root.right.right = new Node(9);
		tree.root.right.right.right = new Node(0);
		tree.root.right.right.right.left = new Node(4);
		tree.root.right.right.right.right = new Node(-1);
		tree.root.right.right.right.right.left = new Node(10);
		System.out.println("Max pathSum of the given binary tree is " + tree.maxPathSum(root));
	}
}
