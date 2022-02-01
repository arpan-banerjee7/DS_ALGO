package striver.BST;

import java.util.Stack;

// 230. Kth Smallest Element in a BST
/*
Kth smallest element in BSt- to fo follow up

https://www.youtube.com/watch?v=9TJYWh0adfk&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=46
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

If the BST is modified often (i.e., we we can do insert and delete operations) and you need to find the kth
smallest frequently, how would you optimize?
 */

// make use of the property that inorder traversal of tree is always sorted
public class KthSmallestLargestInBST {
	class Solution {
		int kthSmallest(TreeNode root, int k) {
			Stack<TreeNode> st = new Stack<>();
			TreeNode node = root;
			int cnt = 0;
			while (true) {
				if (node != null) {
					st.push(node);
					node = node.left;
				} else {

					if (st.empty() == true)
						break;
					node = st.peek();
					st.pop();
					// inorder.push_back(node->val);
					cnt++;
					if (cnt == k)
						return node.val;
					node = node.right;
				}
			}
			return -1;
		}
	};
}
