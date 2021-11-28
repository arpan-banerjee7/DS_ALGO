package striver.BT;

// 1448. Count Good Nodes in Binary Tree
// Ref: https://www.youtube.com/watch?v=Ho22p_2JBWA Naresh 
// https://leetcode.com/problems/count-good-nodes-in-binary-tree/discuss/635259/JavaC%2B%2BPython-One-line For the recursive diagram
// Alerternative recursive sol- new concecpt a bit diff-
// https://www.youtube.com/watch?v=7cp5imvDzl4

// TC = O(N);
// Call stack
public class CountGoodNodes {
	public int goodNodes(TreeNode root) {
		if (root == null)
			return 0;
		return helper(root, root.val);
	}

	public static int helper(TreeNode root, int max) {
		if (root == null)
			return 0;
		if (root.val >= max) {
			return 1 + helper(root.left, Math.max(root.val, max)) + helper(root.right, Math.max(root.val, max));
		} else {
			return helper(root.left, max) + helper(root.right, max);
		}

	}
}
