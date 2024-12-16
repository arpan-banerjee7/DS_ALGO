package google.tree;

import java.util.*;

public class RemoveLeavesFromNaryTree {
    static class Node {
        int val;
        List<Node> children;

        public Node(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }

    static List<Integer> res = new ArrayList<>();

    private static void helper(Node root) {
        if (root == null) {
            return;
        }

        if (root.children.isEmpty()) {
            res.add(root.val);// add the leaf
            return;
        }

        for (Node child : root.children) {
            helper(child);
        }
        root.children = null;
        res.add(root.val);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.children.add(new Node(2));
        root.children.add(new Node(5));
        root.children.add(new Node(3));

        root.children.get(0).children.add(new Node(7));
        root.children.get(0).children.add(new Node(4));

        root.children.get(1).children.add(new Node(9));

        root.children.get(0).children.get(1).children.add(new Node(8));


        helper(root);
        for (int i : res) {
            System.out.print(i + " ");
        }
        Math.random();
    }


}
