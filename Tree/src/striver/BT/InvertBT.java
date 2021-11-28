package striver.BT;

// 226. Invert Binary Tree
// Ref: https://www.youtube.com/watch?v=_i0jqdVkObU Techdose

public class InvertBT {
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return null;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);

		TreeNode temp = left;
		root.left = right;
		root.right = temp;
		return root;
	}
}
