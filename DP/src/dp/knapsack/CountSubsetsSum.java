package dp.knapsack;
// https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x-using-recursion/

public class CountSubsetsSum {

	private static int countSubsetSum(int[] nums, int count, int sum, int n) {
		if (sum == 0) {
			count++;
			return count;
		}

		if (n == 0) {
			return 0;
		}

		// if greater do not inlcude in the subet
		if (nums[n - 1] > sum) {
			return countSubsetSum(nums, count, sum, n - 1);
		}

		int c1 = countSubsetSum(nums, count, sum - nums[n - 1], n - 1);
		int c2 = countSubsetSum(nums, count, sum, n - 1);

		return c1 + c2;

	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5 };
		int sum = 10;
		System.out.println(countSubsetSum(arr, 0, sum, arr.length));// output=3
	}

}
