package striver.BT;

// Construct in level order fashion from parent array-
// https://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
	
public class ConstructBtFromParentArrayInLevelOrder {
	// Java program to construct binary tree from
	// given array in level order fashion

	Node root;

	// Tree Node
	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	// Function to insert nodes in level order
	public Node insertLevelOrder(int[] arr, Node root, int i) {
		// Base case for recursion
		if (i < arr.length) {
			Node temp = new Node(arr[i]);
			root = temp;

			// insert left child
			root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

			// insert right child
			root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
		}
		return root;
	}

	// Function to print tree nodes in InOrder fashion
	public void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	// Driver program to test above function
	public static void main(String args[]) {
		ConstructBtFromParentArrayInLevelOrder t2 = new ConstructBtFromParentArrayInLevelOrder();
		int arr[] = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
		t2.root = t2.insertLevelOrder(arr, t2.root, 0);
		t2.inOrder(t2.root);
	}

}
