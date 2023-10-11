package striver.BT;

// https://www.codingninjas.com/codestudio/problems/childrensumproperty_790723?source=youtube&amp;campaign=Striver_Tree_Videos&amp;utm_source=youtube&amp;utm_medium=affiliate&amp;utm_campaign=Striver_Tree_Videos
// https://www.youtube.com/watch?v=fnmisPM6cVo&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=31 Striver
// https://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/

public class MakeBTFollowChildrenSumProperty {
	static class BinaryTreeNode<Integer> {
		int data;
		BinaryTreeNode<Integer> left;
		BinaryTreeNode<Integer> right;

		public BinaryTreeNode(int data) {
			this.data = data;
		}
	}

	public static void changeTree(BinaryTreeNode<Integer> root) {
		// Write your code here.
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		int leftChild = root.left != null ? root.left.data : 0;
		int rightChild = root.right != null ? root.right.data : 0;
		if (leftChild + rightChild >= root.data) {
			root.data = leftChild + rightChild;
		} else {
			if (root.left != null) {
				root.left.data = root.data;
			}
			if (root.right != null) {
				root.right.data = root.data;
			}
		}

		changeTree(root.left);
		changeTree(root.right);

		int left = root.left != null ? root.left.data : 0;
		int right = root.right != null ? root.right.data : 0;
		root.data = left + right;

	}

	public static void changeTree_without_addingrootdata_while_going_down(BinaryTreeNode<Integer> root) {
		// Write your code here.
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		int leftChild = root.left != null ? root.left.data : 0;
		int rightChild = root.right != null ? root.right.data : 0;

		if (leftChild + rightChild < root.data) {
			if (root.left != null) {
				root.left.data = root.data;
			}
			if (root.right != null) {
				root.right.data = root.data;
			}
		}
		changeTree(root.left);
		changeTree(root.right);

		int left = root.left != null ? root.left.data : 0;
		int right = root.right != null ? root.right.data : 0;
		root.data = left + right;

	}
}
