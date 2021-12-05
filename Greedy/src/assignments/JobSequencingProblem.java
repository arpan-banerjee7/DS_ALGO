package assignments;

import java.util.Arrays;


/*Job Sequencing gfg-// idea is to perform job with maximum profit first, but on the last day
// of its deadline, so that we can perform other jobs as well 
//TC- O(nlogn)+o(m*n)--> to do cut down o(m*n)
https://www.youtube.com/watch?v=LjPx4wQaRIs&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=3
https://www.geeksforgeeks.org/job-sequencing-problem/
*/
public class JobSequencingProblem {
	static class Job {
		int id, profit, deadline;

		Job(int x, int y, int z) {
			this.id = x;
			this.deadline = y;
			this.profit = z;
		}
	}

	// idea is to perform job with maximum profit first, but on the last day
	// of its deadline, so that we can perform other jobs as well
	static int[] JobScheduling(Job arr[], int n) {

		// sort based on max profit
		Arrays.sort(arr, (a, b) -> b.profit - a.profit);

		int maxDeadline = 0;
		for (int i = 0; i < n; i++) {
			maxDeadline = Math.max(arr[i].deadline, maxDeadline);
		}

		// array to keep track of days on which jobs are performed
		int[] days = new int[maxDeadline + 1];

		// iterate through the job array and try to allocate the job on the
		// last date of its deadline
		int count = 0;
		int totalProfit = 0;
		for (Job job : arr) {
			int deadline = job.deadline;
			for (int i = deadline; i > 0; i--) {
				if (days[i] == 0) {
					days[i] = job.id;
					count++;
					totalProfit += job.profit;
					break;
				}
			}
		}

		return new int[] { count, totalProfit };
	}

	public static void main(String[] args) {
		/*
		 * Input: N = 4 Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)} Output: 2 60
		 * Explanation: Job1 and Job3 can be done with maximum profit of 60 (20+40).
		 */
	}

}
