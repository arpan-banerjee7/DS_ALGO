package striver;

// 104. Maximum Depth of Binary Tree
// Ref: https://www.youtube.com/watch?v=eD3tmO66aBA&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=15 Take u forward

// TC: O(n)
public class MaxDepthofBT {

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int maxLeft = maxDepth(root.left);
		int maxRight = maxDepth(root.right);

		return Math.max(maxLeft, maxRight) + 1;
	}

}