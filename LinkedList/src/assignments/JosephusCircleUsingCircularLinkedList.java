package assignments;

// https://www.youtube.com/watch?v=ULUNeD0N9yI Aditya Verma
// https://www.youtube.com/watch?v=fZ3p2Iw-O2I Gaurav Sen
// https://www.youtube.com/watch?v=46zD5d9y9b4 Anuj Bhai

//Java Code to find the last man Standing
public class JosephusCircleUsingCircularLinkedList {

	// Node class to store data
	static class Node {
		public int data;
		public Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	/*
	 * Function to find the only person left after one in every m-th node is killed
	 * in a circle of n nodes
	 */
	static void getJosephusPosition(int m, int n) {
		// Create a circular linked list of
		// size N.
		Node head = new Node(1);
		Node prev = head;
		for (int i = 2; i <= n; i++) {
			prev.next = new Node(i);
			prev = prev.next;
		}

		// Connect last node to first
		prev.next = head;

		/*
		 * while only one node is left in the linked list
		 */
		Node ptr1 = head, ptr2 = head;

		while (ptr1.next != ptr1) {

			// Find m-th node
			int count = 1;
			while (count != m) {
				ptr2 = ptr1;
				ptr1 = ptr1.next;
				count++;
			}

			/* Remove the m-th node */
			ptr2.next = ptr1.next;
			ptr1 = ptr2.next;
		}
		System.out.println("Last person left standing " + "(Josephus Position) is " + ptr1.data);
	}

	/* Driver program to test above functions */
	public static void main(String args[]) {
		 int n = 14, m = 2;
		getJosephusPosition(m, n); // Output 13
	}
}