package striver.BT;

import java.util.HashMap;
import java.util.Map;

// 106. Construct Binary Tree from Inorder and Postorder Traversal
// Consrtuct Bt from inorder and postorder-https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// https://www.youtube.com/watch?v=s5XRtcud35E
	
public class ConstructBTFromInorderPostorderTraversal {
	int preIndex = 0;

	private void mapInorder(int[] inorder, Map<Integer, Integer> inMap) {
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}
	}

	private TreeNode buildTree(int[] postorder, int[] inorder, Map<Integer, Integer> inMap, int start, int end) {
		if (start > end) {
			return null;
		}

		// construct the root
		TreeNode temp = new TreeNode(postorder[preIndex--]);

		// if there is only one node then no need to recur further
		if (start == end) {
			return temp;
		}

		// find the index of root in inorder to get the idea of left and right sub trees
		int index = inMap.get(temp.val);

		// recusively form left and right subtees
		temp.right = buildTree(postorder, inorder, inMap, index + 1, end);
		temp.left = buildTree(postorder, inorder, inMap, start, index - 1);

		// at the end return the root
		return temp;
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		// build Iorder lookup map
		Map<Integer, Integer> inMap = new HashMap<>();
		mapInorder(inorder, inMap);
		preIndex = postorder.length - 1;
		return buildTree(postorder, inorder, inMap, 0, postorder.length - 1);
	}
}
