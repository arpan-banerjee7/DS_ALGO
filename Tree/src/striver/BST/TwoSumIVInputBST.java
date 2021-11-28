package striver.BST;

import java.util.Stack;

// 653. Two Sum IV - Input is a BST
/*
Two sum- use concept of Binary Seach Tree iterator/submissions/
https://www.youtube.com/watch?v=ssL3sHwPeb4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=52
	https://leetcode.com/problems/two-sum/
 */

// TC - O(n)
// space- o(h)+o(h)
public class TwoSumIVInputBST {

	class BSTIterator {

		Stack<TreeNode> st;
		boolean reverse;

		public BSTIterator(TreeNode root, boolean isRev) {
			st = new Stack<>();
			reverse = isRev;
			if (isRev) {
				pushRight(root);
			} else {
				pushLeft(root);
			}

		}

		public int next() {
			TreeNode node = st.pop();
			if (reverse) {
				pushRight(node.left);
			} else {
				pushLeft(node.right);
			}

			return node.val;
		}

		public boolean hasNext() {
			return !st.isEmpty();

		}

		private void pushLeft(TreeNode node) {
			while (node != null) {
				st.add(node);
				node = node.left;
			}
		}

		private void pushRight(TreeNode node) {
			while (node != null) {
				st.add(node);
				node = node.right;
			}
		}

	}

	class Solution {
		public boolean findTarget(TreeNode root, int k) {
			if (root == null)
				return false;
			BSTIterator bstLeft = new BSTIterator(root, false);
			BSTIterator bstRight = new BSTIterator(root, true);
			int low = bstLeft.next();
			int high = bstRight.next();

			while (low < high) {
				if (low + high == k)
					return true;
				if (low + high > k) {
					high = bstRight.next();
				} else {
					low = bstLeft.next();
				}
			}
			return false;
		}
	}
}
