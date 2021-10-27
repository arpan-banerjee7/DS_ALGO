package dp.knapsack.problems;
// Naive approach

public class MinSubsetSumDiff {

	private static boolean check(int[] nums, int sum, int n) {
		if (sum == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}

		if (nums[n - 1] > sum) {
			return check(nums, sum, n - 1);

		} else {
			return (check(nums, sum - nums[n - 1], n - 1) || check(nums, sum, n - 1));

		}

	}

	public static int minDifference(int arr[], int n) {
		// Your code goes here
		int minDiff = Integer.MAX_VALUE;
		int currDiff = 0;
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}

		for (int i = 0; i <= sum / 2; i++) {
			if (check(arr, i, n)) {
				currDiff = sum - (2 * i);
				minDiff = Math.min(currDiff, minDiff);
			}
		}
		return minDiff;

	}

	public static void main(String[] args) {
		int N = 4;
		int[] arr = { 1, 6, 11, 5 };
		System.out.println(minDifference(arr, N));

	}

}
