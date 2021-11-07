package graph.algorithims;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// https://youtu.be/V6GxfKDyLBM (TUF)
// https://github.com/striver79/StriversGraphSeries/blob/main/checkCycleDGBFSJava (code TUF)

public class DetectCycleDirectedGraphBFS {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		DetectCycleDirectedGraphBFS ds = new DetectCycleDirectedGraphBFS();
		while (t-- > 0) {
			
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			int V = sc.nextInt();
			int E = sc.nextInt();
			for (int i = 0; i < V + 1; i++)
				list.add(i, new ArrayList<Integer>());
			for (int i = 0; i < E; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				list.get(u).add(v);
			}
			if (ds.isCyclic(V, list) == true)
				System.out.println("1");
			else
				System.out.println("0");
		}
	}

	public boolean isCyclic(int N, ArrayList<ArrayList<Integer>> adj) {
		int indegree[] = new int[N];
		for (int i = 0; i < N; i++) {
			for (Integer it : adj.get(i)) {
				indegree[it]++;
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}
		int cnt = 0;
		while (!q.isEmpty()) {
			Integer node = q.poll();
			cnt++;
			for (Integer it : adj.get(node)) {
				indegree[it]--;
				if (indegree[it] == 0) {
					q.add(it);
				}
			}
		}
		if (cnt == N)
			return false;
		return true;
	}
}