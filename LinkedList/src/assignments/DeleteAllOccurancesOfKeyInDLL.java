package assignments;

// Delete all occurrences of a given key in a doubly linked list
// https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1

public class DeleteAllOccurancesOfKeyInDLL {
    class Node {
        int data;
        Node next;
        Node prev;

        Node() {
        };

        Node(int x) {
            data = x;
            next = null;
            prev = null;
        }
    }

    Node deleteAllOccurOfX(Node head, int x) {
        // Write your code here
        Node dummy = new Node();
        Node curr = dummy;
        Node temp = head;
        while (temp != null) {
            if (temp.data != x) {
                curr.next = temp;
                temp.prev = curr;
                curr = curr.next;
            }
            temp = temp.next;

        }
        //what if last node which is next to curr is k
        curr.next = null;
        return dummy.next;


    }
}
