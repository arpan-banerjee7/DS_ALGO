package striver;

import java.util.ArrayList;
import java.util.List;

// https://www.youtube.com/watch?v=RlUu72JrOCQ&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=6 Take u forward
// 144. Binary Tree Preorder Traversal
public class PreOrderTraversalRecursive {
	TreeNode root;

	private void preorderRecursive(TreeNode node, List<Integer> preorder) {
		if (node == null)
			return;

		preorder.add(node.val);
		preorderRecursive(node.left, preorder);
		preorderRecursive(node.right, preorder);
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> preorder = new ArrayList<Integer>();
		preorderRecursive(root, preorder);
		return preorder;
	}

	public static void main(String[] args) {
		PreOrderTraversalRecursive tree = new PreOrderTraversalRecursive();
		tree.root = new TreeNode(10);
		tree.root.left = new TreeNode(20);
		tree.root.right = new TreeNode(30);
		tree.root.left.left = new TreeNode(40);
		tree.root.left.left.left = new TreeNode(70);
		tree.root.left.right = new TreeNode(50);
		tree.root.right.left = new TreeNode(60);
		tree.root.left.left.right = new TreeNode(80);

		List<Integer> preorder = tree.preorderTraversal(tree.root);
		preorder.forEach(e -> System.out.print(e + " "));// Output: 10 20 40 70 80 50 30 60
	}

}