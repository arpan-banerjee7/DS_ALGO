package google;

import java.util.*;

public class MostActiveUser {
    static class Activity {
        long timestamp;
        String userId;

        public Activity(long timestamp, String userId) {
            this.timestamp = timestamp;
            this.userId = userId;
        }
    }

    // Map to store cumulative counts for each user at each timestamp
    private TreeMap<Long, Map<String, Integer>> timeToUserCounts;

    // Constructor
    public MostActiveUser(List<Activity> activities) {
        // Initialize the data structure
        timeToUserCounts = new TreeMap<>();
        preprocessActivities(activities);
    }

    private void preprocessActivities(List<Activity> activities) {
        // Sort activities by timestamp
        activities.sort(Comparator.comparingLong(a -> a.timestamp));

        // Map to keep track of cumulative counts for each user
        Map<String, Integer> cumulativeCounts = new HashMap<>();

        for (Activity activity : activities) {
            // Update cumulative count for the user
            cumulativeCounts.put(activity.userId, cumulativeCounts.getOrDefault(activity.userId, 0) + 1);

            // Create a copy of cumulativeCounts for the current timestamp
            Map<String, Integer> countsSnapshot = new HashMap<>(cumulativeCounts);

            // Store the snapshot in the TreeMap
            timeToUserCounts.put(activity.timestamp, countsSnapshot);
        }
    }

    // Method to get the most active user up to time t
    public String getMostActiveUserUpToTime(long t) {
        // Get the cumulative counts up to time t
        Map.Entry<Long, Map<String, Integer>> entry = timeToUserCounts.floorEntry(t);
        if (entry == null) {
            return null; // No activities up to time t
        }

        Map<String, Integer> userCounts = entry.getValue();

        // Find the user with the maximum count
        String mostActiveUser = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> userEntry : userCounts.entrySet()) {
            int count = userEntry.getValue();
            if (count > maxCount) {
                maxCount = count;
                mostActiveUser = userEntry.getKey();
            }
        }

        return mostActiveUser;
    }

    // Method to get the top k most active users up to time t
    public List<String> getTopKActiveUsersUpToTime(long t, int k) {
        // Get the cumulative counts up to time t
        Map.Entry<Long, Map<String, Integer>> entry = timeToUserCounts.floorEntry(t);
        if (entry == null) {
            return Collections.emptyList(); // No activities up to time t
        }

        Map<String, Integer> userCounts = entry.getValue();

        // Use a max heap to get the top k users
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Populate the min-heap with the first k elements
        for (Map.Entry<String, Integer> userCount : userCounts.entrySet()) {
            minHeap.offer(userCount);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> topKUsers = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            topKUsers.add(minHeap.poll().getKey());
        }

        return topKUsers;
    }

    // Test the implementation
    public static void main(String[] args) {
        List<Activity> activities = Arrays.asList(
                new Activity(1000, "user1"),
                new Activity(1001, "user2"),
                new Activity(1002, "user1"),
                new Activity(1003, "user3"),
                new Activity(1004, "user2"),
                new Activity(1005, "user2"),
                new Activity(1006, "user1")
        );

        MostActiveUser mau = new MostActiveUser(activities);

        // Query at time 1005
        String mostActiveUser = mau.getMostActiveUserUpToTime(1005);
        System.out.println("Most active user up to time 1005: " + mostActiveUser);

        // Top 2 active users up to time 1005
        List<String> topKUsers = mau.getTopKActiveUsersUpToTime(1005, 2);
        System.out.println("Top 2 active users up to time 1005: " + topKUsers);

        // Query at time 1003
        mostActiveUser = mau.getMostActiveUserUpToTime(1003);
        System.out.println("Most active user up to time 1003: " + mostActiveUser);

        // Top 2 active users up to time 1003
        topKUsers = mau.getTopKActiveUsersUpToTime(1003, 2);
        System.out.println("Top 2 active users up to time 1003: " + topKUsers);
    }
}
