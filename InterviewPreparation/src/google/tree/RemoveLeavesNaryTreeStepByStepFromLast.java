package google.tree;

import java.util.*;
// https://leetcode.com/discuss/interview-question/1693416/google-onsite-recursively-delete-leave-nodes-in-a-multi-tree
// https://leetcode.com/discuss/interview-question/1739888/google-phone-screen-sde
public class RemoveLeavesNaryTreeStepByStepFromLast {

    static class TreeNode {
        int val;
        List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    private static Map<TreeNode, Integer> outdegree;
    private static Map<TreeNode, TreeNode> parentMap;

    public static List<Integer> findLeaves(TreeNode root) {
        outdegree = new HashMap<>();
        parentMap = new HashMap<>();

        // Traverse the tree and initialize outdegree and parent maps
        traverse(root);

        // Queue for processing nodes
        Queue<TreeNode> queue = new LinkedList<>();
        for (TreeNode node : outdegree.keySet()) {
            if (outdegree.get(node) == 0) {
                queue.offer(node);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            result.add(curr.val);

            if (curr == root) break;

            TreeNode parent = parentMap.get(curr);
            outdegree.put(parent, outdegree.get(parent) - 1);

            // Remove the current node from its parent's children
            parent.children.remove(curr);

            if (outdegree.get(parent) == 0) {
                queue.offer(parent);
            }
        }

        return result;
    }

    private static void traverse(TreeNode node) {
        if (node == null) return;

        int childCount = 0;
        for (TreeNode child : node.children) {
            parentMap.put(child, node);
            traverse(child);
            childCount++;
        }
        outdegree.put(node, childCount);
    }

    public static void main(String[] args) {
        // Construct the N-ary tree
        TreeNode root = new TreeNode(1);

        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);

        root.children.add(node2);
        root.children.add(node5);
        root.children.add(node3);

        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        node2.children.add(node7);
        node2.children.add(node4);

        TreeNode node9 = new TreeNode(9);
        node5.children.add(node9);

        TreeNode node8 = new TreeNode(8);
        node4.children.add(node8);

        // Run the function and print the result
        List<Integer> leavesOrder = findLeaves(root);
        System.out.println("Removal Order: " + leavesOrder);// [7, 8, 9, 3, 4, 5, 2, 1]
    }
}
/*
Is there a O(n) solution which does not bottom-up depth on this question? I got asked this question in a google on-stie interview, but I don't think it is possible to solve this question by not using the bottom-up depth and dfs.


Recursively delete leave nodes in a multi-tree. Require O(n) solution


Example input:


      1
   /   |  \
  2    5   3
 / \   |
7   4  9
    |
    8
should return: [7, 8, 9, 3, 4, 5, 2, 1]
 */

/*
For a rooted tree with any arbitary number of children for each node, not necessarily n-ary tree.
Remove all the leaf nodes, and store them in a list, this would create new leaf nodes. Repeat untill all the nodes are removed
Conditions : Freshly created leaf nodes(node whose children are removed) should not be removed just after its children are removed, unless there's no other option for us, then we can remove it


For Example


                             1
					2        3       4
				5  6  8     7
			Following is a tree rooted at 1, with 2,3,4 as its children. 2 has children 5,6,8 and 3 has children 7

		One Possible output for the given input would be,
		5 6 8 7 4 2 3 1
	Explanation : Remove 5 & 6 since they are leaf nodes, Now 2 is a freshly created leaf
	Since we can process other nodes, we will not remove it
	Next Leaf removed is 7, After this newly created 3 cannot be removed
	Hence Remove 4, after that since it is a leaf
	Now Remove 2, after which remove 3
    1 is now a freshly created leaf, but since there is no other options remaining, remove 1
 */