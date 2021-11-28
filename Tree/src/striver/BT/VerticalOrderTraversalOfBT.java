package striver.BT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

// Striver- https://www.youtube.com/watch?v=q_a6lpbKJdw&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=23
// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/discuss/231148/Java-TreeMap-Solution


// TC -o(nlogn)  spcae -o(n)
class Tuple {
	TreeNode node;
	int col;
	int row;

	public Tuple(TreeNode _node, int _col, int _row) {
		node = _node;
		col = _col;
		row = _row;
	}
}

public class VerticalOrderTraversalOfBT {
	public static List<List<Integer>> verticalTraversal(TreeNode root) {
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
		Queue<Tuple> q = new LinkedList<Tuple>();
		q.offer(new Tuple(root, 0, 0));

		while (!q.isEmpty()) {
			Tuple tuple = q.poll();
			TreeNode node = tuple.node;
			int x = tuple.col;
			int y = tuple.row;

			if (!map.containsKey(x)) {
				map.put(x, new TreeMap<>());
			}

			if (!map.get(x).containsKey(y)) {
				map.get(x).put(y, new PriorityQueue<>());
			}

			map.get(x).get(y).offer(node.val);

			if (node.left != null) {
				q.offer(new Tuple(node.left, x - 1, y + 1));
			}
			if (node.right != null) {
				q.offer(new Tuple(node.right, x + 1, y + 1));
			}
		}

		List<List<Integer>> list = new ArrayList<>();

		for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
			list.add(new ArrayList<>());
			for (PriorityQueue<Integer> nodesPQ : ys.values()) {
				while (!nodesPQ.isEmpty()) {
					list.get(list.size() - 1).add(nodesPQ.poll());
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		node.left = new TreeNode(9);
		node.right = new TreeNode(20);
		node.right.left = new TreeNode(15);
		node.right.right = new TreeNode(7);

		List<List<Integer>> list = verticalTraversal(node);
		System.out.println(Arrays.toString(list.toArray()));// output: [[9], [3, 15], [20], [7]]
	}
}
