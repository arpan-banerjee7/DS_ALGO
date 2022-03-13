package graph.algorithims;

import java.util.LinkedList;

// https://www.youtube.com/watch?v=64KK9K4RpKE Techdose
// https://www.programiz.com/dsa/strongly-connected-components

public class FindArticulationPointsUsingTarjansAlgo {

	static int time = 0;

	private static void DFS(LinkedList<Integer>[] adj, int u, int[] disc, int[] low, int[] parent,
			boolean[] articulationPoint) {

		disc[u] = low[u] = time;
		time += 1;
		int children = 0;

		for (int v : adj[u]) {
			if (disc[v] == -1) // If v is not visited
			{
				children += 1;
				parent[v] = u;
				DFS(adj, v, disc, low, parent, articulationPoint);
				low[u] = Math.min(low[u], low[v]);
				if (parent[u] == -1 && children > 1) // Case-1: U is root
					articulationPoint[u] = true;

				if ((parent[u] != -1) && (low[v] >= disc[u])) // Case-2: Atleast 1 component will get separated
					articulationPoint[u] = true;
			} else if (v != parent[u]) // Ignore child to parent edge
				low[u] = Math.min(low[u], disc[v]);
		}

	}

	private static void findAPsTarjan(LinkedList<Integer>[] adj, int noOfVertices) {
		int[] disc = new int[noOfVertices]; // will never change
		int[] low = new int[noOfVertices]; // updated while backtracking
		int[] parent = new int[noOfVertices];

		for (int i = 0; i < noOfVertices; i++) {
			disc[i] = -1;
			low[i] = -1;
			parent[i] = -1;
		}

		boolean[] articulationPoint = new boolean[noOfVertices];
		for (int i = 0; i < noOfVertices; ++i)
			if (disc[i] == -1)
				DFS(adj, i, disc, low, parent, articulationPoint);

		System.out.println("Articulation Points are: ");
		for (int i = 0; i < noOfVertices; ++i)
			if (articulationPoint[i] == true)
				System.out.print(i + " ");
	}

	public static void main(String[] args) {
		// building the adjacency list harcoded
		int noOfVertices = 5;
		LinkedList<Integer>[] adj = new LinkedList[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			adj[i] = new LinkedList<Integer>();
		}
		adj[0].add(1);
		adj[1].add(0);

		adj[0].add(2);
		adj[2].add(0);

		adj[1].add(2);
		adj[2].add(1);

		adj[1].add(3);
		adj[3].add(1);

		adj[2].add(3);
		adj[3].add(2);

		adj[1].add(4);
		adj[4].add(1);

//        adj[0].add(2);
//        adj[2].add(0);
//        adj[0].add(3);
//        adj[3].add(0);
//        adj[1].add(0);
//        adj[0].add(1);
//        adj[2].add(1);
//        // adj[2].pb(4);
//        // adj[4].pb(2);
//        adj[1].add(2);
//        adj[3].add(4);
//        adj[4].add(3);
		findAPsTarjan(adj, noOfVertices);

	}

}
