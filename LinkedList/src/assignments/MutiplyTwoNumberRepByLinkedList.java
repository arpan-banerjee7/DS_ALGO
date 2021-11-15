package assignments;

// https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/

public class MutiplyTwoNumberRepByLinkedList {

	// Linked list node
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			next = null;
		}
	}

	// Multiply contents of two linked lists
	static long multiplyTwoLists(Node first, Node second) {
		long N = 1000000007;
		long num1 = 0, num2 = 0;

		while (first != null || second != null) {

			if (first != null) {
				num1 = ((num1) * 10) % N + first.data;
				first = first.next;
			}

			if (second != null) {
				num2 = ((num2) * 10) % N + second.data;
				second = second.next;
			}

		}
		return ((num1 % N) * (num2 % N)) % N;
	}

	// A utility function to print a linked list
	static void printList(Node node) {
		while (node != null) {
			System.out.print(node.data);
			if (node.next != null)
				System.out.print("->");
			node = node.next;
		}
		System.out.println();
	}

	// Driver program to test above function
	public static void main(String args[]) {
		// create first list 9->4->6
		Node first = new Node(9);
		first.next = new Node(4);
		first.next.next = new Node(6);
		System.out.print("First List is: ");
		printList(first);

		// create second list 8->4
		Node second = new Node(8);
		second.next = new Node(4);
		System.out.print("Second List is: ");
		printList(second);

		// Multiply the two lists and see result
		System.out.print("Result is: ");
		System.out.println(multiplyTwoLists(first, second));
	}
}
