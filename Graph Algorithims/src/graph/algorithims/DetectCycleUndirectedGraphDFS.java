package graph.algorithims;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://youtu.be/Y9NFqI6Pzd4 (TUF)
public class DetectCycleUndirectedGraphDFS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		while (T-- > 0) {
			String[] s = br.readLine().trim().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
			for (int i = 0; i < V; i++)
				adj.add(i, new ArrayList<Integer>());
			for (int i = 0; i < E; i++) {
				String[] S = br.readLine().trim().split(" ");
				int u = Integer.parseInt(S[0]);
				int v = Integer.parseInt(S[1]);
				adj.get(u).add(v);
				adj.get(v).add(u);
			}
			Solution obj = new Solution();
			boolean ans = obj.isCycle(V, adj);
			if (ans)
				System.out.println("1");
			else
				System.out.println("0");
		}
	}
// Driver Code Ends

	public boolean checkForCycle(int node, int parent, boolean vis[], ArrayList<ArrayList<Integer>> adj) {
		vis[node] = true;
		for (Integer it : adj.get(node)) {
			if (vis[it] == false) {
				if (checkForCycle(it, node, vis, adj) == true)
					return true;
			} else if (it != parent)
				return true;
		}

		return false;
	}

	// 0-based indexing Graph
	public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean vis[] = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (vis[i] == false) {
				if (checkForCycle(i, -1, vis, adj))
					return true;
			}
		}

		return false;
	}
}