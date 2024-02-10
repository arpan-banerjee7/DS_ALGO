package assignments;

// 160. Intersection of Two Linked Lists
// https://leetcode.com/problems/intersection-of-two-linked-lists/description/
/*
Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
 */

import java.util.HashSet;
import java.util.Set;

public class IntersectionOf2LinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;
        Set<ListNode> set = new HashSet<>();
        while (currA != null) {
            set.add(currA);
            currA = currA.next;
        }
        while (currB != null) {

            if (set.contains(currB)) {
                return currB;
            }
            set.add(currB);
            currB = currB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != currB) {
            currA = currA.next;
            currB = currB.next;
            if (currA == currB) return currA;

            if (currA == null) currA = headB;
            if (currB == null) currB = headA;
        }
        return currA; // intersect at the start(both are same linkedlists)
    }


}
