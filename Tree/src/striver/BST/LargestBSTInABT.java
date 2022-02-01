package striver.BST;

/*
Largest BST in a Binary Tree-
https://www.youtube.com/watch?v=X0oXMdtUDwo&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=55
	https://practice.geeksforgeeks.org/problems/largest-bst/1#
 */

public class LargestBSTInABT {
	class Node {
		int data;
		Node left, right;

		public Node(int d) {
			data = d;
			left = right = null;
		}
	}

	class Tuple {
		int count;
		int max;
		int min;

		public Tuple(int count, int max, int min) {
			this.count = count;
			this.max = max;
			this.min = min;
		}
	}

	class Solution {

		private Tuple findLargestBST(Node root) {
			if (root == null) {
				Tuple tuple = new Tuple(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
				return tuple;
			}
			if (root.left == null && root.right == null) {
				return new Tuple(1, root.data, root.data);
			}

			Tuple tLeft = findLargestBST(root.left);
			Tuple tRight = findLargestBST(root.right);

			if (root.data > tLeft.max && root.data < tRight.min) {
				int count = 1 + tLeft.count + tRight.count;
				int min = Math.min(tLeft.min, root.data);
				int max = Math.max(root.data, tRight.max);
				Tuple nextTuple = new Tuple(count, max, min);
				return nextTuple;
			}
			int count = Math.max(tLeft.count, tRight.count);
			return new Tuple(count, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}

		// Return the size of the largest sub-tree which is also a BST
		private int largestBst(Node root) {
			// Write your code here
			Solution s = new Solution();
			Tuple res = s.findLargestBST(root);
			return res.count;

		}
	}
}
