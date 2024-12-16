package linkedin;

import java.util.*;

public class PossibleBipartition {
    private boolean dfs(int parent, List<Integer> adj[], Integer[] color) {
        if (color[parent] == null) {
            color[parent] = 1;
        }
        for (int child : adj[parent]) {
            if (color[child] == null) {
                color[child] = 1 - color[parent];
                if (!dfs(child, adj, color)) {
                    return false;
                }
            } else if (color[child] == color[parent]) {
                return false;
            }
        }
        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // build adjacency list
        List<Integer> adj[] = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] dis : dislikes) {
            adj[dis[0]].add(dis[1]);
            adj[dis[1]].add(dis[0]);
        }

        Integer[] colors = new Integer[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] == null && !dfs(i, adj, colors)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 4}};
        PossibleBipartition pb = new PossibleBipartition();
        System.out.println(pb.possibleBipartition(n, dislikes));
    }
}
