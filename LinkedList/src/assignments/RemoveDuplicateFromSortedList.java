package assignments;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// https://www.youtube.com/watch?v=dhLtP3UriEU  code library

public class RemoveDuplicateFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode curr = head;

		while (curr != null && curr.next != null) {
			if (curr.val == curr.next.val) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
		return head;
	}
}
