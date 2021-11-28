package striver.BT;

import java.util.Stack;

// 114. Flatten Binary Tree to Linked List

// Ref: https://www.youtube.com/watch?v=sWf7k1x9XR4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=39 Take u forward
// 3 ways check leetcode notes

// TC - O(N) 
// SC - O(N)

/*
1. Recursion --> right then left connect while backtracking
2. using stack-> same concept
3. using Morris inorder traversal- dry run and check what we need to do, connect prev.right to curr.right
 */
public class FlattenBinaryTreetoLinkedList {
	// using Morris Traversal for inorder
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode curr = root;
		while (curr != null) {
			if (curr.left == null) {// always have to move right
				curr = curr.right;
			} else {
				TreeNode prev = curr.left;
				while (prev.right != null) {
					prev = prev.right;
				}

				prev.right = curr.right;
				curr.right = curr.left;
				curr.left = null;
				curr = curr.right;
			}
		}
	}

	// iterative using stack- push right first then left and try to do a dry runa nd
	// map
	// the solution accordingly
	public void flatten1(TreeNode root) {
		Stack<TreeNode> st = new Stack<>();
		if (root != null) {
			st.push(root);
		}
		while (!st.isEmpty()) {
			TreeNode node = st.pop();
			if (node.right != null) {
				st.push(node.right);
			}
			if (node.left != null) {
				st.push(node.left);
			}

			if (!st.isEmpty()) {// to avoid emptystackexception
				node.right = st.peek();
				node.left = null;
			}
		}
	}

	// recursive solution // NRL reverse preoder, connect while backtracking
	TreeNode prev = null;

	private void helper(TreeNode root) {
		if (root == null) {
			return;
		}

		helper(root.right);
		helper(root.left);

		root.right = prev;
		root.left = null;
		prev = root;
	}

	public void flatten2(TreeNode root) {
		helper(root);
	}
}
