package striver.BST;

// https://www.youtube.com/watch?v=xm_W1ub-K-w&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=43
// https://www.codingninjas.com/codestudio/problems/floor-from-bst_920457?source=youtube&campaign=Striver_Tree_Videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=Striver_Tree_Videos&leftPanelTab=1

public class FloorInABST {
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

	public static int floorInBST(TreeNode<Integer> root, int X) {
		// Write your code here.
		int floor = -1;
		TreeNode<Integer> temp = root;

		while (temp != null) {
			if (temp.data == X) {
				floor = temp.data;
				return floor;
			}

			if (X > temp.data) {
				floor = temp.data;
				temp = temp.right;
			} else {

				temp = temp.left;
			}
		}
		return floor;
	}
}
