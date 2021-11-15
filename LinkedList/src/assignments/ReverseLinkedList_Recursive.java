package assignments;
// https://www.geeksforgeeks.org/reverse-a-linked-list/
// https://leetcode.com/problems/reverse-linked-list/

public class ReverseLinkedList_Recursive {

	// Java program for reversing the linked list

	static Node head;

	static class Node {

		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	/* Recursive function to reverse the linked list */
	private Node reverse(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node rest = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return rest;
	}

	// prints content of double linked list
	void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
	}

	// Driver Code
	public static void main(String[] args) {
		ReverseLinkedList_Recursive list = new ReverseLinkedList_Recursive();
		ReverseLinkedList_Recursive.head = new Node(4);
		ReverseLinkedList_Recursive.head.next = new Node(3);
		ReverseLinkedList_Recursive.head.next.next = new Node(2);
		ReverseLinkedList_Recursive.head.next.next.next = new Node(1);

		System.out.println("Given Linked list");
		list.printList(head);
		head = list.reverse(head);
		System.out.println("");
		System.out.println("Reversed linked list ");
		list.printList(head);
	}
}

