package problems;

import java.util.ArrayList;

import striver.BT.TreeNode;

// Path to Given Node
// https://www.interviewbit.com/problems/path-to-given-node/
// https://takeuforward.org/data-structure/print-root-to-node-path-in-a-binary-tree/

public class PrintPathsFromRootToGivenNode {

	private int findPath(TreeNode root, int target, ArrayList<Integer> res) {
		if (root == null)
			return 0;
		if (root.val == target) {
			res.add(root.val);
			return root.val;
		}

		int left = findPath(root.left, target, res);
		int right = findPath(root.right, target, res);
		if (left == 0 && right == 0)
			return 0;

		res.add(root.val);
		return root.val;
	}

	// another method
	private int findPath1(TreeNode root, int target, ArrayList<Integer> res) {
		if (root == null)
			return 0;
		if (root.val == target) {
			res.add(root.val);
			return root.val;
		}

		int left = findPath(root.left, target, res);
		int right = findPath(root.right, target, res);
		if (left == 0 && right == 0)
			return 0;

		res.add(root.val);
		return root.val;
	}

	public ArrayList<Integer> solve(TreeNode A, int B) {
		ArrayList<Integer> res = new ArrayList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		findPath(A, B, res);
		for (int i = res.size() - 1; i >= 0; i--) {
			ans.add(res.get(i));
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
