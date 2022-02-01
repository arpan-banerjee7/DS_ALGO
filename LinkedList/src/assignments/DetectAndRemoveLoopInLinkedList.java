package assignments;

public class DetectAndRemoveLoopInLinkedList {
	class Node {
		int data;
		Node next;
	}

	// Function to remove a loop in the linked list.
	public static void removeLoop(Node head) {
		// code here
		// remove the loop without losing any nodes
		Node slow = head;
		Node fast = head;
		int flag = 0;
		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			slow = head;
			if (slow != fast) {
				while (slow.next != fast.next) {
					slow = slow.next;
					fast = fast.next;
				}

			} else {// when they meet at the head-- very very imp
				while (fast.next != slow) {
					fast = fast.next;
				}
			}
			fast.next = null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
