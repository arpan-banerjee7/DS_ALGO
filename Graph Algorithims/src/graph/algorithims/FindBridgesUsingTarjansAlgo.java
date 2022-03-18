package graph.algorithims;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://www.youtube.com/watch?v=Rhxs4k6DyMM&list=PLEJXowNB4kPzByLnnFYNSCoqtFz0VKLk5&index=29  techdose

public class FindBridgesUsingTarjansAlgo {

	static int time = 0;

	private static void DFS(LinkedList<Integer>[] adj, int u, int[] disc, int[] low, int[] parent,
			List<int[]> bridges) {

		disc[u] = low[u] = time;
		time += 1;

		for (int v : adj[u]) {
			if (disc[v] == -1) // If v is not visited
			{
				parent[v] = u;
				DFS(adj, v, disc, low, parent, bridges);
				low[u] = Math.min(low[u], low[v]);

				if (low[v] > disc[u])
					bridges.add(new int[] { u, v });
			} else if (v != parent[u]) // Ignore child to parent edge
				low[u] = Math.min(low[u], disc[v]);
		}

	}

	private static void findBridgesTarjan(LinkedList<Integer>[] adj, int noOfVertices) {
		int[] disc = new int[noOfVertices]; // will never change
		int[] low = new int[noOfVertices]; // updated while backtracking
		int[] parent = new int[noOfVertices];

		for (int i = 0; i < noOfVertices; i++) {
			disc[i] = -1;
			low[i] = -1;
			parent[i] = -1;
		}

		List<int[]> bridges = new ArrayList<>();
		for (int i = 0; i < noOfVertices; ++i)
			if (disc[i] == -1)
				DFS(adj, i, disc, low, parent, bridges);

		System.out.println("Bridges are: ");
		for (int[] bridge : bridges) {
			System.out.println(bridge[0] + " " + bridge[1]);
		}
	}

	public static void main(String[] args) {
		// building the adjacency list harcoded
		int noOfVertices = 5;
		LinkedList<Integer>[] adj = new LinkedList[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			adj[i] = new LinkedList<Integer>();
		}
		adj[0].add(2);
		adj[2].add(0);
		adj[0].add(3);
		adj[3].add(0);
		adj[1].add(0);
		adj[0].add(1);
		adj[2].add(1);
		// adj[2].pb(4);
		// adj[4].pb(2);
		adj[1].add(2);
		adj[3].add(4);
		adj[4].add(3);
		findBridgesTarjan(adj, noOfVertices);
	}

}
