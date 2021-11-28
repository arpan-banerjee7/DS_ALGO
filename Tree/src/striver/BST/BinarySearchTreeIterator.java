package striver.BST;

import java.util.Stack;

/*
Implement Binary Seacrh Iterator- follow the same technique as inorder iterative traversalpushLeft-
https://www.youtube.com/watch?v=D2jMcmxU4bs&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=51
https://leetcode.com/problems/binary-search-tree-iterator/submissions/
 */

public class BinarySearchTreeIterator {
	// TC o(1)
	// space o(h) h=height of tree
	class BSTIterator {

		Stack<TreeNode> st;

		private void pushLeft(TreeNode node) {
			while (node != null) {
				st.add(node);
				node = node.left;
			}
		}

		public BSTIterator(TreeNode root) {
			st = new Stack<>();
			pushLeft(root);
		}

		public int next() {
			if (!hasNext())
				return -1;
			TreeNode node = st.pop();
			pushLeft(node.right);
			return node.val;
		}

		public boolean hasNext() {
			return !st.isEmpty();

		}
	}

	/**
	 * Your BSTIterator object will be instantiated and called as such:
	 * BSTIterator obj = new BSTIterator(root);
	 * int param_1 = obj.next();
	 * boolean param_2 = obj.hasNext();
	 */
}
