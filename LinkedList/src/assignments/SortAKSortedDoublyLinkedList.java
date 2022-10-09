package assignments;

import java.util.Comparator;
import java.util.PriorityQueue;

// Sort a k sorted doubly linked list
// https://www.geeksforgeeks.org/sort-k-sorted-doubly-linked-list/
// https://www.codingninjas.com/codestudio/problems/sort-a-k-sorted-doubly-linked-list_1118118?leftPanelTab=0
// https://www.hackerrank.com/contests/test-your-skills-in-linked-list/challenges
// https://www.youtube.com/watch?v=dYfM6J1y0mU

public class SortAKSortedDoublyLinkedList {

	static Node head;

	static class Node {
		int data;
		Node next, prev;

		Node(int d) {
			data = d;
			next = prev = null;
		}
	}

	class compareNode implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return n1.data - n2.data;
		}
	}

	// function to sort a k sorted doubly linked list
	Node sortAKSortedDLL(Node head, int k) {

		// if list is empty
		if (head == null)
			return head;

		// priority_queue 'pq' implemented as min heap with the
		// help of 'compare' function in compare Node class
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new compareNode());

		Node newHead = null, last = null;

		// Create a Min Heap of first (k+1) elements from
		// input doubly linked list
		for (int i = 0; head != null && i <= k; i++) {

			// push the node on to 'pq'
			pq.add(head);

			// move to the next node
			head = head.next;
		}

		// loop till there are elements in 'pq'
		while (!pq.isEmpty()) {

			// place root or top of 'pq' at the end of the
			// result sorted list so far having the first node
			// pointed to by 'newHead'
			// and adjust the required links
			if (newHead == null) {
				newHead = pq.peek();
				newHead.prev = null;

				// 'last' points to the last node
				// of the result sorted list so far
				last = newHead;
			}

			else {
				last.next = pq.peek();
				pq.peek().prev = last;
				last = pq.peek();
			}

			// remove element from 'pq'
			pq.poll();

			// if there are more nodes left in the input list
			if (head != null) {

				// push the node on to 'pq'
				pq.add(head);

				// move to the next node
				head = head.next;
			}
		}

		// making 'next' of last node point to NULL
		last.next = null;

		// new head of the required sorted DLL
		return newHead;
	}

	/* UTILITY FUNCTIONS */
	/*
	 * Function to insert a node at the beginning of the Doubly Linked List
	 */
	void push(int new_data) {
		/* allocate node */
		Node new_node = new Node(new_data);

		/*
		 * since we are adding at the beginning, prev is always NULL
		 */
		new_node.prev = null;

		/* link the old list off the new node */
		new_node.next = head;

		/* change prev of head node to new node */
		if (head != null) {
			head.prev = new_node;
		}

		/* move the head to point to the new node */
		head = new_node;
	}

	/*
	 * Function to print nodes in a given doubly linked list This function is same
	 * as printList() of singly linked list
	 */
	void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
	}

	// Driver code
	public static void main(String[] args) {
		SortAKSortedDoublyLinkedList list = new SortAKSortedDoublyLinkedList();

		/*
		 * Let us create a k sorted doubly linked list to test the functions Created
		 * doubly linked list will be 3<->6<->2<->12<->56<->8
		 */
		list.push(8);
		list.push(56);
		list.push(12);
		list.push(2);
		list.push(6);
		list.push(3);

		int k = 2;

		System.out.println("Original Doubly linked list:");
		list.printList(head);

		Node sortedDLL = list.sortAKSortedDLL(head, k);
		System.out.println("");
		System.out.println("Doubly Linked List after sorting:");
		list.printList(sortedDLL);
	}
}
