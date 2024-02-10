package assignments;
//  Sort linked list of 0s 1s 2s
// https://www.codingninjas.com/studio/problems/sort-linked-list-of-0s-1s-2s_1071937?leftPanelTabValue=PROBLEM
// Take three dummy lists indocating 0 1 and 2

public class SortLL012 {
    public Node sortList(Node head) {
        // Write your code here
        Node dummy0 = new Node();
        Node dummy1 = new Node();
        Node dummy2 = new Node();

        Node zero = dummy0;
        Node one = dummy1;
        Node two = dummy2;

        Node curr = head;
        while (curr != null) {
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }
        two.next = null;
        one.next = dummy2.next;
        zero.next = dummy1.next;
        return dummy0.next;
    }

    class Node {
        public int data;
        public Node next;

        Node() {
            this.data = 0;
            this.next = null;
        }

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
