package google.linesweep;

import java.util.HashMap;
import java.util.Map;


public class UnionOfRanges {
    private Map<Integer, Boolean> tree; // Store covered status (true or false)
    private Map<Integer, Boolean> lazy; // Store pending updates for lazy propagation
    private int size;

    public UnionOfRanges(int size) {
        this.size = size;
        this.tree = new HashMap<>();
        this.lazy = new HashMap<>();
    }

    // Lazy propagation to update the current node and propagate to its children
    private void pushDown(int idx, int left, int right) {
        if (lazy.containsKey(idx)) {
            boolean pendingUpdate = lazy.get(idx);

            // Update the current node
            tree.put(idx, pendingUpdate);

            if (left != right) { // Propagate to children
                lazy.put(2 * idx + 1, pendingUpdate);
                lazy.put(2 * idx + 2, pendingUpdate);
            }

            // Clear the lazy value for the current node
            lazy.remove(idx);
        }
    }

    // Update a range [start, end] with the specified value (true for covered)
    public void update(int start, int end, boolean value, int idx, int left, int right) {
        pushDown(idx, left, right); // Ensure any pending updates are applied

        if (left > end || right < start) {
            return; // No overlap
        }

        if (left >= start && right <= end) { // Total overlap
            tree.put(idx, value);
            if (left != right) {
                lazy.put(2 * idx + 1, value);
                lazy.put(2 * idx + 2, value);
            }
            return;
        }

        // Partial overlap: Recursively update children
        int mid = left + (right - left) / 2;
        update(start, end, value, 2 * idx + 1, left, mid); // Left child
        update(start, end, value, 2 * idx + 2, mid + 1, right); // Right child

        // Merge children's values for the current node
        boolean leftVal = tree.getOrDefault(2 * idx + 1, false);
        boolean rightVal = tree.getOrDefault(2 * idx + 2, false);
        tree.put(idx, leftVal && rightVal);
    }

    // Query if a point is covered
    public boolean query(int pos, int idx, int left, int right) {
        pushDown(idx, left, right); // Ensure any pending updates are applied

        if (tree.getOrDefault(idx, false)) return true; // Segment is fully covered

        if (left == right) { // Leaf node
            return tree.getOrDefault(idx, false);
        }

        int mid = left + (right - left) / 2;
        if (pos <= mid) {
            return query(pos, 2 * idx + 1, left, mid); // Query left child
        } else {
            return query(pos, 2 * idx + 2, mid + 1, right); // Query right child
        }
    }

    // Public methods to simplify usage
    public void insertRange(int start, int end) {
        update(start, end, true, 0, 1, size);
    }

    public boolean queryPoint(int pos) {
        return query(pos, 0, 1, size);
    }

    public static void main(String[] args) {
        int size = 1_000_000;
        UnionOfRanges rangeUnion = new UnionOfRanges(size);

        // Insert ranges
        rangeUnion.insertRange(2, 5);
        rangeUnion.insertRange(9, 13);

        // Query points
        System.out.println("Query 0: " + rangeUnion.queryPoint(0));   // Expected: false
        System.out.println("Query 2: " + rangeUnion.queryPoint(2));   // Expected: true
        System.out.println("Query 6: " + rangeUnion.queryPoint(6));   // Expected: false
        System.out.println("Query 10: " + rangeUnion.queryPoint(10)); // Expected: true

        // Insert overlapping range
        rangeUnion.insertRange(4, 10);

        // Query points after inserting overlapping range
        System.out.println("Query 3: " + rangeUnion.queryPoint(3));   // Expected: true
        System.out.println("Query 6: " + rangeUnion.queryPoint(6));   // Expected: true
        System.out.println("Query 8: " + rangeUnion.queryPoint(8));   // Expected: true
        System.out.println("Query 14: " + rangeUnion.queryPoint(14)); // Expected: false
    }
}
//interval tree inputs
/*
input
2-5 -> user1
6-9 -> user2
4-7 -> user3
query will be a point
3 -> expected ans is user1 as he falls between 2-5
8 -> expected ans is user2 as he falls between 6-9
5 -> expected ans is user1&user3 as user1 range is 2-5 and user3 range is 4-7 (edited)

 */

/*
Implement a class that acts as a union of ranges. The class should allow to

Insert ranges into the class.
Query the class to check if a particular point is in any of the inserted ranges.
Example:

Ranges Inserted:

2-5
9-13
Points Queried

0 -> False
2 -> True
10 -> True

min -> 1

max -> 1 million
 */