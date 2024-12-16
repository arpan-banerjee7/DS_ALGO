package google.linesweep;

import java.util.*;

// TC - Insertions -O(nlogn)
// Point queries- O(nlogn+k)
public class FindUsersInOverlappingRangesLineSweep {
    TreeMap<Integer, List<String>> events = new TreeMap<>();

    // Add an interval for a user
    public void addInterval(int start, int end, String user) {
        events.putIfAbsent(start, new ArrayList<>());
        events.get(start).add(user);

        events.putIfAbsent(end + 1, new ArrayList<>());
        events.get(end + 1).add("-" + user); // Use '-' to indicate end of an interval
    }

    // Query point to find active users
    public List<String> query(int point) {
        List<String> result = new ArrayList<>();
        Set<String> activeUsers = new HashSet<>();

        for (Map.Entry<Integer, List<String>> entry : events.headMap(point + 1).entrySet()) {
            for (String user : entry.getValue()) {
                if (user.startsWith("-")) {
                    activeUsers.remove(user.substring(1));
                } else {
                    activeUsers.add(user);
                }
            }
        }

        result.addAll(activeUsers);
        return result;
    }


    public static void main(String[] args) {
        FindUsersInOverlappingRangesLineSweep sweep = new FindUsersInOverlappingRangesLineSweep();

        // Add user ranges
        sweep.addInterval(2, 5, "user1");
        sweep.addInterval(6, 9, "user2");
        sweep.addInterval(4, 7, "user3");

        // Query points
        System.out.println(sweep.query(3)); // Output: [user1]
        System.out.println(sweep.query(8)); // Output: [user2]
        System.out.println(sweep.query(5)); // Output: [user1, user3]
    }
}

/*
Input
2-5 -> user1
6-9 -> user2
4-7 -> user3
query will be a point
Output
3 -> expected ans is user1 as he falls between 2-5
8 -> expected ans is user2 as he falls between 6-9
5 -> expected ans is user1&user3 as user1 range is 2-5 and user3 range is 4-7 (edited)
 */

