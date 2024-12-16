package google;

import java.util.*;

public class TrainOccupancySegmentTree {

    static class STree {
        private int[] tree, lazy;
        private int size;

        public STree(int size) {
            this.size = size;
            this.tree = new int[4 * size];
            this.lazy = new int[4 * size];
        }

        // Range sum segment tree with lazy propagation
        public void update(int start, int end, int value, int idx, int left, int right) {
            // Propagate any pending updates
            if (lazy[idx] != 0) {
                tree[idx] += (right - left + 1) * lazy[idx];
                if (left != right) { // Not a leaf node
                    lazy[2 * idx + 1] += lazy[idx];
                    lazy[2 * idx + 2] += lazy[idx];
                }
                lazy[idx] = 0;
            }

            // No overlap
            if (left > end || right < start) {
                return;
            }

            // Total overlap
            if (left >= start && right <= end) {
                tree[idx] += (right - left + 1) * value;
                if (left != right) { // Not a leaf node
                    lazy[2 * idx + 1] += value;
                    lazy[2 * idx + 2] += value;
                }
                return;
            }

            // Partial overlap
            int mid = left + (right - left) / 2;
            update(start, end, value, 2 * idx + 1, left, mid);
            update(start, end, value, 2 * idx + 2, mid + 1, right);
            tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
        }

        // Query for a specific point in the segment tree
        public int query(int pos, int idx, int left, int right) {
            // Propagate any pending updates
            if (lazy[idx] != 0) {
                tree[idx] += (right - left + 1) * lazy[idx];
                if (left != right) { // Not a leaf node
                    lazy[2 * idx + 1] += lazy[idx];
                    lazy[2 * idx + 2] += lazy[idx];
                }
                lazy[idx] = 0;
            }

            // No overlap
            if (left > pos || right < pos) {
                return 0;
            }

            // Total overlap
            if (left == right) {
                return tree[idx];
            }

            // Partial overlap
            int mid = left + (right - left) / 2;
            int leftQuery = query(pos, 2 * idx + 1, left, mid);
            int rightQuery = query(pos, 2 * idx + 2, mid + 1, right);
            return leftQuery + rightQuery;
        }
    }

    public static void main(String[] args) {
        // Number of segments
        int N = 5;

        // List of tickets (departure, arrival)
        List<int[]> tickets = new ArrayList<>();
        tickets.add(new int[]{1, 4}); // Passenger from stop 1 to stop 4
        tickets.add(new int[]{2, 5}); // Passenger from stop 2 to stop 5

        // Initialize the segment tree
        STree segmentTree = new STree(N);

        // Update the segment tree based on the tickets
        for (int[] ticket : tickets) {
            int departure = ticket[0];
            int arrival = ticket[1];
            // Update occupancy for the range [departure, arrival - 1]
            segmentTree.update(departure, arrival - 1, 1, 0, 0, N);
        }

        // Query the occupancy for each segment
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = segmentTree.query(i, 0, 0, N );
        }

        // Output the result
        System.out.println("Train occupancy for each segment:");
        System.out.println(Arrays.toString(result)); // Expected: [0, 1, 2, 2, 1]
    }
}
