package problems;

import java.util.LinkedList;

// 207. Course Schedule
// https://leetcode.com/problems/course-schedule/

public class CourseSchedule {
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

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		// build adjacency list
		LinkedList<Integer>[] adj = new LinkedList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			adj[i] = new LinkedList<Integer>();
		}

		for (int[] preq : prerequisites) {
			adj[preq[1]].add(preq[0]);
		}

		// to check if it is revisite in the same dfs call
		boolean[] dfsVis = new boolean[numCourses];

		// to check if it was visited earlier
		boolean[] vis = new boolean[numCourses];

		for (int i = 0; i < numCourses; i++) {
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
		 * Input: numCourses = 2, prerequisites = [[1,0]] Output: true Explanation:
		 * There are a total of 2 courses to take. To take course 1 you should have
		 * finished course 0. So it is possible.
		 */
	}

}
