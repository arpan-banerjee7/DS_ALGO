package striver.BST;

/*
Recover BST-
https://www.youtube.com/watch?v=ZWGW7FminDM&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=53
	https://leetcode.com/problems/recover-binary-search-tree/
 */

// TC- o(n)
// space- o(1) ignoring recursive call stack space
public class RecoverBinarySearchTree {

		TreeNode prev = null;
		TreeNode first = null;
		TreeNode middle = null;
		TreeNode last = null;

		private void inorder(TreeNode root) {
			if (root == null) {
				return;
			}
			inorder(root.left);

			if (prev != null && root.val < prev.val) {
				// check if its the first violation
				if (first == null) {
					first = prev;
					middle = root;
				} else {
					last = root;
				}
			}
			prev = root;
			inorder(root.right);
		}

		public void recoverTree(TreeNode root) {
			if (root == null)
				return;
			inorder(root);

			// swap logic
			if (first != null && last != null) {
				int temp = first.val;
				first.val = last.val;
				last.val = temp;
			} else if (first != null && middle != null) {
				int temp = middle.val;
				middle.val = first.val;
				first.val = temp;
			}

		}
}
