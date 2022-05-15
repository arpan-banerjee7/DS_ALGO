package binarytree;

// 617. Merge Two Binary Trees
// https://leetcode.com/problems/merge-two-binary-trees/

public class MergeTwoBinaryTrees {
	public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return null;

		if (root1 == null) {
			return root2;
		}
		if (root2 == null) {
			return root1;
		}

		TreeNode node = new TreeNode(root1.val + root2.val);
		node.left = mergeTrees(root1.left, root2.left);
		node.right = mergeTrees(root1.right, root2.right);
		return node;
	}

	public static void main(String[] args) {
		/*
		 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7] Output:
		 * [3,4,5,5,4,null,7]
		 */

	}

}
