package intuit;

public class ReverseDLLInKGroups {
    static class Node {
        int data;
        Node next;
        Node back;

        public Node(int data, Node next, Node back) {
            this.data = data;
            this.next = next;
            this.back = back;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.back = null;
        }
    }

    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private static Node convertArrayToDLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node prev = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i], null, prev);
            prev.next = temp;
            prev = temp;
        }
        return head;
    }

    private static boolean canReverse(Node head, int k) {
        int count = 0;
        while (head != null && count != k) {
            head = head.next;
            count++;
        }
        return count == k;
    }

    private static Node reverse(Node head, int k) {
        if (!canReverse(head, k)) {
            return head;
        }

        Node curr = head;
        Node prev = null;
        int count = 0;
        while (curr != null && count < k) {
            curr.back = curr.next;
            curr.next = prev;
            prev = curr;
            curr = curr.back;
            count++;
        }
        if (curr != null) {
            prev.back = null;
            head.next = reverse(curr, k);
            head.next.back = head;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        Node head = convertArrayToDLL(arr);
        System.out.println("BEFORE: reversing in " + k + " groups");
        printList(head);
        Node reversedHead = reverse(head, k);
        System.out.println("AFTER:: reversing in " + k + " groups");
        printList(reversedHead);

    }
}
