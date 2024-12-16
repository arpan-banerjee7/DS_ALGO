package google.tree;

import java.util.*;


public class RemoveLeavesFromBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static List<Integer> res = new ArrayList<>();

    private static TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            res.add(root.val);// add the leaf
            return null;
        }

        helper(root.left);
        helper(root.right);

        // required for modifying the tree
        root.left = null;
        root.right = null;
        res.add(root.val);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        helper(root);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

}


