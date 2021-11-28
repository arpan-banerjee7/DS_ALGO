package striver.BT;

import java.util.ArrayList;
import java.util.List;

/*https://www.youtube.com/watch?v=80Zug6D1_r4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=38 Take u forward
TC: O(n)  SC: O(1)
--> Morris Traveral
*/
public class MorrisTraversal {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> inorder = new ArrayList<Integer>();

		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				inorder.add(cur.val);
				cur = cur.right;
			} else {
				TreeNode prev = cur.left;
				while (prev.right != null && prev.right != cur) {
					prev = prev.right;
				}

				if (prev.right == null) {
					prev.right = cur;
					// inorder.add(cur.val); Add here for preorder traversal
					cur = cur.left;
				} else {
					prev.right = null;
					inorder.add(cur.val); // remove this for preorder taversal
					cur = cur.right;
				}
			}
		}
		return inorder;
	}
}
