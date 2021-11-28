package striver.BT;

import java.util.LinkedList;
import java.util.Queue;

/*
Max width of a binary tree- cool trick to prevent overflow- https://leetcode.com/problems/maximum-width-of-binary-tree/
https://www.youtube.com/watch?v=ZbybYvcVLks&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=29
 */

public class MaxWidthOfBT {
	static class Tuple {
		TreeNode node;
		int index;

		Tuple(TreeNode node, int index) {
			this.node = node;
			this.index = index;
		}
	}

	public int widthOfBinaryTree(TreeNode root) {
		int maxCount = 0;
		Queue<Tuple> queue = new LinkedList<>();
		if (root != null) {
			queue.add(new Tuple(root, 0));
		}

		while (!queue.isEmpty()) {
			int size = queue.size();
			int toSubstract = queue.peek().index;
			int max = 0;
			int min = 0;
			for (int i = 0; i < size; i++) {
				Tuple tuple = queue.poll();
				TreeNode node = tuple.node;
				int index = tuple.index - toSubstract;

				if (i == 0) {
					min = index;
				}

				if (i == (size - 1)) {
					max = index;
				}

				if (node.left != null) {
					queue.add(new Tuple(node.left, 2 * index + 1));
				}
				if (node.right != null) {
					queue.add(new Tuple(node.right, 2 * index + 2));
				}
			}
			maxCount = Math.max(max - min + 1, maxCount);
		}
		return maxCount;
	}
}
