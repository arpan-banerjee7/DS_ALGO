package assignments;
// Rotate a Linked List
// https://www.geeksforgeeks.org/problems/rotate-a-linked-list/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
// https://www.youtube.com/watch?v=uT7YI7XbTY8&list=PLgUwDviBIf0rAuz8tVcM0AymmhTRsfaLU&index=23
// modified k as per strivers question
public class RotateALinkedList {
    //Function to rotate a linked list.
    public Node rotate(Node head, int k) {
        if (head == null) return head;
        // add code here
        int length = 1;
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
            length++;
        }

        if (k == length) return head;
        // adjust k
        k = length - k;
        int nL = length - k - 1;

        curr.next = head; // point tail to head

        curr = head;
        int count = 0;
        Node temp = null;
        Node newHead = null;
        while (curr != null && count < nL) {
            curr = curr.next;
            count++;
        }

        if (curr != null) {
            newHead = curr.next;
            curr.next = null;
        }
        return newHead;
    }

    class Node {
        int data;
        Node next;

        Node() {
        };

        Node(int x) {
            data = x;
            next = null;
        }
    }

}
