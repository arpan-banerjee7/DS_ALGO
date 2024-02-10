package assignments;
/*
Input:
LinkedList: 4->5->6
Output: 457
Explanation: 4->5->6 represents 456 and when 1 is added it becomes 457.
 */

// Add 1 to a number represented as linked list
// https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
public class Add1ToLinkedList {

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

    class Solution {
        private static Node reverse(Node head) {
            Node curr = head;
            Node next = null;
            Node prev = null;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

        public Node addOne(Node head) {
            //code here.
            Node reversedHead = reverse(head);
            Node curr = reversedHead;
            Node dummy = new Node(0);
            Node temp = dummy;
            int sum = 1;
            int carry = 0;
            while (curr != null || carry != 0) {
                if (curr != null) {
                    sum += curr.data;
                    curr = curr.next;
                }
                sum += carry;
                carry = sum / 10;
                Node newNode = new Node(sum % 10);
                temp.next = newNode;
                temp = temp.next;
                sum = 0;
            }
            return reverse(dummy.next);
        }
    }

    private static int recurseAndAdd(Node head) {
        if (head == null) return 1; //start adding from the last node
        int carry = recurseAndAdd(head.next);
        int sum = carry + head.data;
        carry = sum / 10;
        head.data = sum % 10;
        return carry;
    }

    public Node addOneRecursive(Node head) {
        //code here.
        Node curr = head;
        int carry = recurseAndAdd(curr);
        if (carry == 1) {
            Node newNode = new Node(1);
            newNode.next = head;
            return newNode;
        }
        return head;
    }

}
