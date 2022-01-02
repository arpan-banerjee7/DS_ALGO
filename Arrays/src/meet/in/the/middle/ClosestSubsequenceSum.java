package meet.in.the.middle;
// 1755. Closest Subsequence Sum

import java.util.Arrays;

/*1755. Closest Subsequence Sum
https://leetcode.com/problems/closest-subsequence-sum/
Cocepts of BItMasking-https://www.youtube.com/watch?v=xFWgZ5DTjFo
https://leetcode.com/problems/closest-subsequence-sum/discuss/1053751/Java-Detailed-Explanation-%2B-comments-or-Meet-In-the-Middle-or-O-(n-*-2(n2)-or
*/
public class ClosestSubsequenceSum {
	// TC - 2^(n/2)*n/2
	private void createSet(int[] set, int[] a, int n1) {
		int sum = 0;
		int setIndex = 0;
		for (int i = 0; i < n1; i++) {
			sum = 0;
			for (int j = 0; j < a.length; j++) {
				if ((i & (1 << j)) > 0) {
					sum += a[j];
				}
			}
			set[setIndex++] = sum;
		}

	}

	public int minAbsDifference(int[] nums, int goal) {
		int n = nums.length;
		int[] set1 = new int[1 << n / 2];
		int[] set2 = new int[1 << (n - n / 2)];
		createSet(set1, Arrays.copyOfRange(nums, 0, n / 2), 1 << n / 2);
		createSet(set2, Arrays.copyOfRange(nums, n / 2, n), 1 << (n - n / 2));

		// so till now we have two arrays
		// first array has all posible subset sums of half of the array
		// second array has all possible subset sums of the next half of the array

		// now we have also sorted the second array
		// idea is- for each element in first array we will try to find an element in
		// the second array such that abs diff(goal-(arr[i]+arr[2])) is min

		// for this we already have arr[i],to find the required arr[2]
		// so abs(goal-arr[i]) should be as small as
		// possible
		// for eg goal is 5, arr[i]=2, ideal arr[j] would be 3, then ans would be 0
		// bt in case we dn t find 3 in second array,we will look for either its upper
		// bound
		// or lower bound, i.e just the next greater number or next smaller number
		Arrays.sort(set2);
		int min = Integer.MAX_VALUE;
		for (int i : set1) {
			// log2^N/2 => N/2
			int posi = Arrays.binarySearch(set2, goal - i);
			if (posi >= 0)
				return 0;
			int pos = -1 * (posi + 1);
			int low = pos - 1;
			if (low >= 0)
				min = Math.min(min, Math.abs(goal - (i + set2[low])));
			if (pos < set2.length)
				min = Math.min(min, Math.abs(goal - (i + set2[pos])));
		}
		return min;
	}

	public static void main(String[] args) {
		/*Input
		["NumArray", "sumRange", "sumRange", "sumRange"]
		[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
		Output
		[null, 1, -1, -3]

		Explanation
		NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
		numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
		numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
		numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
		*/

	}

}
