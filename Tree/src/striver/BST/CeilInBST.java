package striver.BST;

/*
https://www.youtube.com/watch?v=KSsk8AhdOZA&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=42
https://www.codingninjas.com/codestudio/problems/ceil-from-bst_920464?source=youtube&campaign=Striver_Tree_Videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=Striver_Tree_Videos&leftPanelTab=1
 */

public class CeilInBST {
	static class TreeNode<T> {
		T data;
		TreeNode<T> left;
		TreeNode<T> right;

		TreeNode(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	};

	public static int findCeil(TreeNode<Integer> node, int x) {

		// Write your code here
		int ceil = -1;
		TreeNode<Integer> temp = node;

		while (temp != null) {
			if (temp.data == x) {
				ceil = temp.data;
				return ceil;
			}

			if (x > temp.data) {
				temp = temp.right;
			} else {
				ceil = temp.data;
				temp = temp.left;
			}
		}
		return ceil;

	}
}
