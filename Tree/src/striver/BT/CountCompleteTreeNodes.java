package striver.BT;

//222. Count Complete Tree Nodes
/*
Count nodes in a complete binary tree-
https://www.youtube.com/watch?v=u-yWemKGWO0&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=34
https://leetcode.com/problems/count-complete-tree-nodes/submissions/
 */

// TC-o(logn^2) 
public class CountCompleteTreeNodes {
	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + countNodes(root.left) + countNodes(root.right);
	}
}
