package striver;

// 543. Diameter of Binary Tree
// https://www.youtube.com/watch?v=Rezetez59Nk&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=17 Take u forward

public class DiameterOfBT {
	int maxDiameter = Integer.MIN_VALUE;

	private int findHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = findHeight(root.left);
		int right = findHeight(root.right);
		int temp = Math.max(left, right) + 1;// diameter does not pass through this node
		// keep a note of the max diameter encountered so far
		maxDiameter = Math.max(maxDiameter, left + right + 1);
		return temp; // explore other nodes to find max if present;
	}

	public int diameterOfBinaryTree(TreeNode root) {
		findHeight(root);
		return maxDiameter - 1;
	}
}
