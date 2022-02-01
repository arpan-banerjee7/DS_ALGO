package assignments;

public class CopyLinkedListWithRandomPointers {
	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	// https://www.youtube.com/watch?v=VNf6VynfpdM
	// TC-o(n+n+n)=o(n)
	// space - o(1)
	class Solution {
		public Node copyRandomList(Node head) {

			// first traversal- insert copy nodes ajacent to given nodes
			Node curr = head;
			Node temp = head;
			Node copyNode = null;
			while (curr != null) {
				temp = curr.next;
				copyNode = new Node(curr.val);
				copyNode.next = temp;
				curr.next = copyNode;
				curr = temp;
			}

			// second traversal- link the random pointers
			curr = head;
			while (curr != null) {
				if (curr.random != null) {
					curr.next.random = curr.random.next;
				}
				curr = curr.next.next;
			}

			// third traversal- make the given list to its original state
			// and seperate out the duplicate list
			curr = head;
			Node pseudoHead = new Node(0);
			copyNode = pseudoHead;
			while (curr != null) {
				copyNode.next = curr.next;
				copyNode = copyNode.next;
				curr.next = curr.next.next;
				curr = curr.next;
			}
			return pseudoHead.next;
		}
	}
}
