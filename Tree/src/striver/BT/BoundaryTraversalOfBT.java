package striver.BT;

import java.util.ArrayList;

// Striver - https://www.youtube.com/watch?v=0ca1nvR0be4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=22
// https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
// 878 · Boundary of Binary Tree Lintcode

public class BoundaryTraversalOfBT {
	class Solution {
		// check if it is a leaf node
		private boolean isLeaf(Node node) {
			if (node.left == null && node.right == null) {
				return true;
			}
			return false;
		}

		// take the left side first without the leaf nodes
		private void addLeft(Node node, ArrayList<Integer> res) {
			Node curr = node.left;
			while (curr != null) {
				if (!isLeaf(curr)) {
					res.add(curr.data);
				}
				if (curr.left != null) {
					curr = curr.left;
				} else {
					curr = curr.right;
				}
			}
		}

		private void addLeaf(Node node, ArrayList<Integer> res) {
			if (node == null) {
				return;
			}

			addLeaf(node.left, res);
			if (isLeaf(node)) {
				res.add(node.data);
			}
			addLeaf(node.right, res);
		}

		// take the right side first without the leaf nodes
		private void addRight(Node node, ArrayList<Integer> res) {
			Node curr = node.right;
			ArrayList<Integer> temp = new ArrayList<>();
			while (curr != null) {
				if (!isLeaf(curr)) {
					temp.add(curr.data);
				}
				if (curr.right != null) {
					curr = curr.right;
				} else {
					curr = curr.left;
				}
			}
			for (int i = temp.size() - 1; i >= 0; i--) {
				res.add(temp.get(i));
			}
		}

		ArrayList<Integer> boundary(Node node) {
			ArrayList<Integer> res = new ArrayList<>();

			if (!isLeaf(node)) {
				res.add(node.data);
			}
			addLeft(node, res);
			addLeaf(node, res);
			addRight(node, res);
			return res;
		}
	}
}
