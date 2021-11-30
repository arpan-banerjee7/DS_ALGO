package assignments;

import java.util.Arrays;

// Min Platforms- same as Meeting rooms 2
// https://www.youtube.com/watch?v=dxVcMDI7vyI&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=2
// https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#

public class MinPlatforms {
	// Function to find the minimum number of platforms required at the
	// railway station such that no train waits.
	static int findPlatform(int arr[], int dep[], int n) {
		// add your code here
		Arrays.sort(arr);
		Arrays.sort(dep);

		int platforms = 0;
		int maxPlatforms = Integer.MIN_VALUE;

		int start = 0;
		int end = 0;
		while (start < n && end < n) {
			if (arr[start] <= dep[end]) {
				platforms++;
				start++;
			} else {
				platforms--;
				end++;
			}
			maxPlatforms = Math.max(platforms, maxPlatforms);
		}
		return maxPlatforms;
	}

	public static void main(String[] args) {
		int n = 6;
		//int[] arr = { 0900, 0940, 0950, 1100, 1500, 1800 };
		//int[] dep = { 0910, 1200, 1120, 1130, 1900, 2000 };
		//System.out.println(findPlatform(arr, dep, n));
		/*
		 * Output: 3 Explanation: Minimum 3 platforms are required to safely arrive and
		 * depart all trains.
		 */

	}

}
