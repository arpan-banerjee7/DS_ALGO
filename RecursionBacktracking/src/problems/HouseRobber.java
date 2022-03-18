package problems;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/house-robber/

public class HouseRobber {
	
	class Solution {
		Integer[] dp;

		private int maxSum(int[] arr, int idx) {
			if (idx == 0)
				return arr[0];
			if (idx < 0)
				return 0;

			if (dp[idx] != null) {
				return dp[idx];
			}

			int sum1 = arr[idx] + maxSum(arr, idx - 2);
			int sum2 = maxSum(arr, idx - 1);

			return dp[idx] = Math.max(sum1, sum2);
		}

		public int rob(int[] nums) {
			int n = nums.length;
			dp = new Integer[n];
			return maxSum(nums, n - 1);
		}
	}
	
	// Sahil Srivatsava
	class Solution1 {
	    private int maxMoney(int idx,int[] nums,  Map<Integer,Integer> map){
	        if(idx==nums.length)
	            return 0;
	        if(idx>nums.length)
	            return 0;
	        
	        int currentKey=idx;
	        if(map.containsKey(currentKey))
	            return map.get(currentKey);
	        
	        int rob=nums[idx]+maxMoney(idx+2,nums,map);
	        int noRob=maxMoney(idx+1,nums,map);
	        int maxValue=Math.max(rob,noRob);
	        map.put(currentKey,maxValue);
	        return maxValue;
	        
	    }
	    public int rob(int[] nums) {
	        int n=nums.length;
	        Map<Integer,Integer> map=new HashMap<>();
	        return maxMoney(0,nums,map);
	    }
	}
	
	// generalized but difficult
	class Solution2 {
		public int rob(int[] nums) {
			int n = nums.length;
			int[] dp = new int[n];

			dp[0] = nums[0];
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 1; i < n; i++) {
				sum1 = nums[i];
				if (i > 1) {
					sum1 += dp[i - 2];
				}
				sum2 = dp[i - 1];
				dp[i] = Math.max(sum1, sum2);
			}
			return dp[n - 1];
		}
	}
}