package microsoft;

import java.util.LinkedList;
import java.util.Queue;

// 116. Populating Next Right Pointers in Each Node
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// extra space- https://www.youtube.com/watch?v=yFTr_EnlERM naresh gupta
// o(1) space- https://www.youtube.com/watch?v=uI5CB32O_uo ayushi sharma

public class PopulatingNextRightPointersInEachNode {

	// extra space
	// doing level order traversal - BFS
	public Node connect(Node root) {
		Queue<Node> queue = new LinkedList<>();
		if (root != null) {
			queue.add(root);
		}

		while (!queue.isEmpty()) {
			int size = queue.size();
			int temp = size;
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				temp--;

				// actual step
				if (temp == 0) {
					node.next = null;
				} else {
					node.next = queue.peek();
				}

				if (node.left != null) {
					queue.add(node.left);
				}

				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}
		return root;
	}

	// o(1) space
	public Node connect1(Node root) {
		if (root == null)
			return null;
		Node curr = root; // denoting each level
		while (curr.left != null) {
			Node temp = curr; // for moving to next nodes in each level
			while (temp != null) {
				temp.left.next = temp.right;
				temp.right.next = temp.next == null ? null : temp.next.left;
				temp = temp.next;
			}
			curr = curr.left;
		}
		return root;
	}

	public static void main(String[] args) {

	}

}
