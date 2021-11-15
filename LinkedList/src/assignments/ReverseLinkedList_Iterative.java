package assignments;

public class ReverseLinkedList_Iterative {
	static Node head;

	static class Node {

		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	/* Function to reverse the linked list */
	Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
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
		ReverseLinkedList_Iterative list = new ReverseLinkedList_Iterative();
		ReverseLinkedList_Iterative.head = new Node(4);
		ReverseLinkedList_Iterative.head.next = new Node(3);
		ReverseLinkedList_Iterative.head.next.next = new Node(2);
		ReverseLinkedList_Iterative.head.next.next.next = new Node(1);

		System.out.println("Given Linked list");
		list.printList(head);
		head = list.reverse(head);
		System.out.println("");
		System.out.println("Reversed linked list ");
		list.printList(head);
	}
}

