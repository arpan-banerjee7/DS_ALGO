package graph.algorithims;

import java.util.Arrays;
import java.util.LinkedList;

// 1042. Flower Planting With No Adjacent

// https://leetcode.com/problems/flower-planting-with-no-adjacent/
// https://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
// https://www.interviewcake.com/question/java/graph-coloring
// https://www.techiedelight.com/greedy-coloring-graph/

public class GraphColoring {
	public int[] gardenNoAdj(int n, int[][] paths) {
		// build the graph
		LinkedList<Integer>[] adj = new LinkedList[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new LinkedList<Integer>();
		}
		for (int[] path : paths) {
			adj[path[0]].add(path[1]);
			adj[path[1]].add(path[0]);
		}

		int[] colors = new int[n + 1];
		boolean[] available = new boolean[n + 1];
		Arrays.fill(available, true);
		colors[1] = 1;

		for (int u = 2; u <= n; u++) {
			for (int it : adj[u]) {
				// if adjacent nodes are already colored,mark those colors as unavailable
				if (colors[it] != 0) {
					available[colors[it]] = false;
				}
			}

			// find the next available color and assing it to the current vertex
			int cr = 1;
			for (cr = 1; cr <= n; cr++) {
				if (available[cr]) {
					break;
				}
			}

			colors[u] = cr;

			// reset available vertices for next iteration
			Arrays.fill(available, true);
		}
		int[] res = new int[n];
		int resIdx = 0;
		for (int i = 1; i < n + 1; i++) {
			res[resIdx++] = colors[i];
		}
		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: n = 3, paths = [[1,2],[2,3],[3,1]] Output: [1,2,3] 
		 * Explanation:
		 * Gardens 1 and 2 have different types. Gardens 2 and 3 have different types.
		 * Gardens 3 and 1 have different types. Hence, [1,2,3] is a valid answer. Other
		 * valid answers include [1,2,4], [1,4,2], and [3,2,1].
		 */
	}

}
