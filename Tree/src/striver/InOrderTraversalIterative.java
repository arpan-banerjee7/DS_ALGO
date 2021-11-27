package striver;

import java.util.Stack;

//Iterative with extra space- https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
//Iterative without extra space( Morris Traversal- uses concept of threaded Binary Tree) https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
//video desc striver- https://www.youtube.com/watch?v=80Zug6D1_r4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=39

public class InOrderTraversalIterative {
	// non-recursive java program for inorder traversal

	Node root;

	void inorder() {
		if (root == null)
			return;

		Stack<Node> s = new Stack<Node>();
		Node curr = root;

		// traverse the tree
		while (curr != null || s.size() > 0) {
			while (curr != null) {
				s.push(curr);
				curr = curr.left;
			}

			curr = s.pop();

			System.out.print(curr.data + " ");
			curr = curr.right;
		}
	}

	public static void main(String args[]) {

		/*
		 * creating a binary tree and entering the nodes
		 */
		InOrderTraversalIterative tree = new InOrderTraversalIterative();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.inorder();
	}
}
