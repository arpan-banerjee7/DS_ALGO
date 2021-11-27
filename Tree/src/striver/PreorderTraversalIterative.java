package striver;

import java.util.Stack;

// Iterative- with/ without space - https://www.geeksforgeeks.org/iterative-preorder-traversal/
// Without space - modify Morris traversal for inorder

// Time Complexity: O(N) 
// Auxiliary Space: O(H), where H is the height of the tree.
public class PreorderTraversalIterative {

	Node root;

	void preorderIterative() {
		preorderIterative(root);
	}

	// Iterative function to do Preorder
	// traversal of the tree
	void preorderIterative(Node node) {
		if (node == null) {
			return;
		}

		Stack<Node> st = new Stack<Node>();

		// Start from root node (set curr
		// node to root node)
		Node curr = node;

		// Run till stack is not empty or
		// current is not NULL
		while (curr != null || !st.isEmpty()) {

			// Print left children while exist
			// and keep pushing right into the
			// stack.
			while (curr != null) {
				System.out.print(curr.data + " ");

				if (curr.right != null)
					st.push(curr.right);

				curr = curr.left;
			}

			// We reach when curr is NULL, so We
			// take out a right child from stack
			if (!st.isEmpty()) {
				curr = st.pop();
			}
		}
	}

	// Driver code
	public static void main(String args[]) {
		PreorderTraversalIterative tree = new PreorderTraversalIterative();

		tree.root = new Node(10);
		tree.root.left = new Node(20);
		tree.root.right = new Node(30);
		tree.root.left.left = new Node(40);
		tree.root.left.left.left = new Node(70);
		tree.root.left.right = new Node(50);
		tree.root.right.left = new Node(60);
		tree.root.left.left.right = new Node(80);

		tree.preorderIterative(); // Output: 10 20 40 70 80 50 30 60
		
	}
}
