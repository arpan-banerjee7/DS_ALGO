package google;

import java.util.*;

public class MaxScoreJump {
    public int getMaxScore(List<Integer> stones) {
        int n = stones.size();
        int totalScore = 0;
        int[] suffixMaxs = new int[n];
        int maxStoneIdx = n - 1;

        // Finding the maximum value for every element on its right
        suffixMaxs[n - 1] = -1;
        for (int idx = n - 2; idx >= 0; idx--) {
            suffixMaxs[idx] = maxStoneIdx;
            if (stones.get(idx) > stones.get(maxStoneIdx)) {
                maxStoneIdx = idx;
            }
        }

        // Calculating the total score
        int idx = 0;
        while (idx < n - 1) { // We'll stop at the end of the array
            int nextIdx = suffixMaxs[idx];
            int jumpScore = stones.get(nextIdx) * (nextIdx - idx);
            totalScore += jumpScore;
            idx = nextIdx; // Jump
        }

        return totalScore;
    }

    // Driver code to test the function
    public static void main(String[] args) {
        MaxScoreJump solution = new MaxScoreJump();
        List<Integer> stones = new ArrayList<>();
        stones.add(3);
        stones.add(10);
        stones.add(7);
        stones.add(9);

        System.out.println("Maximum score: " + solution.getMaxScore(stones)); // Example output
    }
}
