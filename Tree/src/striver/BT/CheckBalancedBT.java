package striver.BT;

/*
 * check tree visualizer - [1,2,2,3,null,null,3,4,null,null,4]
not a balanced bt

3 solutions-
https://leetcode.com/problems/balanced-binary-tree/discuss/1582218/Java-oror-3-recursive-solutions-oror-easy-to-understand
 */

// 110. Balanced Binary Tree

// TC- o(n)
public class CheckBalancedBT {

	private int checkBalanced(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftHeight = checkBalanced(root.left);
		if (leftHeight == -1) {
			return -1;
		}

		int rightHeight = checkBalanced(root.right);
		if (rightHeight == -1) {
			return -1;
		}

		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}

		return 1 + Math.max(leftHeight, rightHeight);
	}

	public boolean isBalanced(TreeNode root) {
		if (checkBalanced(root) == -1) {
			return false;
		}
		return true;
	}
}
