package assignments;

public class RemoveDuplicatesFromSortedDLL {
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

    Node removeDuplicates(Node head) {
        // Code Here.
        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.data == curr.next.data) {
                // curr.next.prev=null;
                curr.next = curr.next.next;
                if (curr.next != null) {
                    curr.next.prev = curr;
                }

            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}
