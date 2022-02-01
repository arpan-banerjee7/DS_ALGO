package problems;

public class TransformToSumTree {
	class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private int calSum(Node root) {
		if (root == null)
			return 0;
		int leftSum = calSum(root.left);
		int rightSum = calSum(root.right);

		// leaf node
		if (leftSum == 0 && rightSum == 0) {
			int leafData = root.data;
			root.data = 0;
			return leafData;
		}
		int sum = root.data + leftSum + rightSum;
		root.data = leftSum + rightSum;
		return sum;
	}

	public void toSumTree(Node root) {
		// add code here.
		calSum(root);
	}

	public static void main(String[] args) {
		/*
		 *     20
          /    \
        4        12
       /  \     /  \
     0     0   0    0
		 */

	}

}
