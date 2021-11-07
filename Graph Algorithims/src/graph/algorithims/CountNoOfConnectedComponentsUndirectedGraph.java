package graph.algorithims;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
// https://www.geeksforgeeks.org/program-to-count-number-of-connected-components-in-an-undirected-graph/

public class CountNoOfConnectedComponentsUndirectedGraph {
	private int V; // No. of vertices in graph.

	private LinkedList<Integer>[] adj; // Adjacency List
										// representation

	ArrayList<ArrayList<Integer>> components = new ArrayList<>();

	@SuppressWarnings("unchecked")
	CountNoOfConnectedComponentsUndirectedGraph(int v) {
		V = v;
		adj = new LinkedList[v];

		for (int i = 0; i < v; i++)
			adj[i] = new LinkedList();
	}

	void addEdge(int u, int w) {
		adj[u].add(w);
		adj[w].add(u); // Undirected Graph.
	}

	void DFSUtil(int v, boolean[] visited, ArrayList<Integer> al) {
		visited[v] = true;
		al.add(v);
		System.out.print(v + " ");
		Iterator<Integer> it = adj[v].iterator();

		while (it.hasNext()) {
			int n = it.next();
			if (!visited[n])
				DFSUtil(n, visited, al);
		}
	}

	void DFS() {
		boolean[] visited = new boolean[V];

		for (int i = 0; i < V; i++) {
			ArrayList<Integer> al = new ArrayList<>();
			if (!visited[i]) {
				DFSUtil(i, visited, al);
				components.add(al);
			}
		}
	}

	int ConnecetedComponents() {
		return components.size();
	}

	public static void main(String[] args) {
		CountNoOfConnectedComponentsUndirectedGraph g = new CountNoOfConnectedComponentsUndirectedGraph(6);

		g.addEdge(1, 5);
		g.addEdge(0, 2);
		g.addEdge(2, 4);
		System.out.println("Graph DFS:");
		g.DFS();
		System.out.println("\nNumber of Conneceted Components: " + g.ConnecetedComponents());
	}
}
