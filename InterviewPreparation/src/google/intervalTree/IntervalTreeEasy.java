package google.intervalTree;

import java.util.*;
// https://www.geeksforgeeks.org/interval-tree/
// https://www.youtube.com/watch?v=iaOVnU2NhYg


// TC - Insertions -O(nlogn)
// Point queries- O(nlogn+k)
// check overlap- O(logn)
public class IntervalTreeEasy {

    static class Interval {
        int start, end;
        String user;

        Interval(int start, int end, String user) {
            this.start = start;
            this.end = end;
            this.user = user;
        }

        @Override
        public String toString() {
            return "[" + start + "-" + end + " (" + user + ")]";
        }
    }

    static class Node {
        Interval interval;
        int maxEnd;
        Node left, right;

        Node(Interval interval) {
            this.interval = interval;
            this.maxEnd = interval.end;
        }
    }

    private Node root;

    // Insert an interval
    public void insert(Interval interval) {
        root = insert(root, interval);
    }

    private Node insert(Node root, Interval interval) {
        if (root == null) {
            return new Node(interval);
        }

        if (interval.start < root.interval.start) {
            root.left = insert(root.left, interval);
        } else {
            root.right = insert(root.right, interval);
        }

        root.maxEnd = Math.max(root.maxEnd, interval.end);
        return root;
    }

    // Query all intervals containing the point
    public List<Interval> query(int point) {
        List<Interval> result = new ArrayList<>();
        query(root, point, result);
        return result;
    }

    private void query(Node root, int point, List<Interval> result) {
        if (root == null) {
            return;
        }

        // Check if the current interval contains the point
        if (point >= root.interval.start && point <= root.interval.end) {
            result.add(root.interval);
        }

        // Check left subtree if the point is less than the max end of the left subtree
        if (root.left != null && root.left.maxEnd >= point) {
            query(root.left, point, result);
        }

        if (root.right != null) {
            query(root.right, point, result);
        }
    }


    public static void main(String[] args) {
        IntervalTreeEasy tree = new IntervalTreeEasy();

        // Insert user-defined ranges
        tree.insert(new Interval(2, 5, "user1"));
        tree.insert(new Interval(6, 9, "user2"));
        tree.insert(new Interval(4, 7, "user3"));

        // Queries
        System.out.println("Query for point 3: " + tree.query(3)); // Expected: user1
        System.out.println("Query for point 8: " + tree.query(8)); // Expected: user2
        System.out.println("Query for point 5: " + tree.query(5)); // Expected: user1 & user3
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

