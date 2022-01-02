package meet.in.the.middle;

import java.util.Arrays;

// 1049. Last Stone Weight II
// other approaches - https://leetcode.com/problems/last-stone-weight-ii/discuss/1631347/Java-or-3-sloutions-or-Top-Down-recursive-or-Bottom-up-DP-or-Meet-in-the-middle-or-commented
// https://leetcode.com/problems/last-stone-weight-ii/
// gfg- https://www.geeksforgeeks.org/meet-in-the-middle/
// Meet in the middle vs dp vs recursion
// https://www.baeldung.com/cs/subset-of-numbers-closest-to-target

public class LastStoneWeight2 {

	// TC - 2^(n/2)*n/2
	// generate subset sums for a half of the given array
	private void createSet(int[] set, int[] nums, int n1) {
		int sum = 0, setIndex = 0;
		for (int i = 0; i < n1; i++) {
			sum = 0;
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) > 0) {
					sum += nums[j];
				}
			}
			set[setIndex++] = sum;
		}
	}

	public int lastStoneWeightII(int[] stones) {
		int n = stones.length;
		int sum = 0;
		for (int i : stones) {
			sum += i;
		}

		int[] set1 = new int[1 << n / 2];
		int[] set2 = new int[1 << (n - n / 2)];

		// to include from excluded
		createSet(set1, Arrays.copyOfRange(stones, 0, n / 2), 1 << n / 2);
		createSet(set2, Arrays.copyOfRange(stones, n / 2, n), 1 << (n - n / 2));

		// TC n/2*2^n/2
		Arrays.sort(set2);
		int target = sum / 2;

		// lower bound using binary search
		int max = Integer.MIN_VALUE;

		for (int set1Num : set1) {
			int low = 0, high = set2.length - 1;
			while (low <= high) {
				int mid = low + (high - low) / 2;
				if (set1Num + set2[mid] <= target) {
					low = mid + 1;
					max = Math.max(max, set1Num + set2[mid]);
				} else {
					high = mid - 1;
				}
			}
		}
		return sum - 2 * (max);
	}

	public int lastStoneWeightII1(int[] stones) {
		int n = stones.length;
		int sum = 0;
		for (int i : stones) {
			sum += i;
		}

		int[] set1 = new int[1 << n / 2];
		int[] set2 = new int[1 << (n - n / 2)];

		// to include from excluded
		createSet(set1, Arrays.copyOfRange(stones, 0, n / 2), 1 << n / 2);
		createSet(set2, Arrays.copyOfRange(stones, n / 2, n), 1 << (n - n / 2));

		// TC n/2*2^n/2
		Arrays.sort(set2);
		int target = sum / 2;

		// lower bound using binary search
		int max = Integer.MIN_VALUE;
		for (int i : set1) {
			// lower bound using binary search
			int posi = Arrays.binarySearch(set2, target - i);
			if (posi < 0) {
				int pos = -1 * (posi + 1);
				int low = pos - 1;
				if (low >= 0) {
					max = Math.max(max, set2[low] + i);
				}
			} else {
				max = Math.max(max, set2[posi] + i);
			}

		}
		return sum - 2 * (max);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
