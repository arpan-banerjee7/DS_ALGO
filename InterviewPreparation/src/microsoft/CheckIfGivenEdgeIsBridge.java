package microsoft;

import java.util.ArrayList;

// https://practice.geeksforgeeks.org/problems/bridge-edge-in-graph/1#

public class CheckIfGivenEdgeIsBridge {

	private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
		vis[node] = true;
		for (int it : adj.get(node)) {
			if (!vis[it]) {
				dfs(it, adj, vis);
			}
		}
	}

	// Function to find if the given edge is a bridge in graph.
	static int isBridge(int V, ArrayList<ArrayList<Integer>> adj, int c, int d) {
		// remove the given edge
		adj.get(c).remove(d);
		adj.get(d).remove(c);

		boolean vis[] = new boolean[V];
		dfs(c, adj, vis);

		return vis[d] ? 0 : 1;
	}
}