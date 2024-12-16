package google;

import java.util.*;

public class MinTeleportorsEncounteredAvoidingBroken {
    public static int findMinimumTeleportations(
            int N,
            List<List<Integer>> connections,
            Set<Integer> brokenTeleporters,
            int startTeleporter,
            int destinationTeleporter
    ) {
        // Edge case: if the start or destination teleporters are broken
        if (brokenTeleporters.contains(startTeleporter) || brokenTeleporters.contains(destinationTeleporter)) {
            return -1;
        }

        // Initialize visited array
        boolean[] visited = new boolean[N];

        // Initialize BFS queue
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from startTeleporter
        queue.offer(startTeleporter);
        visited[startTeleporter] = true;

        int level = 0; // Represents the number of teleportations

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // If we have reached the destination, return the level (distance)
                if (current == destinationTeleporter) {
                    return level;
                }

                // Iterate over neighbors
                for (int neighbor : connections.get(current)) {
                    // If neighbor is not broken and not visited
                    if (!brokenTeleporters.contains(neighbor) && !visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }

            // Increment level after processing the current level
            level++;
        }

        // Destination not reachable
        return -1;
    }

    public static void main(String[] args) {
        // Example usage:

        // Number of teleporters
        int N = 6;

        // Connections (graph representation)
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            connections.add(new ArrayList<>());
        }

        // Define connections between teleporters
        // For example, teleporter 0 is connected to teleporters 1 and 2
        connections.get(0).add(1);
        connections.get(0).add(2);

        connections.get(1).add(0);
        connections.get(1).add(3);

        connections.get(2).add(0);
        connections.get(2).add(3);

        connections.get(3).add(1);
        connections.get(3).add(2);
        connections.get(3).add(4);

        connections.get(4).add(3);
        connections.get(4).add(5);

        connections.get(5).add(4);

        // Set of broken teleporters
        Set<Integer> brokenTeleporters = new HashSet<>();
        brokenTeleporters.add(2);
        brokenTeleporters.add(4);

        // Starting and destination teleporters
        int startTeleporter = 0;
        int destinationTeleporter = 5;

        int minTeleportations = findMinimumTeleportations(
                N,
                connections,
                brokenTeleporters,
                startTeleporter,
                destinationTeleporter
        );

        if (minTeleportations != -1) {
            System.out.println("Minimum teleportations needed: " + minTeleportations);
        } else {
            System.out.println("Destination not reachable avoiding broken teleporters.");
        }
    }
}


