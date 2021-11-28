package striver.BT;

import java.util.ArrayList;
import java.util.List;

/*
Right/Left view of Binary Tree- can avoid using map b using a array an comapring the size before inserting
https://leetcode.com/problems/binary-tree-right-side-view/submissions/
https://www.youtube.com/watch?v=KV4mRzTjlAk&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=25 
*/

public class RightleftViewOfBT {
	private void rightViewHelper(TreeNode node, int l, List<Integer> res) {
		if (node == null) {
			return;
		}

		if (res.size() == l) {
			res.add(node.val);
		}
		rightViewHelper(node.right, l + 1, res);
		rightViewHelper(node.left, l + 1, res);

	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		// Map<Integer,Integer> map=new LinkedHashMap<>();
		rightViewHelper(root, 0, res);
		return res;
	}
}
