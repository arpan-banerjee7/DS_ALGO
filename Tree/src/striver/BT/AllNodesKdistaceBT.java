package striver.BT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 863. All Nodes Distance K in Binary Tree
/*
Nodes at k distance- 
https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1 to down
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/submissions/
https://www.youtube.com/watch?v=i9ORlEy6EsI&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=31
 */

//TC: O(n) + O(n)    SC: O(n) + O(n) + O(n)
public class AllNodesKdistaceBT {
	private void setParentBFS(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
		Queue<TreeNode> queue = new LinkedList<>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node.left != null) {
				parentMap.put(node.left, node);
				queue.add(node.left);
			}
			if (node.right != null) {
				parentMap.put(node.right, node);
				queue.add(node.right);
			}

		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		// create parent pointers
		Map<TreeNode, TreeNode> parentMap = new HashMap<>();
		setParentBFS(root, parentMap);

		// BFS(radially traverse in all 3 directions with setp=1)
		Queue<TreeNode> queue = new LinkedList<>();
		Map<TreeNode, Boolean> visited = new HashMap<>();
		int currLevel = 0;
		if (target != null) {
			queue.add(target);
			visited.put(target, true);
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			if (currLevel == k) {
				break;
			}

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null && visited.get(node.left) == null) {
					visited.put(node.left, true);
					queue.add(node.left);
				}
				if (node.right != null && visited.get(node.right) == null) {
					visited.put(node.right, true);
					queue.add(node.right);
				}
				if (parentMap.containsKey(node) && visited.get(parentMap.get(node)) == null) {
					visited.put(parentMap.get(node), true);
					queue.add(parentMap.get(node));
				}

			}
			currLevel++;
		}
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			res.add(queue.poll().val);
		}
		return res;
	}
}
