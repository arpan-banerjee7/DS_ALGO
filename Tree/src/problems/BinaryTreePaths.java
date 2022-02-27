package problems;

import java.util.ArrayList;
import java.util.List;

import striver.BT.TreeNode;

// 257. Binary Tree Paths
// https://leetcode.com/problems/binary-tree-paths/
// Use string builder-https://leetcode.com/problems/binary-tree-paths/discuss/68258/Accepted-Java-simple-solution-in-8-lines#:~:text=The%20time%20complex

public class BinaryTreePaths {

	/// normal string
	private void findPaths(TreeNode root, List<String> res, String op) {
		if (root.left == null && root.right == null) {
			res.add(op + root.val);
			return;
		}
		if (root.left != null) {
			findPaths(root.left, res, op + root.val + "->");
		}
		if (root.right != null) {
			findPaths(root.right, res, op + root.val + "->");
		}
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();
		findPaths(root, res, "");
		return res;
	}

	// ---------------------------------------------------------------------------------------------------------------------//

	// using StringBuilder
	public List<String> binaryTreePaths1(TreeNode root) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		helper(res, root, sb);
		return res;
	}

	private void helper(List<String> res, TreeNode root, StringBuilder sb) {
		if (root == null) {
			return;
		}
		int len = sb.length();
		sb.append(root.val);
		if (root.left == null && root.right == null) {
			res.add(sb.toString());
		} else {
			sb.append("->");
			helper(res, root.left, sb);
			helper(res, root.right, sb);
		}
		sb.setLength(len);
	}

	public static void main(String[] args) {
		/*
		 * Input: root = [1,2,3,null,5] Output: ["1->2->5","1->3"]
		 */

	}

}
