package assignments;

// Leetcode 1578. Minimum Deletion Cost to Avoid Repeating Letters
public class MinimumDeletionCosttoAvoidRepeatingLetters {
	// concept-- https://www.youtube.com/watch?v=3zMxJJxYph4

	// see the chunks, if there are m repeating chars, we have to delete m-1 to
	// make it non adjacent, how to delete the min costs- take total cost of that
	// chunk-
	// max cost among the chunk
	public static int minCost(String s, int[] cost) {
		int n = s.length();
		int gSum = 0;
		int gMax = 0;
		int ans = 0;

		for (int i = 0; i < n; i++) {
			if (i > 0 && (s.charAt(i) != s.charAt(i - 1))) {
				// chunk endded here
				// now we have to perform a delete op so calculate the cost
				ans += gSum - gMax;

				// start over again for identifying a new chunk
				gSum = 0;
				gMax = 0;
			}
			// not keeping in else because when a chunk ends, and new chunk starts
			// bw that there would be a char who will contribute nothing to ans
			// but will enter the if block, we have to keep track of its cost
			gSum += cost[i];
			gMax = Math.max(gMax, cost[i]);
		}

		// for the last chunk
		ans += gSum - gMax;
		return ans;
	}

	public static void main(String[] args) {
		String s = "abaac";
		int[] cost = {1,2,3,4,5};
		System.out.println(minCost(s, cost));// Output: 3
	}

}
