package array.questions;

// https://leetcode.com/problems/trapping-rain-water/
// https://www.youtube.com/watch?v=C8UjlJZsHBw

// For optimization and intuition of that approach-- https://www.youtube.com/watch?v=m18Hntz4go8
// https://www.youtube.com/watch?v=XqTBrQYYUcc

/*Uses two extra spaces, that can be optimized
 Whenever we need to find lower envelope, optimization almost always include either two pointers or binary search
 here optimization is done suing two pointers
 */

// TC- o(n)
// Space o(n)
public class TrappingRainwater {

	// total water trapped= sum of water trapped over each building
	// how much water can be above a building depends on the
	// min(height of building on the left side, height on right side)

	// code- max_left=find max of to the left of each element
	// max_right=find max of to the right of each element
	// Water trapped obove each building= mmin(max_left[i],max_right[i])-height[i]
	// if height[i]<=0 --> height[i]=0
	// add all and return the ans
	public int trap(int[] height) {

		int n = height.length;
		int minHeight = 0;
		int currSum = 0;
		int totalSum = 0;
		int[] maxLeft = new int[n];
		int[] maxRight = new int[n];

		for (int i = 1; i < n; i++) {
			maxLeft[i] = Math.max(height[i - 1], maxLeft[i - 1]);
		}

		for (int i = n - 2; i >= 0; i--) {
			maxRight[i] = Math.max(height[i + 1], maxRight[i + 1]);
		}

		for (int i = 0; i < n; i++) {
			if (maxLeft[i] != 0 && maxRight[i] != 0) {
				minHeight = Math.min(maxLeft[i], maxRight[i]);
				if (minHeight <= height[i]) {
					currSum = 0;
				} else {
					currSum = minHeight - height[i];
				}
				totalSum += currSum;
			}

		}
		return totalSum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
