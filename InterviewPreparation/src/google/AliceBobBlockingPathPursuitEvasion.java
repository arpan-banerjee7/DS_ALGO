package google;

import java.util.*;

public class AliceBobBlockingPathPursuitEvasion {

    // BFS to store the parent and distances of nodes from a source
    static void bfs(List<List<Integer>> graph, int source, List<Integer> parent, List<Integer> distance) {
        Queue<Integer> queue = new LinkedList<>();
        distance.set(source, 0);
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighbor : graph.get(currentNode)) {
                if (distance.get(neighbor) == Integer.MAX_VALUE) {
                    parent.set(neighbor, currentNode);
                    distance.set(neighbor, distance.get(currentNode) + 1);
                    queue.add(neighbor);
                }
            }
        }
    }

    // Optimized BFS for Bob to check interception
    static String canAliceReachSafely(List<List<Integer>> graph, int V, int aliceStart, int aliceTarget, int bobStart) {
        // Step 1: Find Alice's shortest path and distances
        List<Integer> parentAlice = new ArrayList<>(Collections.nCopies(V, -1));
        List<Integer> distanceAlice = new ArrayList<>(Collections.nCopies(V, Integer.MAX_VALUE));

        bfs(graph, aliceStart, parentAlice, distanceAlice);

        if (distanceAlice.get(aliceTarget) == Integer.MAX_VALUE) {
            return "No"; // Alice can't even reach the target
        }

        // Extract the nodes on Alice's shortest path
        Set<Integer> aliceShortestPathNodes = new HashSet<>();
        int currentNode = aliceTarget;
        while (currentNode != -1) {
            aliceShortestPathNodes.add(currentNode);
            currentNode = parentAlice.get(currentNode);
        }

        // Step 2: Perform BFS for Bob and check interception
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> distanceBob = new ArrayList<>(Collections.nCopies(V, Integer.MAX_VALUE));
        queue.offer(bobStart);
        distanceBob.set(bobStart, 0);

        while (!queue.isEmpty()) {
            int currentNodeBob = queue.poll();

            // Check if the current node is part of Alice's shortest path
            if (aliceShortestPathNodes.contains(currentNodeBob)) {
                if (distanceBob.get(currentNodeBob) <= distanceAlice.get(currentNodeBob)) {
                    return "No"; // Bob can block Alice
                }
            }

            for (int neighbor : graph.get(currentNodeBob)) {
                if (distanceBob.get(neighbor) == Integer.MAX_VALUE) {
                    distanceBob.set(neighbor, distanceBob.get(currentNodeBob) + 1);
                    queue.offer(neighbor);
                }
            }
        }

        return "Yes"; // Alice can reach safely
    }

    public static void main(String[] args) {
        int V = 6; // Number of nodes
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize adjacency list for graph
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges
        int[][] edges = {
                {0, 1}, {1, 2}, {1, 3}, {3, 4}, {2, 4}, {4, 5}, {0, 5}
        };
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int aliceStart = 2; // Alice's start
        int aliceTarget = 5; // Alice's target
        int bobStart = 4; // Bob's start

        // Determine if Alice can reach safely
        String result = canAliceReachSafely(graph, V, aliceStart, aliceTarget, bobStart);
        System.out.println(result);
    }
}


