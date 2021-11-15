package assignments;
// https://leetcode.com/problems/add-two-numbers/
// https://www.youtube.com/watch?v=LBVsXSMOIk4&list=PLgUwDviBIf0r47RKH7fdWN54AbWFgGuii&index=6 striver

// TC o(max(m,n))
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

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode temp = dummy;
		int carry = 0;
		int sum = 0;
		while (l1 != null || l2 != null || carry == 1) {
			sum = 0;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}

			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}

			sum += carry;
			carry = sum / 10;
			ListNode newNode = new ListNode(sum % 10);
			temp.next = newNode;
			temp = temp.next;
		}
		return dummy.next;
	}
}
