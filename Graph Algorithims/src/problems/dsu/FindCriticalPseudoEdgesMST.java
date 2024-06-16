package graph.dsu;
import java.util.*;

// https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/?envType=problem-list-v2&envId=p4wxv0w3
// https://www.youtube.com/watch?v=zl-rLRPpl_s
public class FindCriticalPseudoEdgesMST {
    static class UnionFind {
        int size; // count of disjoint sets
        private int[] parents;

        UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            size = n;
        }

        int find(int i) {
            if (parents[i] == i) {
                return i;
            }
            int root = find(parents[i]);
            parents[i] = root;
            return root;
        }

        boolean union(int i, int j) {
            int root_i = find(i);
            int root_j = find(j);

            if (root_i != root_j) {
                parents[root_i] = root_j;
                size--;
                return true;
            }
            return false;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        // mentioned in the question to return original index of the edges
        Map<int[], Integer> edgeIndexMap = new HashMap<>(); //0(e)
        for (int i = 0; i < edges.length; i++) {
            edgeIndexMap.put(edges[i], i);
        }

        Arrays.sort(edges, (e1, e2) -> e1[2] - e2[2]); //0(elog(e))
        int minCost = MST(n, edges, null, null); //o(e)

        for (int[] edge : edges) { //o(e^2)
            int excludeCost = MST(n, edges, null, edge);
            if (excludeCost > minCost) {
                critical.add(edgeIndexMap.get(edge));
            } else {
                int includeCost = MST(n, edges, edge, null);
                if (includeCost == minCost) {
                    pseudo.add(edgeIndexMap.get(edge));
                }
            }
        }
        return Arrays.asList(critical, pseudo);
    }

    private int MST(int n, int[][] edges, int[] include, int[] exclude) { //o(e)
        UnionFind uf = new UnionFind(n);
        int cost = 0;

        if (include != null) {
            uf.union(include[0], include[1]);
            cost += include[2];
        }
        for (int[] edge : edges) { //o(e)
            if (exclude != edge && uf.union(edge[0], edge[1])) {
                cost += edge[2];
            }
        }
        return uf.size == 1 ? cost : Integer.MAX_VALUE;
    }
    /*
    Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
    Output: [[0,1],[2,3,4,5]]
    Explanation: The figure above describes the graph.
     */
}
