package striver.BT;

import java.util.ArrayList;
import java.util.List;

/*
Lowest Common Ancestor-- can be solved using Path from root to a given node but uses extra 
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
space-https://www.youtube.com/watch?v=_-QHfMDde90&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=28
 */
public class LowestCommonAncestorOfBT {

	// Space optimized solution
	// concept
	// if you do not find p and q as left/right child of a node, return null for
	// that node
	// if you find either of p/q return it.
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root.val == p.val || root.val == q.val) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return root;

	}

	//----------------------------------------------------------------------------------------------------//
	
	// brute force- using extra space
	// using the concept of path from root to node
	private boolean pathFromRootToNode1(TreeNode root, TreeNode a, List<TreeNode> path) {
		if (root == null) {
			return false;
		}
		path.add(root);
		if (root.val == a.val) {
			return true;
		}

		if (pathFromRootToNode1(root.left, a, path) || pathFromRootToNode1(root.right, a, path)) {
			return true;
		}

		path.remove(path.size() - 1);
		return false;
	}

	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> path1 = new ArrayList<>();
		List<TreeNode> path2 = new ArrayList<>();
		pathFromRootToNode1(root, p, path1);
		pathFromRootToNode1(root, q, path2);

		int i = 0;
		int j = 0;
		TreeNode ans = null;
		while (i < path1.size() && j < path2.size()) {
			if (path1.get(i).val == path2.get(i).val) {
				ans = path1.get(i);
			} else {
				break;
			}
			i++;
			j++;
		}
		return ans;
	}
}
