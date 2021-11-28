package striver.BST;

// 98. Validate Binary Search Tree
// https://www.youtube.com/watch?v=f-sj7I5oXEI&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=47
// https://leetcode.com/problems/validate-binary-search-tree/

public class ValidateBST {
	private boolean checkValidity(TreeNode root, Long low, Long high) {
		if (root == null) {
			return true;

		}

		if (!(root.val > low && root.val < high)) {
			return false;
		}

		boolean left = checkValidity(root.left, low, new Long(root.val));
		boolean right = checkValidity(root.right, new Long(root.val), high);

		return left && right;
	}

	public boolean isValidBST(TreeNode root) {
		return checkValidity(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}
}
