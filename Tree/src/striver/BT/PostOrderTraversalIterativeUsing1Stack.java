package striver.BT;

import java.util.ArrayList;
import java.util.Stack;

// https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
// https://youtu.be/NzIGLLwZBS8		Striver
// 145. Binary Tree Postorder Traversal

// TC:O(n)  SC: O(n)
public class PostOrderTraversalIterativeUsing1Stack {
	// A java program for iterative postorder traversal using stack

	Node root;
	ArrayList<Integer> list = new ArrayList<Integer>();
	ArrayList<Integer> postOrderIterative(Node node) {
		Stack<Node> S = new Stack<Node>();

		// Check for empty tree
		if (node == null)
			return list;
		S.push(node);
		Node prev = null;
		while (!S.isEmpty()) {
			Node current = S.peek();
			if (prev == null || prev.left == current || prev.right == current) {
				if (current.left != null)
					S.push(current.left);
				else if (current.right != null)
					S.push(current.right);
				else {
					S.pop();
					list.add(current.data);
				}
			} else if (current.left == prev) {
				if (current.right != null)
					S.push(current.right);
				else {
					S.pop();
					list.add(current.data);
				}
			} else if (current.right == prev) {
				S.pop();
				list.add(current.data);
			}

			prev = current;
		}

		return list;
	}

	// Driver program to test above functions
	public static void main(String args[]) {
		PostOrderTraversalIterativeUsing1Stack tree = new PostOrderTraversalIterativeUsing1Stack();

		// Let us create trees shown in above diagram
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

		ArrayList<Integer> mylist = tree.postOrderIterative(tree.root);

		System.out.println("Post order traversal of binary tree is :");
		System.out.println(mylist);
	}
}
