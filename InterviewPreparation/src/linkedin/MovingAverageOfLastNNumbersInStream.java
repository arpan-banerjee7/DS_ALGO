package linkedin;
// 346. Moving Average from Data Stream
import java.util.*;

// TC- O(1), space - O(N)
public class MovingAverageOfLastNNumbersInStream {
    private int windowSum = 0;
    private int size;
    private Deque<Integer> queue;

    public MovingAverageOfLastNNumbersInStream(int size) {
        this.size = size;
        this.queue = new ArrayDeque<>();
    }

    public double next(int val) {
        // If the queue is full, remove the oldest element
        if (queue.size() == size) {
            windowSum -= queue.poll();
        }

        // Add the new value to the queue and update the sum
        queue.add(val);
        windowSum += val;

        // Return the average of the current window
        return windowSum * 1.0 / queue.size();
    }
}
/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.


Example 1:

Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) /
 */