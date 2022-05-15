package binarytree;

// 1022. Sum of Root To Leaf Binary Numbers
// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

public class SumOfRootToLeafBinaryNumbers {
	private void rootSum(TreeNode root, StringBuilder currPath, int[] ans) {
		if (root.left == null && root.right == null) {
			currPath.append(root.val);
			ans[0] += Integer.parseInt(currPath.toString(), 2);
			currPath.setLength(currPath.length() - 1);
			return;
		}

		currPath.append(root.val);
		if (root.left != null)
			rootSum(root.left, currPath, ans);

		if (root.right != null)
			rootSum(root.right, currPath, ans);

		currPath.setLength(currPath.length() - 1);
		return;

	}

	public int sumRootToLeaf(TreeNode root) {
		int[] ans = new int[1];
		rootSum(root, new StringBuilder(), ans);
		return ans[0];
	}

	public static void main(String[] args) {
		/*
		 * Input: root = [1,0,1,0,1,0,1] Output: 22 Explanation: (100) + (101) + (110) +
		 * (111) = 4 + 5 + 6 + 7 = 22
		 */
	}

}
