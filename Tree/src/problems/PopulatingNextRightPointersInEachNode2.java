package problems;

// 117. Populating Next Right Pointers in Each Node II
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

public class PopulatingNextRightPointersInEachNode2 {
	public Node connect(Node root) {
		Node head = null; // head of the next level
		Node prev = null; // the leading node on the next level
		Node cur = root; // current node of current level

		while (cur != null) {

			while (cur != null) { // iterate on the current level
				// left child
				if (cur.left != null) {
					if (prev != null) {
						prev.next = cur.left;
					} else {
						head = cur.left;
					}
					prev = cur.left;
				}
				// right child
				if (cur.right != null) {
					if (prev != null) {
						prev.next = cur.right;
					} else {
						head = cur.right;
						System.out.println(head.val);
					}
					prev = cur.right;
				}
				// move to next node
				cur = cur.next;
			}

			// move to next level
			cur = head;
			head = null;
			prev = null;
		}
		return root;
	}

	public static void main(String[] args) {
		/*
		 * Input: root = [1,2,3,4,5,null,7] Output: [1,#,2,3,#,4,5,7,#] Explanation:
		 * Given the above binary tree (Figure A), your function should populate each
		 * next pointer to point to its next right node, just like in Figure B. The
		 * serialized output is in level order as connected by the next pointers, with
		 * '#' signifying the end of each level.
		 */
	}

}
