package striver.BST;

//Lintcode 448 · Inorder Successor in BST [https://www.lintcode.com/problem/448/]
//Ref: https://www.youtube.com/watch?v=lQIXz5NJYLs Code Library
//https://www.techiedelight.com/find-inorder-successor-given-key-bst/ **

public class InorderSuccesorInBST {
	// driver function
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (root == null)
			return null;

		TreeNode res = findSuccessor(root, null, p.val);
		return res;
	}

	// Recursive function to find an inorder successor for the given key in the BST
	private static TreeNode findSuccessor(TreeNode root, TreeNode succ, int key) {
		// base case
		if (root == null) {
			return null;
		}

		// if a node with the desired value is found, the successor is the minimum
		// value node in its right subtree (if any)
		if (root.val == key) {
			if (root.right != null) {
				return findMinimum(root.right);
			}
		}

		// if the given key is less than the root node, recur for the left subtree
		else if (key < root.val) {
			// update successor to the current node before recursing in the
			// left subtree
			succ = root;
			return findSuccessor(root.left, succ, key);
		}

		// if the given key is more than the root node, recur for the right subtree
		else {
			return findSuccessor(root.right, succ, key);
		}

		return succ;
	}

	// Helper function to find minimum value node in a given BST
	private static TreeNode findMinimum(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}

		return root;
	}
}
