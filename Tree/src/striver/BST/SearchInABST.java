package striver.BST;

// 700. Search in a Binary Search Tree
// https://www.youtube.com/watch?v=KcNt6v_56cc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=41
// https://leetcode.com/problems/search-in-a-binary-search-tree/submissions/

public class SearchInABST {
	public TreeNode searchBST(TreeNode root, int val) {
		TreeNode temp = root;
		while (temp != null && temp.val != val) {
			temp = val < temp.val ? temp.left : temp.right;
		}
		return temp;
	}
}
