package google.sixDegreeSeparation;

import java.util.*;

public class FriendFinder {

    private final SocialNetworkService sns;

    public FriendFinder(SocialNetworkService socialNetworkService) {
        sns = socialNetworkService;
    }

    /**
     * Returns an ordered list of connectivity if the given personAId has a connection of friend relationships to personZId
     * within the specified degreesOfSeparation, otherwise return null.
     */
    public List<String> findShortestPath(String personAId, String personZId, int degreesOfSeparation) {
        if (personAId == null || personZId == null || degreesOfSeparation < 1) {
            return null;
        }
        if (personAId.equals(personZId)) {
            return Collections.singletonList(personAId);
        }

        // Initialize data structures for bidirectional BFS
        Set<String> visitedFromA = new HashSet<>();
        Set<String> visitedFromZ = new HashSet<>();
        Map<String, String> parentFromA = new HashMap<>();
        Map<String, String> parentFromZ = new HashMap<>();

        Queue<String> queueFromA = new LinkedList<>();
        Queue<String> queueFromZ = new LinkedList<>();

        // Initialize levels to keep track of degrees of separation
        Map<String, Integer> levelFromA = new HashMap<>();
        Map<String, Integer> levelFromZ = new HashMap<>();

        // Start BFS from both ends
        queueFromA.offer(personAId);
        visitedFromA.add(personAId);
        levelFromA.put(personAId, 0);

        queueFromZ.offer(personZId);
        visitedFromZ.add(personZId);
        levelFromZ.put(personZId, 0);

        // Flag to indicate if intersection is found
        String intersection;

        while (!queueFromA.isEmpty() && !queueFromZ.isEmpty()) {
            // Expand from the side with fewer nodes
            if (queueFromA.size() <= queueFromZ.size()) {
                intersection = bfsStep(queueFromA, visitedFromA, visitedFromZ, parentFromA, levelFromA, levelFromZ, degreesOfSeparation);
            } else {
                intersection = bfsStep(queueFromZ, visitedFromZ, visitedFromA, parentFromZ, levelFromZ, levelFromA, degreesOfSeparation);
            }

            // If intersection is found, reconstruct the path
            if (intersection != null) {
                return buildPath(intersection, parentFromA, parentFromZ);
            }
        }

        // No path found within the specified degrees of separation
        return null;
    }

    // Helper method to perform one step of BFS
    private String bfsStep(Queue<String> queue, Set<String> visitedThisSide, Set<String> visitedOtherSide,
                           Map<String, String> parentThisSide, Map<String, Integer> levelThisSide,
                           Map<String, Integer> levelOtherSide,
                           int degreesOfSeparation) {

        int size = queue.size();
        while (size!= 0) {
            String current = queue.poll();
            int currentLevel = levelThisSide.get(current);

            // Stop expanding if degrees of separation limit is reached
            if (currentLevel >= degreesOfSeparation) {
                continue;
            }

            Collection<String> friends = sns.findFriends(current);
            if (friends == null) {
                continue;
            }

            for (String neighbor : friends) {
                if (!visitedThisSide.contains(neighbor)) {
                    visitedThisSide.add(neighbor);
                    parentThisSide.put(neighbor, current);
                    levelThisSide.put(neighbor, currentLevel + 1);

                    // Check if the neighbor has been visited from the other side
                    if (visitedOtherSide.contains(neighbor)) {
                        // Check combined degrees of separation
                        if (levelThisSide.get(neighbor) + levelOtherSide.get(neighbor) <= degreesOfSeparation) {
                            return neighbor; // Intersection found
                        }
                    }

                    queue.offer(neighbor);
                }
            }
        }
        return null;
    }

    // Helper method to build the path from personAId to personZId
    private List<String> buildPath(String intersection, Map<String, String> parentFromA, Map<String, String> parentFromZ) {
        LinkedList<String> path = new LinkedList<>();

        // Build path from personAId to intersection
        String current = intersection;
        while (current != null) {
            path.addFirst(current);
            current = parentFromA.get(current);
        }

        // Build path from intersection to personZId
        current = parentFromZ.get(intersection);
        while (current != null) {
            path.addLast(current);
            current = parentFromZ.get(current);
        }

        return path;
    }

    public static void main(String[] args) {
        // Create an instance of SNSImpl
        SNSImpl sns = new SNSImpl();

        // Add friends as per the test case
        sns.addFriend("Kevin", "UserB");
        sns.addFriend("Kevin", "UserS");
        sns.addFriend("UserB", "UserC");
        sns.addFriend("UserA", "UserD");
        sns.addFriend("UserX", "UserC");
        sns.addFriend("UserY", "UserX");
        sns.addFriend("Bacon", "UserY");

        // Create an instance of FriendFinder
        FriendFinder ff = new FriendFinder(sns);

        // First test case: Finding the shortest path from "Kevin" to "Bacon" with max 5 degrees
        List<String> path1 = ff.findShortestPath("Kevin", "Bacon", 5);
        System.out.println("Shortest path from Kevin to Bacon within 5 degrees: " + path1);

        // Modify the network by adding a shorter path
        sns.addFriend("UserS", "Bacon");

        // Second test case: Finding the shortest path from "Kevin" to "Bacon" with max 6 degrees
        List<String> path2 = ff.findShortestPath("Kevin", "Bacon", 6);
        System.out.println("Shortest path from Kevin to Bacon within 6 degrees: " + path2);
    }
}


