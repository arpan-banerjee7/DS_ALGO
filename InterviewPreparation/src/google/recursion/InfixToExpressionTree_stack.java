package google.recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// convert a given infix operation to binary expression tree

public class InfixToExpressionTreeStack {

    static class Node {
        char val;
        Node left, right;

        public Node(char val) {
            this.val = val;
        }
    }

    Map<Character, Integer> prio = new HashMap<>();

    public Node expTree(String s) {

        prio.put('(', 1);
        prio.put('+', 2);
        prio.put('-', 2);
        prio.put('*', 3);
        prio.put('/', 3);

        Deque<Character> ops = new ArrayDeque<>();
        Deque<Node> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                ops.push(ch);
            } else if (Character.isDigit(ch)) {
                stack.push(new Node(ch));
            } else if (ch == ')') {
                while (ops.peek() != '(') {
                    combine(ops, stack);
                }
                // pop left '('
                ops.pop();
            } else {
                // @note: must be >=, for test case "1+2+3+4+5"
                while (!ops.isEmpty() && prio.get(ops.peek()) >= prio.get(ch)) {
                    combine(ops, stack);
                }

                ops.push(ch);
            }
        }

        while (stack.size() > 1) {
            combine(ops, stack);
        }

        return stack.peek();
    }


    private void combine(Deque<Character> ops, Deque<Node> stack) {
        Node root = new Node(ops.pop());
        // right first, then left
        root.right = stack.pop();
        root.left = stack.pop();

        stack.push(root);
    }

    private static String printTree(Node root) {
        if (root == null) return "[]";
        List<String> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // trim trailing nulls (LeetCode omits them)
        int last = result.size() - 1;
        while (last >= 0 && result.get(last).equals("null")) last--;
        return result.subList(0, last + 1).toString();
    }

    public static void main(String[] args) {
        InfixToExpressionTreeStack solver = new InfixToExpressionTreeStack();

        String[][] cases = {

                {"(2*3)+(4+5)+(2/3)",   "[+, +, /, *, +, 2, 3, 2, 3, 4, 5]"},
                {"(2*3)+(4+5)/(2/3)",   "[+, *, /, 2, 3, +, /, null, null, null, null, 4, 5, 2, 3]"}

        };


        int pass = 0;
        for (String[] tc : cases) {
            String input = tc[0], expected = tc[1];
            Node res = solver.expTree(input);
            String got = printTree(res);
            boolean ok = got.equals(expected);
            if (ok) pass++;
            System.out.println("Input:    " + input);
            System.out.println("Got:      " + got);
            System.out.println("Expected: " + expected);
            System.out.println(ok ? "PASS" : "FAIL");
            System.out.println();
        }
        System.out.println("==== " + pass + "/" + cases.length + " passed ====");
    }
}

