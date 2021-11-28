package striver.BT;

import java.util.ArrayList;

/*
Path from root to a given node- https://www.youtube.com/watch?v=fmflMqVOC7k&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=28
https://www.interviewbit.com/problems/path-to-given-node/
https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/
 */

public class PathFromRootToGivenNode {
	private boolean preorderSearch(TreeNode node, int b, ArrayList<Integer> res) {
		if (node == null) {
			return false;
		}

		res.add(node.val);
		// as soon as u find the node return true
		if (node.val == b) {
			return true;
		}
		boolean left = preorderSearch(node.left, b, res);
		boolean right = preorderSearch(node.right, b, res);
		if (left || right) {
			return true;
		}

		res.remove(res.size() - 1);
		return false;
	}

	public ArrayList<Integer> solve(TreeNode A, int B) {
		ArrayList<Integer> res = new ArrayList<>();
		preorderSearch(A, B, res);
		return res;

	}
}
