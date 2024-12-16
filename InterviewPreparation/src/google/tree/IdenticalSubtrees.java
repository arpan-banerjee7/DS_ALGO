


package google.tree;

import java.util.*;

public class IdenticalSubtrees {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postOrderTraversal(root, new HashMap<>(), res);
        return res;
    }

    public String postOrderTraversal(TreeNode node, Map<String, Integer> map, List<TreeNode> res) {
        if (node == null) {
            return "#"; // Represents a null node
        }

        // Traverse left and right subtrees
        String left = postOrderTraversal(node.left, map, res);
        String right = postOrderTraversal(node.right, map, res);

        // Serialize the current subtree
        String serialized = node.val + "," + left + "," + right;

        // Check and count identical subtrees
        map.put(serialized, map.getOrDefault(serialized, 0) + 1);
        if (map.get(serialized) == 2) {
            res.add(node);
        }

        return serialized;
    }
}
