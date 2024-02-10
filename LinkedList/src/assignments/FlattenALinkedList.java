package assignments;
// Flattening a Linked List
// https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article

import java.util.PriorityQueue;

public class FlattenALinkedList {
    Node flatten(Node root) {
        // Your code here
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.data - b.data);
        // build heap o(k)
        while (root != null) {
            pq.add(root);
            root = root.next;
        }

        Node dummy = new Node(-1);
        Node temp = dummy;
        while (!pq.isEmpty()) {
            Node head = pq.poll();
            temp.bottom = head;
            temp = temp.bottom;
            if (head.bottom != null) {
                pq.add(head.bottom);
                head.next = null;
            }
        }
        return dummy.bottom;

    }

    // Recursive solution
    private Node merge(Node A, Node B) {
        if (A == null) return B;
        if (B == null) return A;

        Node res = null;
        if (A.data < B.data) {
            res = A;
            res.bottom = merge(A.bottom, B);
        } else {
            res = B;
            res.bottom = merge(A, B.bottom);
        }
        res.next = null;
        return res;
    }

    Node flattenRecursive(Node root) {
        // Your code here
        if (root == null || root.next == null) {
            return root;
        }
        root.next = flattenRecursive(root.next);
        root = merge(root, root.next);
        return root;
    }

    class Node {
        int data;
        Node next;
        Node bottom;

        Node() {
        };

        Node(int x) {
            data = x;
            next = null;
            bottom = null;
        }
    }
}
