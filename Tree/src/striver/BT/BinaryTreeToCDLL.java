package striver.BT;

/*
Binary Tree to CDLL-
https://www.youtube.com/watch?v=WVFk9DwRgpY
		https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1
 */

public class BinaryTreeToCDLL {
	Node head = null;
	Node prev = null;
	int flag = 0;

	private void helper(Node root) {
		if (root == null)
			return;
		helper(root.left);

		if (flag == 0) {
			flag = 1;
			head = root;
			prev = root;
		} else {
			prev.right = root;
			root.left = prev;
			prev = root;

		}

		helper(root.right);
	}

	// Function to convert binary tree to doubly linked list and return it.
	Node bToCDLL(Node root) {
		// Your code here
		helper(root);
		head.left = prev;
		prev.right = head;
		return head;
	}
}
