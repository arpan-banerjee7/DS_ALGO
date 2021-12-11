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

	public int trap1(int[] height) {
		int n = height.length;
		int leftMax = height[0];
		int rightMax = height[n - 1];
		int trappedWater = 0;

		int start = 0, end = n - 1;
		while (start <= end) {
			// since leftMax is an increasing functiona and rightMax is a decreasing fn
			if (leftMax > rightMax) {
				if (height[end] < rightMax) {
					trappedWater += (rightMax - height[end]);
				}
				rightMax = Math.max(rightMax, height[end]);
				end--;
			} else {
				if (height[start] < leftMax) {
					trappedWater += (leftMax - height[start]);
				}
				leftMax = Math.max(leftMax, height[start]);
				start++;
			}
		}
		return trappedWater;
	}

	// Tc 3*o(n) doing 3 passes for calculating lMax, rMax and then to get the ans
	class Solution {

		private int[] findMax(int[] arr, int n, boolean left) {
			// left max
			int max = Integer.MIN_VALUE;
			int[] maxArr = new int[n];
			if (left) {
				for (int i = 0; i < n; i++) {
					maxArr[i] = max;
					max = Math.max(arr[i], max);
				}

				// right max
			} else {
				for (int i = n - 1; i >= 0; i--) {
					maxArr[i] = max;
					max = Math.max(arr[i], max);
				}
			}
			return maxArr;
		}

		public int trap(int[] height) {
			int n = height.length;

			int[] leftMax = findMax(height, n, true);
			int[] rightMax = findMax(height, n, false);

			// find the ans
			int trappedWater = 0;
			int minHeight = 0;
			for (int i = 0; i < n; i++) {
				minHeight = Math.min(leftMax[i], rightMax[i]);
				if (height[i] < minHeight) {
					trappedWater += (minHeight - height[i]);
				}
			}
			return trappedWater;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
