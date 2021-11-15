package assignments;

// https://leetcode.com/problems/linked-list-cycle/
// https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/

public class LoopDetectionInLinkedLIst {

	public boolean hasCycle(ListNode head) {
		ListNode slow_ptr = head;
		ListNode fast_ptr = head;

		while (fast_ptr != null && fast_ptr.next != null) {
			slow_ptr = slow_ptr.next;
			fast_ptr = fast_ptr.next.next;

			if (slow_ptr == fast_ptr) {
				return true;
			}
		}
		return false;
	}
}
