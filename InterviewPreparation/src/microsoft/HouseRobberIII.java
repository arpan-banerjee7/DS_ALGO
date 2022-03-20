package microsoft;

// 337. House Robber III
// https://leetcode.com/problems/house-robber-iii/
// https://www.youtube.com/watch?v=nHR8ytpzz7c Neetcode

public class HouseRobberIII {
	private int[] helper(TreeNode root) {
		if (root == null)
			return new int[] { 0, 0 };

		int[] left = helper(root.left);
		int[] right = helper(root.right);

		// with root means can t take left and right values(skip immediate children)
		int withRoot = root.val + left[1] + right[1];

		// without root- can take immediate child/can skip also- take max of them
		int withoutRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

		return new int[] { withRoot, withoutRoot };
	}

	public int rob(TreeNode root) {
		int[] res = helper(root);
		return Math.max(res[0], res[1]);
	}
	public static void main(String[] args) {
		/*
		 * Input: root = [3,2,3,null,3,null,1] Output: 7 Explanation: Maximum amount of
		 * money the thief can rob = 3 + 3 + 1 = 7.
		 */
	}
}
