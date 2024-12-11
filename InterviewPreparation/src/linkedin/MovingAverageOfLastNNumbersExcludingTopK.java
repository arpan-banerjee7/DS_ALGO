package linkedin;

import java.util.*;

public class MovingAverageOfLastNNumbersExcludingTopK {
    private int N;
    private int K;
    private Deque<Integer> window;
    private TreeMap<Integer, Integer> small; // TreeMap for the smallest (N - K) elements
    private TreeMap<Integer, Integer> large; // TreeMap for the largest K elements
    private int smallSum; // Sum of elements in the small TreeMap

    public MovingAverageOfLastNNumbersExcludingTopK(int N, int K) {
        this.N = N;
        this.K = K;
        this.window = new ArrayDeque<>();
        this.small = new TreeMap<>();
        this.large = new TreeMap<>();
        this.smallSum = 0;
    }

    public double next(int val) {
        // Add new value
        window.add(val);
        addToTreeMap(small, val);
        smallSum += val;

        // If we exceed N elements, remove the oldest
        if (window.size() > N) {
            int oldest = window.poll();
            if (removeFromTreeMap(small, oldest)) {
                smallSum -= oldest;
            } else {
                removeFromTreeMap(large, oldest);
            }
        }

        // Rebalance the maps
        rebalance();

        // If we haven't reached N elements yet, return 0.0
        if (window.size() < N) {
            return 0.0;
        }

        // Now we know window.size() == N
        // If small doesn't have the required (N - K) elements for some reason, also return 0.0
        if (small.size() < (N - K)) {
            return 0.0;
        }

        return smallSum * 1.0 / small.size();
    }

    private void addToTreeMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private boolean removeFromTreeMap(TreeMap<Integer, Integer> map, int val) {
        if (!map.containsKey(val)) return false;
        if (map.get(val) == 1) {
            map.remove(val);
        } else {
            map.put(val, map.get(val) - 1);
        }
        return true;
    }

    private void rebalance() {
        // Ensure small contains exactly (N - K) elements
        while (small.size() > (N - K)) {
            int largestInSmall = small.lastKey();
            addToTreeMap(large, largestInSmall);
            smallSum -= largestInSmall;
            removeFromTreeMap(small, largestInSmall);
        }

    }

    public static void main(String[] args) {
        MovingAverageOfLastNNumbersExcludingTopK movingAverage = new MovingAverageOfLastNNumbersExcludingTopK(5, 2);
        System.out.println(movingAverage.next(20)); // Output: 0.0 (not enough elements)
        System.out.println(movingAverage.next(2));  // Output: 0.0 (not enough elements)
        System.out.println(movingAverage.next(-2)); // Output: 0.0 (not enough elements)
        System.out.println(movingAverage.next(0));  // Output: 0.0 (not enough elements)
        System.out.println(movingAverage.next(10)); // Mean: (-2 + 2) / 2 = 0.0
        System.out.println(movingAverage.next(1));  // Mean: (2 + -2 + 0) / 3 = 0.33333
        System.out.println(movingAverage.next(5));  // Mean: (-2 + 0 + 1) / 3 = -0.33333
        System.out.println(movingAverage.next(-2)); // Mean: (0 + 1 + -2) / 3 = -0.33333
        System.out.println(movingAverage.next(0));  // Mean: (1 + -2 + 0) / 3 = -0.33333
    }
}

