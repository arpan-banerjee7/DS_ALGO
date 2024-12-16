package google.tree;

import java.util.*;
// https://leetcode.com/discuss/interview-question/4580178/Google-Phone-Screen/
// https://leetcode.com/discuss/interview-question/1682632/google-phone-screen-number-of-islands-in-a-tree
/*
Given a Binary Tree with Zeros and Ones, find number of islands. Island is defined as connected nodes with only ones and parent 0
 */

public class IslandCounter {
    static class TreeNode {
        int value; // Node value (0 or 1)
        List<TreeNode> children; // List of child nodes

        TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }

    public static int countNumberOfIslands(TreeNode node, int parentValue) {
        if (node == null) {
            return 0;
        }

        // Increment count if current node is 1 and parentValue is 0
        int count = (node.value == 1 && parentValue == 0) ? 1 : 0;

        // Recursively process all child nodes
        for (TreeNode child : node.children) {
            count += countNumberOfIslands(child, node.value);
        }

        return count;
    }

    public static void main(String[] args) {
        // Example Tree
        //        0
        //      / | \
        //     1  0  1
        //    /|     |
        //   1 1     1
        //         / | \
        //        1  1  0

        TreeNode root = new TreeNode(0);
        TreeNode child1 = new TreeNode(1);
        TreeNode child2 = new TreeNode(0);
        TreeNode child3 = new TreeNode(1);
        TreeNode child1_1 = new TreeNode(1);
        TreeNode child1_2 = new TreeNode(1);
        TreeNode child3_1 = new TreeNode(1);
        TreeNode child3_2 = new TreeNode(1);
        TreeNode child3_3 = new TreeNode(0);

        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);

        child1.children.add(child1_1);
        child1.children.add(child1_2);

        child3.children.add(child3_1);
        child3.children.add(child3_2);
        child3.children.add(child3_3);

        int result = countNumberOfIslands(root, 0); // Pass 0 as the parent value for the root
        System.out.println("Number of islands: " + result); // Output: 2

        TreeNode root2 = new TreeNode(0);
        TreeNode child2_1 = new TreeNode(0);
        TreeNode child2_2 = new TreeNode(1);
        TreeNode child2_3 = new TreeNode(1);
        TreeNode child2_1_1 = new TreeNode(1);
        TreeNode child2_2_1 = new TreeNode(0);
        TreeNode child2_2_1_1 = new TreeNode(1);
        TreeNode child2_3_1 = new TreeNode(1);

        root2.children.add(child2_1);
        root2.children.add(child2_2);
        root2.children.add(child2_3);

        child2_1.children.add(child2_1_1);
        child2_2.children.add(child2_2_1);
        child2_2_1.children.add(child2_2_1_1);
        child2_3.children.add(child2_3_1);

        System.out.println("Number of islands (Example 2): " + countNumberOfIslands(root2, 0));// output =4
    }
}