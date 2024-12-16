package google;

import java.util.*;

public class GrasshopperProbability {

    static class Graph {
        private Map<Integer, List<Integer>> adjList = new HashMap<>();
        private Map<Integer, Double> probabilityMap = new HashMap<>();

        // Add an edge from parent to child
        public void addEdge(int parent, int child) {
            adjList.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
        }

        // DFS to calculate probability
        private void dfs(int node, double probability) {
            // If the node has children, distribute the probability equally
            if (adjList.containsKey(node)) {
                List<Integer> children = adjList.get(node);
                double childProbability = probability / children.size();
                for (int child : children) {
                    probabilityMap.put(child, probabilityMap.getOrDefault(child, 0.0) + childProbability);
                    dfs(child, childProbability);
                }
            }
        }

        // Calculate the probability starting from the root node
        public void calculateProbabilities(int root) {
            probabilityMap.put(root, 1.0); // Root starts with 100% probability
            dfs(root, 1.0);
        }

        // Print the probability for each node
        public void printProbabilities() {
            for (int node : probabilityMap.keySet()) {
                System.out.println("Node " + node + ": Probability = " + probabilityMap.get(node));
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Build the graph as described
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 7);
        graph.addEdge(3, 5);
        graph.addEdge(3, 8);
        graph.addEdge(5, 7);

        // Calculate probabilities starting from the root (Node 1)
        graph.calculateProbabilities(1);

        // Print the results
        graph.printProbabilities();
    }
}
