package assignments;
// Algo- https://www.youtube.com/watch?v=JA0btO100c0 code library

// intuition- https://www.youtube.com/watch?v=QfbOhn0WZ88&list=PLgUwDviBIf0r47RKH7fdWN54AbWFgGuii&index=11 striver

public class StartingNodeinLoopLinkedList {
	// detect cycle algo, then point to head, start moving head and slow ptr by one
	// where they meet will be the ans
	public ListNode detectCycle(ListNode head) {
		// [1] -1
		if (head == null || head.next == null) {
			return null;
		}

		ListNode slow_ptr = head;
		ListNode fast_ptr = head;

		while (fast_ptr != null && fast_ptr.next != null) {
			slow_ptr = slow_ptr.next;
			fast_ptr = fast_ptr.next.next;

			if (slow_ptr == fast_ptr) {
				break;
			}
		}

		// no cycle cannot use fast_ptr==null(because in odd length it points to last
		// value)
		if (fast_ptr != slow_ptr) {
			return null;
		}

		slow_ptr = head;
		while (slow_ptr != fast_ptr) {
			slow_ptr = slow_ptr.next;
			fast_ptr = fast_ptr.next;
		}
		return slow_ptr;
	}
}
