package striver.BT;

// https://www.geeksforgeeks.org/find-largest-subtree-having-identical-left-and-right-subtrees/
public class LargestSubtreeIdenticalLeftRightSubtrees {
	static class Node {
		int data;
		Node left, right;
	}

	static Node newNode(int data) {
		Node node = new Node();
		node.data = data;
		node.left = node.right = null;
		return (node);
	}

	static int maxSize;
	static Node maxNode;

	static class Pair {
		String child;
		int size;

		Pair(String child, int size) {
			this.child = child;
			this.size = size;
		}
	}

	private static Pair largestSubtree(Node root) {

		if (root == null) {
			String s = "x";
			int height = 0;
			return new Pair(s, height);
		}
		Pair leftPair = largestSubtree(root.left);
		Pair rightPair = largestSubtree(root.right);
		int currSize = Math.max(leftPair.size, rightPair.size) + 1;

		if (leftPair.child.equals(rightPair.child)) {
			if (currSize > maxSize) {
				maxNode = root;
				maxSize = currSize;
			}
		}
		String p = leftPair.child + root.data + rightPair.child;
		return new Pair(p, currSize);
	}

	private static void printTree(Node root) {
		if (root == null)
			return;
		printTree(root.left);
		System.out.println(root.data);
		printTree(root.right);
	}

	public static void main(String[] args) {
		/*
		 * Let us construct the following Tree 50 / \ 10 60 / \ / \ 5 20 70 70 / \ / \
		 * 65 80 65 80
		 */
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
		maxSize = 0;

		largestSubtree(root);
		printTree(maxNode);
	}
}
