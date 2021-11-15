package assignments;
// https://leetcode.com/problems/reverse-nodes-in-k-group/submissions/

// https://www.youtube.com/watch?v=znQ8wJxnRao Code Library
// iterative - https://www.youtube.com/watch?v=Of0HPkk3JgI&t=145s striver

public class ReverseNodesInK_Grp {
	static ListNode head;

	private boolean canReverseKgroups(ListNode head, int k) {
		int count = 0;
		while (head != null && count != k) {
			head = head.next;
			count++;
		}
		return count == k;
	}

	// 1->2->3->4->5->6->7->8
	public ListNode reverseKGroup(ListNode head, int k) {

		// if k size of list is less than k, do not reverse, return head
		if (!canReverseKgroups(head, k)) {
			return head;
		}

		// reverse first k nodes
		ListNode curr = head;
		ListNode next = null;
		ListNode prev = null;
		int count = 0;
		while (curr != null && count < k) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			count++;
		}

		// at this stage after 1st recursive call is over
		// 3->2->1 ideally(1<-2<-3 4->5->6->7->8)
		// next=4 prev=3 head=1

		// if next exixts then furthur try to reverse in k groups
		// and attach the current head to the returned node from the recursive call
		// since at last we are returning prev(which is the new head after it is
		// reversed)

		// 1) 3->2->1 next=4 prev=3 head=1 (returns 3)
		// 2) 6->5->4 next=7 prev=6 head=4 (retunrs 6)
		// 3) 7->8(not reversed) next=null prev=null head=7 (returns 7)

		// 3) when recursion call returns
		// 2) 6->5->4->7->8 7 joins with 4
		// 1) 3->2->1->6->5->4->7->8 4 joins with 1
		if (next != null) {
			head.next = reverseKGroup(next, k);
		}
		return prev;
	}

	// prints content of double linked list
	void printList(ListNode node) {
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
	}

	public static void main(String[] args) {
		ReverseNodesInK_Grp list = new ReverseNodesInK_Grp();
		int k = 3;
		list.head = new ListNode(1);
		list.head.next = new ListNode(2);
		list.head.next.next = new ListNode(3);
		list.head.next.next.next = new ListNode(4);
		list.head.next.next.next.next = new ListNode(5);
		list.head.next.next.next.next.next = new ListNode(6);
		list.head.next.next.next.next.next.next = new ListNode(7);
		list.head.next.next.next.next.next.next.next = new ListNode(8);

		System.out.println("Given Linked list");
		list.printList(head);
		head = list.reverseKGroup(head, k);
		System.out.println("");
		System.out.println("Reversed linked list ");
		list.printList(head);
	}
}
