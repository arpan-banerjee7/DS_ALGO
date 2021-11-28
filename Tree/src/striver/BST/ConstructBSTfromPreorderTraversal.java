package striver.BST;

// 1008. Construct Binary Search Tree from Preorder Traversal

// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/discuss/252754/Java-Stack-Iterative-Solution

public class ConstructBSTfromPreorderTraversal {
	int preIndex = 0;

	private TreeNode constructTree(int[] pre, int start, int end) {
		if (start > end) {
			return null;
		}

		TreeNode tNode = new TreeNode(pre[preIndex++]);
		if (start == end) {
			return tNode;
		}

		int index = search(pre, tNode.val, start, end);
		tNode.left = constructTree(pre, start + 1, index - 1);
		tNode.right = constructTree(pre, index, end);

		return tNode;
	}

	private int search(int[] pre, int data, int start, int end) {
		int i;
		for (i = start; i <= end; i++) {
			if (pre[i] > data) {
				return i;
			}
		}
		return i;
	}

	public TreeNode bstFromPreorder(int[] preorder) {
		return constructTree(preorder, 0, preorder.length - 1);
	}
}
