package meet.in.the.middle;

import java.util.Arrays;

// https://codeforces.com/blog/entry/88660
// https://www.codingninjas.com/codestudio/problems/0-1-knapsack_920542?leftPanelTab=1
// https://www.interviewbit.com/problems/0-1-knapsack/

public class Zero_1_Knapsack {
	private static void createSet(int[][] res, int[] W, int[] val, int wtLimit, int n1) {
		int resIndex = 0;
		for (int i = 0; i < n1; i++) {
			int tempWeight = 0;
			int tempCost = 0;
			for (int j = 0; j < val.length; j++) {
				if ((i & (1 << j)) != 0) { // determines which elements are included in the set
					tempWeight += W[j];
					tempCost += val[j];
					if (tempWeight > wtLimit) {
						break;
					}
				}
			}
			if (tempWeight <= wtLimit)
				res[resIndex++] = new int[] { tempWeight, tempCost };
		}
	}

	private static int knapSackMeetInTheMiddle(int[] W, int[] val, int wtLimit, int n) {

		int[][] leftPart = new int[1 << n / 2][2];
		int[][] rightPart = new int[1 << (n - n / 2)][2];

		createSet(leftPart, Arrays.copyOfRange(W, 0, n / 2), Arrays.copyOfRange(val, 0, n / 2), wtLimit, 1 << n / 2);
		createSet(rightPart, Arrays.copyOfRange(W, n / 2, n), Arrays.copyOfRange(val, n / 2, n), wtLimit,
				1 << (n - n / 2));

		// first sort on weight in ascending order and if same weights value in desc
		Arrays.sort(rightPart, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

		// for each item in leftPart apply binary search in the rightPart,keeping in
		// mind that value should
		// be max and weight<=LimitWeight

		int maxValue = Integer.MIN_VALUE;
		for (int[] leftItem : leftPart) {
			int low = 0, high = rightPart.length - 1;

			while (low <= high) {
				int mid = low + (high - low) / 2;
				int[] rightItem = rightPart[mid];
				if (leftItem[0] + rightItem[0] <= wtLimit) {
					maxValue = Math.max(maxValue, (leftItem[1] + rightItem[1]));
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return maxValue;
	}

	static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

		/*
		 * Your class should be named Solution Don't write main(). Don't read input, it
		 * is passed as function argument. Change in the given tree itself. No need to
		 * return or print the output. Taking input and printing output is handled
		 * automatically.
		 */
		return knapSackMeetInTheMiddle(weight, value, maxWeight, n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
