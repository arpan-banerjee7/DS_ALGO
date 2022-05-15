package problems;

// 965. Univalued Binary Tree
// https://leetcode.com/problems/univalued-binary-tree/

public class UnivaluedBinaryTree {
	private boolean hasSameVal(TreeNode root, int value) {

		if (root == null)
			return true;
		if (root.val != value)
			return false;

		return hasSameVal(root.left, value) && hasSameVal(root.right, value);

	}

	public boolean isUnivalTree(TreeNode root) {
		return hasSameVal(root, root.val);
	}

	public static void main(String[] args) {
		/*
		 * Input: root = [1,1,1,1,1,null,1] Output: true
		 */

	}

}
