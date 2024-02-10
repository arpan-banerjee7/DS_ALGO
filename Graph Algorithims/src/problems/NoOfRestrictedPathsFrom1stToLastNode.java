package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 1786. Number of Restricted Paths From First to Last Node
// https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/description/?envType=list&envId=53js48ke

// Djikstra + DFS
public class NoOfRestrictedPathsFrom1stToLastNode {
    class Node {
        int u;
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private int mod = 1_000_000_007;

    private int dfs(List<Node>[] adj, int[] dist, int start, int end, Integer[] dp) {
        if (start == end) {
            return 1;
        }
        if (dp[start] != null) return dp[start];
        long count = 0;
        for (Node it : adj[start]) {
            if (dist[start] > dist[it.v]) {
                count = count + (dfs(adj, dist, it.v, end, dp) % mod);
            }
        }
        count = (count % mod);
        return dp[start] = (int) count;
    }

    private void runDijkstra(List<Node>[] adj, int[] dist, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        dist[n] = 0;
        pq.add(new Node(n, 0));
        while (!pq.isEmpty()) {
            Node parent = pq.poll();
            for (Node it : adj[parent.v]) {
                if (parent.w + it.w < dist[it.v]) {
                    dist[it.v] = parent.w + it.w;
                    pq.add(new Node(it.v, parent.w + it.w));
                }
            }
        }

    }

    public int countRestrictedPaths(int n, int[][] edges) {
        List<Node>[] adj = new ArrayList[n + 1];
        int[] dist = new int[n + 1];
        Integer[] dp = new Integer[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<Node>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(new Node(edge[1], edge[2]));
            adj[edge[1]].add(new Node(edge[0], edge[2]));
        }

        runDijkstra(adj, dist, n);
        return dfs(adj, dist, 1, n, dp);
    }
}

