package dp.bitmasking.problems;
// https://www.geeksforgeeks.org/job-assignment-problem-using-branch-and-bound/

// Techdose live class
public class TaskAssignMinCost {
	public static int assignTasks(int[][] cost) {
		int n = cost.length;

		int[] dp = new int[1 << n];
		for (int i = 0; i < (1 << n); i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[0] = 0;
		// Try all combinations
		for (int mask = 0; mask < (1 << n); mask++) {
			// number of set bits denotes the next person who will be
			// assigned the task
			int person = Integer.bitCount(mask);
			for (int j = 0; j < n; j++) {
				// if a bit is unset then only we can do any further operation
				// if unset that means that task is still unassigned
				if (((mask) & (1 << j)) == 0) {
					dp[mask | (1 << j)] = Math.min(dp[mask | (1 << j)], dp[mask] + cost[person][j]);
				}
			}
		}
		return dp[(1 << n) - 1];
	}

	public static void main(String[] args) {
		// int[][] cost = { { 3, 4, 1 }, { 3, 6, 5 }, { 4, 5, 6 } }; // output 9
		int[][] cost2 = { { 9, 2, 7, 8 }, { 6, 4, 3, 7 }, { 5, 8, 1, 8 }, { 7, 6, 9, 4 } };// output 13
		System.out.println(assignTasks(cost2));
	}

}
