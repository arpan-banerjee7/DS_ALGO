package assignments;
// 328. Odd Even Linked List
// https://leetcode.com/problems/odd-even-linked-list/description/
/*
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        // empty list
        if (head == null) {
            return null;
        }
        ListNode dummyEven = new ListNode();
        ListNode even = dummyEven;
        ListNode prev = null;
        ListNode odd = head;

        while (odd != null && odd.next != null) {
            ListNode newNode = odd.next;
            even.next = newNode;
            even = even.next;

            odd.next = odd.next.next;
            prev = odd;
            odd = odd.next;
        }
        even.next = null;
        if (odd == null) {
            prev.next = dummyEven.next;
        } else {
            odd.next = dummyEven.next;
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
