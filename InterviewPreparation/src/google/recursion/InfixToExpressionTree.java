package google.recursion;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
// convert a given infix operation to binary expression tree

public class InfixToExpressionTree {
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        String val;

        public TreeNode(String val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode convertInfixToExpression(String s) {
        s = s.replace(" ", ""); // normalize: ignore whitespace
        if (s.isEmpty()) return null;

        // strip a matched outer pair of parens: "(....)" -> recurse on inside
        if (s.charAt(0) == '(' && matchingParen(s, 0) == s.length() - 1) {
            return convertInfixToExpression(s.substring(1, s.length() - 1));
        }

        // scan left-to-right, track depth, record rightmost depth-0 operator per tier
        int subIdx = -1, addIdx = -1, divIdx = -1, multIdx = -1;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            else if (balance == 0) {
                if      (c == '+') addIdx  = i;
                else if (c == '-') subIdx  = i;
                else if (c == '/') divIdx  = i;
                else if (c == '*') multIdx = i;
            }
        }

        // pick root: lowest-precedence operator wins (rightmost of +/- beats rightmost of */)
        int rootIdx;
        if      (addIdx  != -1) rootIdx = addIdx;
        else if (subIdx  != -1) rootIdx = subIdx;
        else if (divIdx  != -1) rootIdx = divIdx;
        else if (multIdx != -1) rootIdx = multIdx;
        else return new TreeNode(s); // pure operand (no depth-0 operator found)

        TreeNode parent = new TreeNode(String.valueOf(s.charAt(rootIdx)));
        parent.left  = convertInfixToExpression(s.substring(0, rootIdx));
        parent.right = convertInfixToExpression(s.substring(rootIdx + 1));
        return parent;
    }

    // returns the index of the ')' that matches the '(' at position open
    private int matchingParen(String s, int open) {
        int balance = 0;
        for (int i = open; i < s.length(); i++) {
            if (s.charAt(i) == '(') balance++;
            else if (s.charAt(i) == ')') { balance--; if (balance == 0) return i; }
        }
        return -1;
    }

    // BFS level-order print: [+,-,1,2,/,null,null,...] — matches LeetCode tree format
    private static String printTree(TreeNode root) {
        if (root == null) return "[]";
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
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
        InfixToExpressionTree solver = new InfixToExpressionTree();

        String[][] cases = {
                // {input, expected level-order}
                {"2-3/(5*2)+1",   "[+, -, 1, 2, /, null, null, null, null, 3, *, null, null, 5, 2]"},
                {"1+2",           "[+, 1, 2]"},
                {"3*4",           "[*, 3, 4]"},
                {"1+2*3",         "[+, 1, *, null, null, 2, 3]"},
                {"(1+2)*3",       "[*, +, 3, 1, 2]"},
                {"(2*3) + (4+5) + (2/3)",   "[+, +, /, *, +, 2, 3, 2, 3, 4, 5]"}

        };

        int pass = 0;
        for (String[] tc : cases) {
            String input = tc[0], expected = tc[1];
            TreeNode res = solver.convertInfixToExpression(input);
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

