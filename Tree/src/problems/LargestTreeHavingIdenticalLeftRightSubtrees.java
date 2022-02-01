package problems;

public class LargestTreeHavingIdenticalLeftRightSubtrees {

	// Java program to find the largest subtree
	// having identical left and right subtree

	/*
	 * A binary tree node has data, pointer to left child and a pointer to right
	 * child
	 */
	static class Node {
		int data;
		Node left, right;
	};

	/*
	 * Helper function that allocates a new node with the given data and null left
	 * and right pointers.
	 */
	static Node newNode(int data) {
		Node node = new Node();
		node.data = data;
		node.left = node.right = null;
		return (node);
	}

	static class string {
		String str;
	}

	static int maxSize;
	static Node maxNode;

	static class pair {
		int first;
		String second;

		pair(int a, String b) {
			first = a;
			second = b;
		}
	}

	// Sets maxSize to size of largest subtree with
	// identical left and right. maxSize is set with
	// size of the maximum sized subtree. It returns
	// size of subtree rooted with current node. This
	// size is used to keep track of maximum size.
	static pair largestSubtreeUtil(Node root, String str) {
		if (root == null)
			return new pair(0, str);

		// string to store structure of left and
		// right subtrees
		String left = "", right = "";

		// traverse left subtree and finds its size
		pair ls1 = largestSubtreeUtil(root.left, left);
		left = ls1.second;
		int ls = ls1.first;

		// traverse right subtree and finds its size
		pair rs1 = largestSubtreeUtil(root.right, right);
		right = rs1.second;
		int rs = rs1.first;

		// if left and right subtrees are similar
		// update maximum subtree if needed (Note that
		// left subtree may have a bigger value than
		// right and vice versa)
		int size = ls + rs + 1;
		if (left.equals(right)) {
			if (size > maxSize) {
				maxSize = size;
				maxNode = root;
			}
		}

		// append left subtree data
		str += "|" + left + "|";

		// append current node data
		str += "|" + root.data + "|";

		// append right subtree data
		str += "|" + right + "|";

		return new pair(size, str);
	}

	// function to find the largest subtree
	// having identical left and right subtree
	static int largestSubtree(Node node) {
		maxSize = 0;
		largestSubtreeUtil(node, "");

		return maxSize;
	}

	/* Driver program to test above functions */
	public static void main(String args[]) {
		/* Let us construct the following Tree
        50
    /     \
    10     60
    / \     / \
    5 20 70 70
        / \ / \
        65 80 65 80 */
		Node root = newNode(50);
		root.left = newNode(10);
		root.right = newNode(60);
		root.left.left = newNode(5);
		root.left.right = newNode(20);
		root.right.left = newNode(70);
		root.right.left.left = newNode(65);
		root.right.left.right = newNode(80);
		root.right.right = newNode(70);
		root.right.right.left = newNode(65);
		root.right.right.right = newNode(80);

		maxNode = null;
		maxSize = largestSubtree(root);

		System.out.println("Largest Subtree is rooted at node " + maxNode.data + "\nand its size is " + maxSize);
	}
}

