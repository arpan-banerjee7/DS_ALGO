package striver.BST;

// 701. Insert into a Binary Search Tree
// https://www.youtube.com/watch?v=FiFiNvM29ps&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=45
// https://leetcode.com/problems/insert-into-a-binary-search-tree/submissions/

public class InsertIntoBST {
	// traverse and insert into the place where it fits
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null)
			return new TreeNode(val);
		TreeNode node = root;
		while (node != null) {

			if (val > node.val) {
				if (node.right == null) {
					node.right = new TreeNode(val);
					break;
				}
				node = node.right;
			} else {
				if (node.left == null) {
					node.left = new TreeNode(val);
					break;
				}
				node = node.left;
			}

		}
		return root;
	}
}
