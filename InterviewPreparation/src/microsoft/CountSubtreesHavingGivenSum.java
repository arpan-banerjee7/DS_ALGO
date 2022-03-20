package microsoft;

// Count Number of SubTrees having given Sum 
// https://practice.geeksforgeeks.org/problems/count-number-of-subtrees-having-given-sum/1/

public class CountSubtreesHavingGivenSum {
	int count = 0;

	private int countSubTrees(Node root, int target) {
		if (root == null)
			return 0;

		int left = countSubTrees(root.left, target);
		int right = countSubTrees(root.right, target);
		if (left + right + root.val == target)
			count++;
		return left + right + root.val;
	}

	private void countTreesUtil(Node root, int target) {
		if (root == null)
			return;

		int left = countSubTrees(root.left, target);
		int right = countSubTrees(root.right, target);

		if (left + right + root.val == target)
			count++;
	}

	// Function to count number of subtrees having sum equal to given sum.
	int countSubtreesWithSumX(Node root, int X) {
		// Add your code here.
		countTreesUtil(root, X);
		return count;

	}

	public static void main(String[] args) {
		/*Input:
		       5
		    /    \
		  -10     3
		 /   \   /  \
		 9   8 -4    7
		X = 7
		Output: 2
		Explanation: Subtrees with sum 7 are
		[9, 8, -10] and [7] (refer the example
		in the problem description).
*/
	}

}
