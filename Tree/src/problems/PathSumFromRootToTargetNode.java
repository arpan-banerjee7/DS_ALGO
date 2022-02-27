package problems;

public class PathSumFromRootToTargetNode {

	private int findSum(Node root, Node target) {
		if (root == null)
			return 0;

		if (root.val == target.val)
			return root.val;

		int left = findSum(root.left, target);
		int right = findSum(root.right, target);

		if (left == 0 && right == 0)
			return 0;
		int sum = left + right + root.val;
		return sum;

	}

	public static void main(String[] args) {

	}

}
