package graph.dsu;
import java.util.*;
// https://leetcode.com/problems/count-the-number-of-complete-components/description/?envType=problem-list-v2&envId=p4wxv0w3
public class CountNumberCompleteComponents {
    private void dfs(List<List<Integer>> adj, boolean[] visited, int u, Set<Integer> vertices) {
        visited[u] = true;
        vertices.add(u);

        for (int v: adj.get(u)) {
            if (!visited[v]) {
                dfs(adj, visited, v, vertices);
            }
        }
    }

    private boolean isSubGraphComplete(List<List<Integer>> adj, Set<Integer> vertices) {
        int n = vertices.size();
        int countEdges = 0;
        for (int v : vertices) {
            countEdges += adj.get(v).size();
        }

        int edges = countEdges/2;
        return (edges == n * (n-1) / 2);
    }

    public int countCompleteComponents(int n, int[][] edges) {
        // Create adj list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; ++i) {
            adj.add(new ArrayList<Integer>());
        }

        for (int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Count number of connected components using dfs/bfs (dfs in this case)
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i=0; i<n; ++i) {
            Set<Integer> verticesList = new HashSet<>();
            if (!visited[i]) {
                dfs(adj, visited, i, verticesList);

                // increase the component count only if this component is complete
                if (isSubGraphComplete(adj, verticesList)) {
                    count++;
                }
            }
        }

        return count;
    }


    /**
     A connected component is complete if and only if each node of the component has an edges list that
     contains exactly the other nodes in that component.

     If a 'self-edge" (ie, [k,k]) is added to the edges list of each node, then the above becomes:

     A connected component is complete if and only if each node of the component has exactly the same list of edges,
     and the number of edges in that list is equal to the number of nodes.

     1. Create the graph from the edges and add the self node to the adjacency list/graph.
     2. Sort the list of edges for each node and keep a count of how many exact same edges were found.
     3. If the number of edges found = size of the list of edges, then add 1 to the ans.

     Step-1
     0- [0,1,2]
     1- [0,1,2]
     2- [0,1,2]
     3- [3,4]
     4- [4,3]
     5- [5]

     Setp-2 sort and keep count
     [0,1,2]- 3
     [3,4]- 2
     [5]- 5

     Step-3 [0,1,2] occures three times means this is a completlty connected component
     same goes for other two
     **/

    static class Solution {
        public int countCompleteComponents(int n, int[][] edges) {
            // Step 1: Initialize the graph with self-edges
            List<Integer>[] adj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                adj[i] = list;
            }

            // Step 2: Build the graph
            for (int[] edge : edges) {
                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);
            }

            // Step 3: Count the frequency of edge lists
            Map<List<Integer>, Integer> edgeListCount = new HashMap<>();
            for (List<Integer> edgeList : adj) {
                Collections.sort(edgeList);
                edgeListCount.put(edgeList, edgeListCount.getOrDefault(edgeList, 0) + 1);
            }

            // Step 4: Calculate the number of complete connected components
            int count = 0;
            for (Map.Entry<List<Integer>, Integer> entry : edgeListCount.entrySet()) {
                if (entry.getKey().size() == entry.getValue()) {
                    count++;
                }
            }

            return count;
        }
    }

}
