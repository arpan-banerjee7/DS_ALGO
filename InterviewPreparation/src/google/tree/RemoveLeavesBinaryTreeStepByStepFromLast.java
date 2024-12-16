package google.tree;

import java.util.*;
// 366. Find Leaves of Binary Tree
// https://leetcode.com/problems/find-leaves-of-binary-tree/description/?orderBy=most_votes

public class RemoveLeavesBinaryTreeStepByStepFromLast {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private Map<TreeNode, Integer> indegree;
    private Map<TreeNode, TreeNode> graph;

    public List<List<Integer>> findLeaves(TreeNode root) {
        indegree = new HashMap<>();
        graph = new HashMap<>();
        traverse(root);

        Queue<TreeNode> queue = new LinkedList<>();
        for (TreeNode node : indegree.keySet()) {
            if (indegree.get(node) == 0)
                queue.offer(node);
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                if (curr == root) break;

                TreeNode parent = graph.get(curr);
                // Delete connection from parent
                if (parent.left == curr)
                    parent.left = null;
                if (parent.right == curr)
                    parent.right = null;
                indegree.put(parent, indegree.get(parent) - 1);
                if (indegree.get(parent) == 0)
                    queue.offer(parent);
            }
            res.add(level);
        }
        return res;
    }

    private void traverse(TreeNode root) {
        int children = 0;
        if (root.left != null) {
            children++;
            graph.put(root.left, root);
            traverse(root.left);
        }
        if (root.right != null) {
            children++;
            graph.put(root.right, root);
            traverse(root.right);
        }
        indegree.put(root, children);
    }
}

// another interesting solution with inserstion based on heigh, like left view and right view of binary tree
/*
class Solution {

    private List<List<Integer>> solution;

    private int getHeight(TreeNode root) {

        // return -1 for null nodes
        if (root == null) {
            return -1;
        }

        // first calculate the height of the left and right children
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int currHeight = Math.max(leftHeight, rightHeight) + 1;

        if (this.solution.size() == currHeight) {
            this.solution.add(new ArrayList<>());
        }

        this.solution.get(currHeight).add(root.val);

        return currHeight;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        this.solution = new ArrayList<>();

        getHeight(root);

        return this.solution;
    }
}
 */