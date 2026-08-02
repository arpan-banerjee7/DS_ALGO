package google.recursion;

public class ConstructBinaryTreeFromString {

    // ── TreeNode ──────────────────────────────────────────────────────────────
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ── YOUR SOLUTION GOES HERE ───────────────────────────────────────────────
    // LC 536 — Construct Binary Tree from String
    // Input : "4(2(3)(1))(6(5))"
    // Output: root of the binary tree
    //
    // Grammar:
    //   node  ::= number | number '(' node ')' | number '(' node ')(' node ')'
    //
    // PRE-CODE CHECKLIST:
    // 1. WHO advances idx?   ->
    // 2. CONTRACT            ->
    // 3. BRANCHES            ->
    // 4. STOP                ->

    private int idx;

    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;
        idx = 0;
        return build(s);
    }

    private TreeNode build(String s) {
        if (idx >= s.length()) {
            return null;
        }
        char ch = s.charAt(idx);
        int number = 0;
        if (Character.isDigit(ch) || ch == '-') {
            int sign = 1;
            if (ch == '-') {
                sign = -sign;
                idx++;
            }
            while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
                number = number * 10 + s.charAt(idx) - '0';
                idx++;
            }
            // idx--;
            number = sign * number;
        }
        TreeNode node = new TreeNode(number);
        // idx++;
        if (idx < s.length() && s.charAt(idx) == '(') {
            idx++;
            node.left = build(s);
            if (idx < s.length() && s.charAt(idx) == '(') {
                idx++;
                node.right = build(s);
            }
        }
        if (idx < s.length() && s.charAt(idx) == ')') {
            idx++;
            return node;
        }

        return node;
    }

    // ── helpers ───────────────────────────────────────────────────────────────
    private static String inorder(TreeNode node) {
        if (node == null) return "n";
        return "[" + inorder(node.left) + " " + node.val + " " + inorder(node.right) + "]";
    }

    private static void printTree(TreeNode root) {
        System.out.println("Inorder: " + inorder(root));
    }

    // ── main ──────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        ConstructBinaryTreeFromString sol = new ConstructBinaryTreeFromString();

        //        4
        //      /   \
        //     2     6
        //    / \   /
        //   3   1 5
        String input = "4(2(3)(1))(6(5))";
        TreeNode root = sol.str2tree(input);
        printTree(root);
        // Expected inorder: [[[n 3 n] 2 [n 1 n]] 4 [[n 5 n] 6 n]]

        // Edge: single node
        sol = new ConstructBinaryTreeFromString();
        TreeNode single = sol.str2tree("42");
        printTree(single);
        // Expected inorder: [n 42 n]

        // Edge: left child only
        sol = new ConstructBinaryTreeFromString();
        TreeNode leftOnly = sol.str2tree("1(2)");
        printTree(leftOnly);
        // Expected inorder: [[n 2 n] 1 n]

        // Edge: negative number
        sol = new ConstructBinaryTreeFromString();
        TreeNode neg = sol.str2tree("-4(2)(-3)");
        printTree(neg);
        // Expected inorder: [[n 2 n] -4 [n -3 n]]
    }
}
