package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
//https://www.youtube.com/watch?v=q_a6lpbKJdw

// TC -o(nlogn)
// spcae -o(n)
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

class VerticalOrderTraversalBT {
	public List<List<Integer>> verticalTraversal(TreeNode root) {
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

}

 class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
