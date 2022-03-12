package array.questions;

import java.util.Arrays;

// 1051. Height Checker
// Order Check
// https://leetcode.com/problems/height-checker/
// countng sort-https://www.youtube.com/watch?v=pEJiGC-ObQE

public class HeighChecker {

	// O(nlog(n)
	 public int heightChecker1(int[] heights) {
	        int[] sorted = heights.clone();
	        
	        Arrays.sort(sorted);
	        
	        int count = 0;
	        for (int i = 0; i < heights.length; ++i) {
	            if (heights[i] != sorted[i]) count++;
	        }
	        return count;
	    }
	 
	public int heightChecker(int[] heights) {
		int[] heightToFreq = new int[101];

		for (int height : heights) {
			heightToFreq[height]++;
		}

		int result = 0;
		int curHeight = 0;

		// for understanding take teh sorted array and dry run this logic
		// basically we are comparing the freq array with the given array
		// given_array=[1,1,4,2,1,3]
		// freq_array= [0,3,1,1,1,0,0,0....]
		// it means 1 appears 3 times, so we run a loop and check if the index of the
		// freq array=given_array[i] and it should happen 3 times, that means 3 ones are
		// back to back so in their correct place, after each iteration we reduce the
		// count in the freq array.
		for (int i = 0; i < heights.length; i++) {
			while (heightToFreq[curHeight] == 0) {
				curHeight++;
			}

			if (curHeight != heights[i]) {
				result++;
			}
			heightToFreq[curHeight]--;
		}

		return result;
	}

	public static void main(String[] args) {
		/*
		 * Input: heights = [1,1,4,2,1,3] Output: 3 Explanation: heights: [1,1,4,2,1,3]
		 * expected: [1,1,1,2,3,4] Indices 2, 4, and 5 do not match.
		 */
	}

}
