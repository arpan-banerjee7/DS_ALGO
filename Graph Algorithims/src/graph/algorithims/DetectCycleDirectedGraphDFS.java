package graph.algorithims;

import java.util.ArrayList;
import java.util.Scanner;
// https://youtu.be/uzVUw90ZFIg (TUF)
// https://github.com/striver79/StriversGraphSeries/blob/main/cycleCheckDGDfsJava (code TUF)

public class DetectCycleDirectedGraphDFS {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		while (t-- > 0) {
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			DetectCycleDirectedGraphDFS obj = new DetectCycleDirectedGraphDFS();
			int V = sc.nextInt();
			int E = sc.nextInt();
			for (int i = 0; i < V + 1; i++)
				list.add(i, new ArrayList<Integer>());
			for (int i = 0; i < E; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				list.get(u).add(v);
			}
			if (obj.isCyclic(V, list) == true)
				System.out.println("1");
			else
				System.out.println("0");
		}
	}

	private boolean checkCycle(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int dfsVis[]) {
		vis[node] = 1;
		dfsVis[node] = 1;

		for (Integer it : adj.get(node)) {
			if (vis[it] == 0) {
				if (checkCycle(it, adj, vis, dfsVis) == true) {
					return true;
				}
			} else if (dfsVis[it] == 1) {
				return true;
			}
		}
		dfsVis[node] = 0;
		return false;
	}

	public boolean isCyclic(int N, ArrayList<ArrayList<Integer>> adj) {
		int vis[] = new int[N];
		int dfsVis[] = new int[N];

		for (int i = 0; i < N; i++) {
			if (vis[i] == 0) {
				if (checkCycle(i, adj, vis, dfsVis) == true)
					return true;
			}
		}
		return false;
	}
}