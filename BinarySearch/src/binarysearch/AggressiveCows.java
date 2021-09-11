package binarysearch;

import java.util.Arrays;

// Ref: Tahe u forward : https://www.youtube.com/watch?v=wSOfYesTBRk
// https://www.youtube.com/watch?v=SiE1XFhYoaA

// Q: https://www.spoj.com/problems/AGGRCOW/
// https://github.com/striver79/SDESheet/blob/main/aggressiveCows

public class AggressiveCows {

	public static int splitArray(int[] nums, int cows) {
		int res = -1;

		Arrays.sort(nums);
		int start = 1, end = nums[nums.length - 1] - nums[0];

		// binary search
		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (isValid(nums, nums.length, cows, mid) == true) {
				res = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return res;
	}

	private static boolean isValid(int[] nums, int l, int cows, int minDist) {
		int cntCows = 1;
		int lastPlacedCow = nums[0];
		for (int i = 1; i < l; i++) {
			if (nums[i] - lastPlacedCow >= minDist) {
				cntCows++;
				lastPlacedCow = nums[i];
			}
		}
		if (cntCows >= cows)
			return true;
		return false;
	}

	public static void main(String[] args) {
		int nums[] = { 1, 2, 4, 8, 9 };
		int cows = 3;
		int ans = splitArray(nums, cows);
		System.out.println(ans);
	}

}