package striver.BT;

// 100. Same Tree
// Ref: https://www.youtube.com/watch?v=BhuvF_-PWS0&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=19 Take u forward

public class SameTree {

	public static void main(String[] args) {

	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null || q == null)
			return (p == q);

		return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

}