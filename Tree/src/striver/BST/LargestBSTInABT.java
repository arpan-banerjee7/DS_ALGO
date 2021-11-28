package striver.BST;

import java.util.LinkedList;
import java.util.Queue;

/*
Largest BST in a Binary Tree-
https://www.youtube.com/watch?v=X0oXMdtUDwo&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=55
	https://practice.geeksforgeeks.org/problems/largest-bst/1#
 */

public class LargestBSTInABT {
	static class Node {
		int data;
		Node left, right;

		public Node(int d) {
			data = d;
			left = right = null;
		}
	}

	static Node buildTree(String str) {
		// Corner Case
		if (str.length() == 0 || str.equals('N'))
			return null;
		String[] s = str.split(" ");

		Node root = new Node(Integer.parseInt(s[0]));
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);

		// Starting from the second element
		int i = 1;
		while (!q.isEmpty() && i < s.length) {
			// Get and remove the front of the queue
			Node currNode = q.remove();

			// Get the curr node's value from the string
			String currVal = s[i];

			// If the left child is not null
			if (!currVal.equals("N")) {

				// Create the left child for the curr node
				currNode.left = new Node(Integer.parseInt(currVal));

				// Push it to the queue
				q.add(currNode.left);
			}

			// For the right child
			i++;
			if (i >= s.length)
				break;
			currVal = s[i];

			// If the right child is not null
			if (!currVal.equals("N")) {

				// Create the right child for the curr node
				currNode.right = new Node(Integer.parseInt(currVal));

				// Push it to the queue
				q.add(currNode.right);
			}

			i++;
		}

		return root;
	}

}
