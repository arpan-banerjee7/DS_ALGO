package google;

import java.util.*;

public class MinimumBrokenTeleporters {

    public static int findPathWithMinBrokenTeleporters(
            int N,
            List<List<Integer>> connections,
            Set<Integer> brokenTeleporters,
            int startTeleporter,
            int destinationTeleporter
    ) {
        // Priority queue to store {cost, teleporter}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize the broken count array with Integer.MAX_VALUE
        int[] brokenCount = new int[N];
        Arrays.fill(brokenCount, Integer.MAX_VALUE);

        // Start BFS from startTeleporter
        pq.offer(new int[]{0, startTeleporter}); // {cost, teleporter}
        brokenCount[startTeleporter] = brokenTeleporters.contains(startTeleporter) ? 1 : 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentCost = current[0];
            int currentTeleporter = current[1];

            // If we reach the destination
            if (currentTeleporter == destinationTeleporter) {
                // Add 1 if the destination teleporter is broken
                return currentCost + (brokenTeleporters.contains(destinationTeleporter) ? 1 : 0);
            }

            // Explore neighbors
            for (int neighbor : connections.get(currentTeleporter)) {
                int newCost = currentCost + (brokenTeleporters.contains(neighbor) ? 1 : 0);

                // Update if we find a cheaper path
                if (newCost < brokenCount[neighbor]) {
                    brokenCount[neighbor] = newCost;
                    pq.offer(new int[]{newCost, neighbor});
                }
            }
        }

        // If we finish BFS and haven't reached the destination
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
        brokenTeleporters.add(5); // Destination teleporter is broken

        // Starting and destination teleporters
        int startTeleporter = 0;
        int destinationTeleporter = 5;

        int minBrokenTeleporters = findPathWithMinBrokenTeleporters(
                N,
                connections,
                brokenTeleporters,
                startTeleporter,
                destinationTeleporter
        );

        if (minBrokenTeleporters != -1) {
            System.out.println("Minimum broken teleporters encountered: " + minBrokenTeleporters);
        } else {
            System.out.println("Destination not reachable.");
        }
    }
}
