package array.questions;

import java.util.Arrays;

/*135. Candy Distribution Problem
[1,2,87,87,87,2,1] 13
[1,2,3,4,2,1] 13
[1,2,3,2,4,2,1] 13

https://leetcode.com/problems/candy/discuss/1230528/Simple-Java-solution-with-comprehensive-comments-and-explanation-for-intuition
Chocolate Distribution Problem | Set 2 - GeeksforGeeks*/

public class CandyDistributionProblem {
	public int candy(int[] ratings) {
		int n = ratings.length;
		int[] candies = new int[n];
		Arrays.fill(candies, 1);

		for (int i = 1; i < n; i++) {

			if (ratings[i - 1] < ratings[i]) {
				candies[i] = candies[i - 1] + 1;
			}
		}

		for (int i = n - 1; i > 0; i--) {
			if (ratings[i] < ratings[i - 1]) {
				candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
			}
		}

		int totalCandies = 0;
		for (int candy : candies) {
			totalCandies += candy;
		}
		return totalCandies;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
