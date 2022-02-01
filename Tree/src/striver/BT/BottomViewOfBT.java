package striver.BT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Bottom View Of Binary Tree- Cut down TC from o(nlogn) to O(n) same as top view, just keep updating the map
// https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1

public class BottomViewOfBT {
	class Tuple {
		Node node;
		int v;

		Tuple(int v, Node node) {
			this.v = v;
			this.node = node;
		}
	}

	class Solution {
		// Function to return a list containing the bottom view of the given tree.
		public ArrayList<Integer> bottomView(Node root) {
			// Code here
			ArrayList<Integer> res = new ArrayList<>();
			Queue<Tuple> queue = new LinkedList<>();
			Map<Integer, Integer> map = new HashMap<>();

			if (root != null) {
				queue.add(new Tuple(0, root));
			}
			// to reduce the TC from o(nlogn) to o(n) avoid using treemap
			int min = Integer.MAX_VALUE;

			while (!queue.isEmpty()) {
				Tuple tuple = queue.poll();
				Node node = tuple.node;
				int v = tuple.v;

				min = Math.min(v, min);

				map.put(v, node.data);

				if (node.left != null) {
					queue.add(new Tuple(v - 1, node.left));
				}
				if (node.right != null) {
					queue.add(new Tuple(v + 1, node.right));
				}
			}

			for (int i = min; map.containsKey(i); i++) {
				res.add(map.get(i));
			}
			return res;
		}
	}
}
