package dp.knapsack.problems;
// https://youtu.be/ot_XBHyqpFc

// Same as count number of subsets with given sum, here sum = arraySum+diff/2
public class CountSubsetsDiff {
	private static int countTargetSum(int[] nums, int count, int sum, int n) {
		if (sum == 0) {
			count++;
			return count;
		}

		if (n == 0) {
			return 0;
		}

		// if greater do not include in the subset
		if (nums[n - 1] > sum) {
			return countTargetSum(nums, count, sum, n - 1);
		}

		int c1 = countTargetSum(nums, count, sum - nums[n - 1], n - 1);
		int c2 = countTargetSum(nums, count, sum, n - 1);

		return c1 + c2;

	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 3 };
		int diff = 1;
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		if (Math.abs(diff) > sum)
			System.out.println(0);

		if ((diff + sum) % 2 != 0) {
			System.out.println(0);
		}

		int t = (sum + diff) / 2;
		System.out.println(countTargetSum(arr, 0, t, arr.length)); // output-3

	}

}
