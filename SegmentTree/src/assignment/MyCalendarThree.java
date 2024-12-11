package segmentTree;

import java.util.*;

public class MyCalendarThree {
    private Map<Integer, Integer> vals;
    private Map<Integer, Integer> lazy;

    public MyCalendarThree() {
        vals = new HashMap<>();
        lazy = new HashMap<>();
    }

    public void update(int start, int end, int left, int right, int idx) {
        // Push down lazy value at the current node
        if (lazy.containsKey(idx)) {
            // Update current node with lazy value
            vals.put(idx, vals.getOrDefault(idx, 0) + lazy.get(idx));

            if (left != right) { // Not a leaf node
                // Propagate lazy value to children
                lazy.put(2 * idx + 1, lazy.getOrDefault(2 * idx + 1, 0) + lazy.get(idx));
                lazy.put(2 * idx + 2, lazy.getOrDefault(2 * idx + 2, 0) + lazy.get(idx));
            }
            // Clear lazy value for current node
            lazy.remove(idx);
        }

        // No overlap
        if (start > right || end < left) {
            return;
        }

        // Total overlap
        if (start <= left && right <= end) {
            // Increment current node's value
            vals.put(idx, vals.getOrDefault(idx, 0) + 1);
            if (left != right) { // Not a leaf node
                lazy.put(2 * idx + 1, lazy.getOrDefault(2 * idx + 1, 0) + 1);
                lazy.put(2 * idx + 2, lazy.getOrDefault(2 * idx + 2, 0) + 1);
            }
            return;
        }

        // Partial overlap
        int mid = left + (right - left) / 2;
        update(start, end, left, mid, 2 * idx + 1);
        update(start, end, mid + 1, right, 2 * idx + 2);

        // Update current node with the max of child nodes
        vals.put(idx, Math.max(vals.getOrDefault(2 * idx + 1, 0), vals.getOrDefault(2 * idx + 2, 0)));
    }

    public int book(int start, int end) {
        update(start, end - 1, 0, 10, 0);
        return vals.getOrDefault(0, 0);
    }

    public static void main(String[] args) {
        MyCalendarThree obj = new MyCalendarThree();
        System.out.println(obj.book(1, 5));
        System.out.println(obj.book(4, 6));
        System.out.println(obj.book(7, 10));
    }
}


/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */

