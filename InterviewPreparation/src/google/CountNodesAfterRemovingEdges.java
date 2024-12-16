package google;

import java.util.*;


public class CountNodesAfterRemovingEdges {
    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<Integer> findConnectedComponents(TreeNode root, int[][] edges) {
        // Step 1: Create adjacency list for the tree
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, graph);

        // Step 2: Remove edges from the graph
        //TC-O(e)
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).remove((Integer)to);
        }

        // Step 3: Find connected components using DFS
        //TC-O(n)
        Set<Integer> visited = new HashSet<>();
        List<Integer> componentSizes = new ArrayList<>();
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                int size = dfs(node, graph, visited);
                componentSizes.add(size);
            }
        }

        return componentSizes;
    }

    private static int dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        int size = 1; // Count the current node
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                size += dfs(neighbor, graph, visited);
            }
        }
        return size;
    }

    //TC-O(n)
    private static void buildGraph(TreeNode node, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }

        graph.putIfAbsent(node.val, new ArrayList<>());
        if (node.left != null) {
            graph.get(node.val).add(node.left.val);
            buildGraph(node.left, graph);
        }
        if (node.right != null) {
            graph.get(node.val).add(node.right.val);
            buildGraph(node.right, graph);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int[][] edges = {{1, 2}, {2, 4}};
        List<Integer> result = findConnectedComponents(root, edges);
        System.out.println(result); // Output: [2, 2, 1]
    }

}
