package microsoft;

import java.util.LinkedList;

// Prerequisite Tasks (Similar to Question of Modern Park)
// https://practice.geeksforgeeks.org/problems/prerequisite-tasks/1/

public class PrerequisiteTasks {
	
	// check only for cycle
	private boolean checkCycle(LinkedList<Integer>[] adj, int s, boolean[] dfsVis, boolean[] vis) {
		vis[s] = true;
		dfsVis[s] = true;

		for (int it : adj[s]) {
			if (!vis[it]) {
				if (checkCycle(adj, it, dfsVis, vis)) {
					return true;
				}
			} else if (dfsVis[it]) {
				return true;
			}
		}
		dfsVis[s] = false;
		return false;
	}

	public boolean isPossible(int N, int[][] prerequisites) {
		// build adjacency list
		LinkedList<Integer>[] adj = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new LinkedList<Integer>();
		}

		for (int[] preq : prerequisites) {
			adj[preq[1]].add(preq[0]);
		}

		// to check if it is revisite in the same dfs call
		boolean[] dfsVis = new boolean[N];

		// to check if it was visited earlier
		boolean[] vis = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (!vis[i]) {
				if (checkCycle(adj, i, dfsVis, vis)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		/*
		 * Input: N = 4, P = 3 prerequisites = {{1,0},{2,1},{3,2}} Output: Yes
		 * Explanation: To do task 1 you should have completed task 0, and to do task 2
		 * you should have finished task 1, and to do task 3 you should have finished
		 * task 2. So it is possible
		 */
	}
}