package problems;

import java.util.*;

// Graph Valid Tree
// https://www.codingninjas.com/studio/problems/graph-valid-tree_1376618?leftPanelTabValue=PROBLEM
// Dfs, checkCycle in undirected graph, find number of connected components
// There is no cycle and the graph is connected, hence it is a tree.
public class GraphValidTree {
    private static boolean checkCycle(List<Integer> adj[], int prev, int parent, boolean[] vis) {
        vis[parent] = true;
        for (int child : adj[parent]) {
            if (!vis[child]) {
                if (checkCycle(adj, parent, child, vis)) {
                    return true;
                }
            } else if (prev != child) {
                return true;
            }
        }
        return false;
    }

    public static Boolean checkGraph(int[][] edges, int n, int m) {
        // Write your code here
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        boolean[] vis = new boolean[n];
        int prev = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                count++;
                if (checkCycle(adj, prev, i, vis)) {
                    return false;
                }
            }
        }
        return count == 1;
    }
}
