package assignments;
// 1472. Design Browser History
// https://leetcode.com/problems/design-browser-history/description/

import java.util.Stack;

public class DesignBrowserHistory {
    class Node {
        String data;
        Node next;
        Node prev;

        Node() {

        }

        Node(String data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        Node(String data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    class BrowserHistory {
        Node head;

        public BrowserHistory(String homepage) {
            head = new Node(homepage);
        }

        public void visit(String url) {
            Node newNode = new Node(url, null, head);
            head.next = newNode;
            head = head.next;
        }

        public String back(int steps) {
            while (steps > 0 && head.prev != null) {
                head = head.prev;
                steps--;
            }
            return head.data;
        }

        public String forward(int steps) {
            while (steps > 0 && head.next != null) {
                head = head.next;
                steps--;
            }
            return head.data;
        }
    }


    // stack solution
    class BrowserHistory1 {
        Stack<String> backHistory;
        Stack<String> forwardHistory;

        public BrowserHistory1(String homepage) {
            backHistory = new Stack<>();
            forwardHistory = new Stack<>();
            backHistory.push(homepage);
        }

        public void visit(String url) {
            backHistory.push(url);
            // clear forward history
            while (!forwardHistory.isEmpty()) {
                forwardHistory.pop();
            }
        }

        public String back(int steps) {
            while (backHistory.size() > 1 && steps > 0) {
                forwardHistory.push(backHistory.pop());
                steps--;
            }
            if (backHistory.isEmpty()) return null;
            return backHistory.peek();
        }

        public String forward(int steps) {
            while (!forwardHistory.isEmpty() && steps > 0) {
                backHistory.push(forwardHistory.pop());
                steps--;
            }
            return backHistory.peek();
        }
    }
}