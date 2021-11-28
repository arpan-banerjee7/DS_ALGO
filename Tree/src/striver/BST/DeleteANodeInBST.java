package striver.BST;

// https://www.youtube.com/watch?v=kouxiP_H5WE&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=45
// https://leetcode.com/problems/delete-node-in-a-bst/submissions/
public class DeleteANodeInBST {
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.val == key) {
			return helper(root);
		}
		TreeNode dummy = root;
		while (root != null) {
			if (root.val > key) {
				if (root.left != null && root.left.val == key) {
					root.left = helper(root.left);
					break;
				} else {
					root = root.left;
				}
			} else {
				if (root.right != null && root.right.val == key) {
					root.right = helper(root.right);
					break;
				} else {
					root = root.right;
				}
			}
		}
		return dummy;
	}

	public TreeNode helper(TreeNode root) {
		if (root.left == null) {
			return root.right;
		} else if (root.right == null) {
			return root.left;
		} else {
			TreeNode rightChild = root.right;
			TreeNode lastRight = findLastRight(root.left);
			lastRight.right = rightChild;
			return root.left;
		}
	}

	public TreeNode findLastRight(TreeNode root) {
		if (root.right == null) {
			return root;
		}
		return findLastRight(root.right);
	}
}
