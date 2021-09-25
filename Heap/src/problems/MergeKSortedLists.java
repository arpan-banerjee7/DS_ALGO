package problems;

import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/
// https://www.youtube.com/watch?v=kpCesr9VXDA
// https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/

// see merge k sorted arrays
// https://www.codingninjas.com/codestudio/problems/merge-k-sorted-arrays_975379?leftPanelTab=2

//                  ---3 solutions---
//1) Add all the nodes in an array, sort it and form a linkedlist from the sorted array-
//TC- o(nlogn) space-o(n)

//2) Divide and conquer, similar to merge sort algo- TC- o(n*klogk)
//it has k linkedlists, so 2^k=n(tot number of nodes/elements) so logk levels
//in each level we traverse through a length of k lists, so total nodes=n*k
//so total TC is o(n*klogk) Space- n*k

//3) Min heap- TC o(n*logk) ignore the first tc to build heap o(k)
//space- o(n)

//3 rd solution
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

}

public class MergeKSortedLists {

	public static ListNode mergeKLists(ListNode[] lists) {
		// min heap -- 1,1,2
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

		// []
		if (lists.length == 0) {
			return null;
		}
		// build a min heap out of the first nodes of each linked lists
		for (ListNode head : lists) {
			if (head != null) {
				minHeap.add(head);
			}
		}

		// [[]]
		if (minHeap.isEmpty()) {
			return null;
		}

		// To get the head pointer, poll just the first element from the min heap
		// and mark it as head, and temp(to iterate)
		ListNode l = minHeap.poll();
		if (l.next != null) {
			minHeap.add(l.next);
		}

		// create the head node
		ListNode head = new ListNode(l.val);
		ListNode temp = head;

		while (!minHeap.isEmpty()) {
			ListNode curr = minHeap.poll();
			if (curr.next != null) {
				minHeap.add(curr.next);
			}
			ListNode n = new ListNode(curr.val);
			temp.next = n;
			temp = temp.next;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode[] arr = new ListNode[3];

		arr[0] = new ListNode(1);
		arr[0].next = new ListNode(4);
		arr[0].next.next = new ListNode(5);

		arr[1] = new ListNode(1);
		arr[1].next = new ListNode(3);
		arr[1].next.next = new ListNode(4);

		arr[2] = new ListNode(2);
		arr[2].next = new ListNode(6);

		ListNode head = mergeKLists(arr);
		while (head != null) {
			System.out.print(head.val + "->");

			head = head.next;
		}
		System.out.println();
	}

}
