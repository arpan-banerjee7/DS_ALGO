package striver.BT;

import java.util.HashMap;
import java.util.Map;

// Construct BT from Inorder and Preorder-
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// https://www.youtube.com/watch?v=aZNaLrVebKQ&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=35

public class ConstructBTfromPreorderInorder {
	int preIndex = 0;

	private void mapInorder(int[] inorder, Map<Integer, Integer> inMap) {
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}
	}

	private TreeNode buildTree(int[] preorder, int[] inorder, Map<Integer, Integer> inMap, int start, int end) {
		if (start > end) {
			return null;
		}

		// construct the root
		TreeNode temp = new TreeNode(preorder[preIndex++]);

		// if there is only one node then no need to recur further
		if (start == end) {
			return temp;
		}

		// find the index of root in inorder to get the idea of left and right sub trees
		int index = inMap.get(temp.val);

		// recusively form left and right subtees
		temp.left = buildTree(preorder, inorder, inMap, start, index - 1);
		temp.right = buildTree(preorder, inorder, inMap, index + 1, end);

		// at the end return the root
		return temp;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		// build Iorder lookup map
		Map<Integer, Integer> inMap = new HashMap<>();
		mapInorder(inorder, inMap);

		return buildTree(preorder, inorder, inMap, 0, preorder.length - 1);
	}
}
