package assignments;

import java.util.Arrays;
// 1235. Maximum Profit in Job Scheduling

/*
https://www.youtube.com/watch?v=43bs9C2Zjus	
https://leetcode.com/problems/maximum-profit-in-job-scheduling/
*Similar question*
### https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
Here each job -
1. same start day
2. takes 1 unit time to complete

**Logic**
idea is to perform job with maximum profit first, but on the last day of its deadline, so that we can perform other jobs as well 
//TC- O(nlogn)+o(m*n)--> to do cut down o(m*n)

### 1353. Maximum Number of Events That Can Be Attended
*/

public class MaxProfitJobScheduling {

	// Memory Limit Exceeded
	// greedy won't work here, we will try out all possible combinations, of
	// pickable jobs
	// and get that combination which returns the max profit

	Integer[][] dp;

	private int maxProfit(int[][] jobs, int end, int index) {
		if (index == jobs.length) {
			return 0;
		}
		if (dp[end][index] != null) {
			return dp[end][index];
		}

		// when end time of current job is > start time of next job
		// cannot pick that job, we wil skip that job and move to the next job
		if (end > jobs[index][0]) {
			return dp[end][index] = maxProfit(jobs, end, index + 1);
		}

		// we jave 2 choices, pick that job, in that case we will have to add its profit
		// skip that job, dn t add its profit
		// like LCS take the max of both the cases
		return dp[end][index] = Math.max(jobs[index][2] + maxProfit(jobs, jobs[index][1], index + 1),
				maxProfit(jobs, end, index + 1));
	}

	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = profit.length;
		int[][] jobs = new int[n][3];
		int maxEnd = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			maxEnd = Math.max(maxEnd, endTime[i]);
			jobs[i][0] = startTime[i];
			jobs[i][1] = endTime[i];
			jobs[i][2] = profit[i];
		}

		// very imp step, we have to sort it, we will start our trials with the
		// jobs starting first and try all possible combinations
		Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
		dp = new Integer[maxEnd + 1][n];

		return maxProfit(jobs, 0, 0);
	}

	/*----------------------------------------------------------------------------------------------------------------------*/
//	 Integer[] dp;
//	    private int maxProfit(int[][] jobs,int end, int index){
//	        if(index==jobs.length){
//	            return 0;
//	        }
//	       
//	        // when end time of current job is > start time of next job
//	        // cannot pick that job, we wil skip that job and move to the next job
//	        if(end>jobs[index][0]){
//	            return maxProfit(jobs,end,index+1);
//	        }
//	        
//	         if(dp[index]!=null){
//	            return dp[index];
//	        }
//	        
//	        // we jave 2 choices, pick that job, in that case we will have to add its profit
//	        // skip that job, dn t add its profit
//	        // like LCS take the max of both the cases
//	        return dp[index]=Math.max(jobs[index][2]+maxProfit(jobs,jobs[index]              [1],index+1),maxProfit(jobs,end,index+1));
//	    }
//	    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
//	        int n=profit.length;
//	        int[][] jobs=new int[n][3];
//	        int maxEnd=Integer.MIN_VALUE;
//	        for(int i=0;i<n;i++){
//	            maxEnd=Math.max(maxEnd,endTime[i]);
//	            jobs[i][0]=startTime[i];
//	            jobs[i][1]=endTime[i];
//	            jobs[i][2]=profit[i];
//	        }
//	        
//	        // very imp step, we have to sort it, we will start our trials with the 
//	        // jobs starting first and try all possible combinations
//	        Arrays.sort(jobs,(a,b)->a[0]-b[0]);
//	        dp=new Integer[n];
//	        
//	        return maxProfit(jobs,0,0);
//	    }

	public static void main(String[] args) {
		/*
		 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
		 * Output: 120 Explanation: The subset chosen is the first and fourth job. Time
		 * range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
		 */

	}

}
