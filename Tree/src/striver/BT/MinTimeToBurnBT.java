package striver.BT;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
Min time to burn the binary tree-
https://www.youtube.com/watch?v=2r5wLmQfD6g&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=32
https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
https://www.interviewbit.com/problems/burn-a-tree/
https://www.codingninjas.com/codestudio/problems/time-to-burn-tree_630563
 */

public class MinTimeToBurnBT {
	private static TreeNode bfsToMapParents(TreeNode root, HashMap<TreeNode, TreeNode> mpp, int start) {
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		TreeNode res = null;
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node.val == start)
				res = node;
			if (node.left != null) {
				mpp.put(node.left, node);
				q.offer(node.left);
			}
			if (node.right != null) {
				mpp.put(node.right, node);
				q.offer(node.right);
			}
		}
		return res;
	}

	private static TreeNode findStartNode(TreeNode node, int start) {
		if (node == null) {
			return null;
		}
		if (node.val == start) {
			return node;
		}
		TreeNode left = findStartNode(node.left, start);
		TreeNode right = findStartNode(node.right, start);
		if (left == null && right == null) {
			return null;
		}
		if (right == null && left.val == start) {
			return left;
		}
		if (left == null && right.val == start) {
			return right;
		}
		return null;

	}

	private static int findMaxDistance(HashMap<TreeNode, TreeNode> mpp, TreeNode target) {
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(target);
		HashMap<TreeNode, Boolean> vis = new HashMap<>();
		vis.put(target, true);
		int maxi = 0;

		while (!q.isEmpty()) {
			int sz = q.size();

			for (int i = 0; i < sz; i++) {
				TreeNode node = q.poll();
				if (node.left != null && vis.get(node.left) == null) {

					vis.put(node.left, true);
					q.offer(node.left);
				}
				if (node.right != null && vis.get(node.right) == null) {

					vis.put(node.right, true);
					q.offer(node.right);
				}

				if (mpp.get(node) != null && vis.get(mpp.get(node)) == null) {

					vis.put(mpp.get(node), true);
					q.offer(mpp.get(node));
				}
			}
			maxi++;
		}
		// leaves can t burn, we are just doing on extra traversal , tryion to insert
		// nulls
		return maxi - 1;
	}

	public int solve(TreeNode A, int B) {
		HashMap<TreeNode, TreeNode> mpp = new HashMap<>();
		bfsToMapParents(A, mpp, B);
		TreeNode target = findStartNode(A, B);
		int maxi = findMaxDistance(mpp, target);
		return maxi;
	}
}
