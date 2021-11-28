package striver.BT;

import java.util.LinkedList;
import java.util.Queue;

// 297. Serialize and Deserialize Binary Tree
// https://www.youtube.com/watch?v=-YbXySKJsX8&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=37
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// same as level order traversal

public class SerializeDeseriallizeABT {
	// using level order traversal/ insert null values as well coded as n

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder res = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null) {
				res.append("n ");
				continue;
			}
			res.append(node.val + " ");
			queue.add(node.left);
			queue.add(node.right);
		}
		return res.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<TreeNode> queue = new LinkedList<>();
		if (data.isEmpty())
			return null;

		// split the node values
		String[] values = data.split(" ");

		// take the first value and put it into the queue
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		queue.add(root);
		for (int i = 1; i < values.length; i++) {
			TreeNode node = queue.poll();
			if (!values[i].equals("n")) {
				TreeNode leftChild = new TreeNode(Integer.parseInt(values[i]));
				node.left = leftChild;
				queue.add(leftChild);
			}
			if (!values[++i].equals("n")) {
				TreeNode rightChild = new TreeNode(Integer.parseInt(values[i]));
				node.right = rightChild;
				queue.add(rightChild);
			}
		}
		return root;
	}
}

//Your Codec object will be instantiated and called as such:
//Codec ser = new Codec();
//Codec deser = new Codec();
//TreeNode ans = deser.deserialize(ser.serialize(root));
