package dp.boundedknapsack;

import java.util.Arrays;
import java.util.HashMap;

// 740. Delete and Earn
// https://leetcode.com/problems/delete-and-earn/

public class DeleteAndEarn {
	public int deleteAndEarn(int[] nums) {
		int max = Arrays.stream(nums).max().getAsInt();
		int[] freq = new int[max + 1];
		for (int e : nums) {
			freq[e]++;
		}
		HashMap<Integer, Integer> memo = new HashMap<>();
		return maxPoint(freq, 0, memo);
	}

	private int maxPoint(int[] freq, int currentIndex, HashMap<Integer, Integer> memo) {
		if (currentIndex >= freq.length)
			return 0;
		int currentKey = currentIndex;
		if (memo.containsKey(currentKey))
			return memo.get(currentKey);
		int del = currentIndex * freq[currentIndex] + maxPoint(freq, currentIndex + 2, memo);
		int noDel = maxPoint(freq, currentIndex + 1, memo);
		memo.put(currentIndex, Math.max(del, noDel));
		return memo.get(currentIndex);
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [3,4,2] Output: 6 Explanation: You can perform the following
		 * operations: - Delete 4 to earn 4 points. Consequently, 3 is also deleted.
		 * nums = [2]. - Delete 2 to earn 2 points. nums = []. You earn a total of 6
		 * points.
		 */
	}

}
