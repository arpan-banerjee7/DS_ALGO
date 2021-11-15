package assignments;

/*
// Recursive- merge two sorted arrays- https://gist.github.com/itrare/7602c8dc845ec8dd41fc42b0a5d1680d
// Iterative solution- https://www.youtube.com/watch?v=Xb4slcp1U38&list=PLgUwDviBIf0r47RKH7fdWN54AbWFgGuii&index=4
// revcursive- https://www.youtube.com/watch?v=EXPY4SNCbhQ
// gfg- https://www.geeksforgeeks.org/merge-sort-for-linked-list/
 
 Results in stackoverflow last case 4,2 always goes with 4,2 since mid is 2, nextOdmid=null, mid,next=null, head is 4->2, so goes on and on.
 
 ```
 private ListNode getMiddle(ListNode head){
        ListNode slow_ptr=head;
        ListNode fast_ptr=head;
        
        while(fast_ptr!=null && fast_ptr.next!=null){
            slow_ptr=slow_ptr.next;
            fast_ptr=fast_ptr.next.next;
        }
         return slow_ptr;
    }
    ```
 */
public class SortLLinkedListUsingMergeSort {
	// not your ideal get mid
	private ListNode getMiddle(ListNode head) {
		ListNode slow = head, fast = head;

		while (fast != null && fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;

	}

	private ListNode mergeSortedLists(ListNode left, ListNode right) {
		ListNode res = null;
		if (left == null) {
			return right;
		}

		if (right == null) {
			return left;
		}

		if (left.val < right.val) {
			res = left;
			// merge happens here
			res.next = mergeSortedLists(left.next, right);
		} else {
			res = right;
			res.next = mergeSortedLists(left, right.next);
		}

		return res;
	}

	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode mid = getMiddle(head);
		ListNode nextOfMid = mid.next;

		mid.next = null;

		ListNode left = sortList(head);
		ListNode right = sortList(nextOfMid);

		return mergeSortedLists(left, right);

	}
}
