package striver;

/*  1) from any node to any node- https://leetcode.com/problems/binary-tree-maximum-path-sum/
	2) from leaf node to leaf node(correct solution not found)- https://www.youtube.com/watch?v=FxgpgxH2k8o
	https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
	https://www.youtube.com/watch?v=Osz-Vwer6rw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=50 Aditya verma
	https://www.techiedelight.com/find-diameter-of-a-binary-tree/
	https://leetcode.com/problems/diameter-of-binary-tree/

          2
	-20       4
	5    3   2
 */

// 124. Binary Tree Maximum Path Sum
// Ref: https://www.youtube.com/watch?v=WszrfSwMz58&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=18 Take u forward

public class MaximumPathSumBT {
	int maxPathSum;

	private int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);

		int tempMaxHeight = Math.max(leftHeight, rightHeight);
		int bestPossibleOption = Math.max(tempMaxHeight + root.val, root.val);

		// keep a track of max diameter on the go while calculating height
		// max diameter= Max(best possible option, left+right+root all together)
		// because best possible option returns the height considering one path
		// in diameter we need both paths , both left and right when positive
		int tempMaxPathSum = Math.max(bestPossibleOption, leftHeight + rightHeight + root.val);

		maxPathSum = Math.max(tempMaxPathSum, maxPathSum);

		// try to return the best posible option, left+root, right+root, or root only
		return bestPossibleOption;
	}

	public int maxPathSum(TreeNode root) {
		maxPathSum = root.val;
		getHeight(root);
		return maxPathSum;
	}
}
