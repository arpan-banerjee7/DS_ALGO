package dp.tree;
// Same as diameter of binary tree
// https://www.youtube.com/watch?v=Osz-Vwer6rw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=48
// https://www.techiedelight.com/find-diameter-of-a-binary-tree/
// https://leetcode.com/problems/diameter-of-binary-tree/
/*
          2
	-20       4
	5    3   2
*/

public class BinaryTreeMaxPathSum {
	// Post order traversal pattern
	// slight modificiation
	// similar to diameter of binary tree
	// https://www.techiedelight.com/find-diameter-of-a-binary-tree/
	// https://leetcode.com/problems/diameter-of-binary-tree/
	int maxSum;

	private int findMaxSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = findMaxSum(root.left);
		int right = findMaxSum(root.right);

		int temp = Math.max(Math.max(left, right) + root.val, root.val);
		// every time we calculate the best possible option
		// max of left,right+1 or root val or left+right+1
		int ans = Math.max(temp, left + right + root.val);
		maxSum = Math.max(maxSum, ans);
		return temp;

	}

	public int maxPathSum(TreeNode root) {
		maxSum = root.val;
		findMaxSum(root);
		return maxSum;
	}
}
