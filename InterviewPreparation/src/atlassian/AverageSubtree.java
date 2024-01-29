package atlassian;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    public int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v) {
        val = v;
        this.left = null;
        this.right = null;
    }
}

public class AverageSubtree {
    double max = Integer.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return max;
    }

    // double[]: sum, count
    private double[] dfs(TreeNode root) {
        if (root == null) {
            return new double[]{0, 0};
        }

        double[] l = dfs(root.left);
        double[] r = dfs(root.right);
        double sum = root.val + l[0] + r[0];
        double count = l[1] + r[1] + 1;

        double avg = sum / count;
        if (avg > max) {
            max = avg;
        }

        return new double[]{sum, count};
    }

    public static void main(String[] args) {

        // Given tree
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(12);
        root.right = new TreeNode(18);

        root.left.left = new TreeNode(11);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(8);
        AverageSubtree avgSubtree = new AverageSubtree();
        // Function call
        avgSubtree.maximumAverageSubtree(root);

        // Print answer
        System.out.println(avgSubtree.max);

        StringBuilder sb = new StringBuilder("");
        sb.append("Abc");
        System.out.println(sb.toString());
    }
}
// https://leetcode.com/problems/shortest-distance-to-a-character/