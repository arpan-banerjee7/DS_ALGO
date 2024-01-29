package atlassian;

import java.util.ArrayList;
import java.util.List;

// https://algo.monster/liteproblems/366
// https://www.youtube.com/watch?v=FRUoRB-clt8
// idea is, we need to print leaves of trees at each level, we wll leverage height of the tree here, since heigh is the only common factor in leaves.
// outer leaves wll have height 0, then second last level leaves wll have height 1 and so on. we wll have a dynamic list and keep inserting the leaves at those
// indices
public class PrintLeavesOfBinaryTree {

    private int printLeafNodes(Node root, List<List<Integer>> res) {
        if (root == null) {
            return 0;
        }

        int left = printLeafNodes(root.left, res);
        int right = printLeafNodes(root.right, res);
        int height = Math.max(left, right);
        if (height >= res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(height).add(root.data);
        return 1 + height;
    }


    // Utility function to create a new tree node
    Node newNode(int data) {
        Node temp = new Node(data);
        temp.left = temp.right = null;
        return temp;
    }

    public static void main(String[] args) {
        PrintLeavesOfBinaryTree tree = new PrintLeavesOfBinaryTree();
        // Create binary tree
        Node root = tree.newNode(1);
        root.left = tree.newNode(2);
        root.right = tree.newNode(3);
        root.left.left = tree.newNode(4);
        root.left.right = tree.newNode(5);
        List<List<Integer>> res = new ArrayList<>();
        // Print leaf nodes of the given tree
        tree.printLeafNodes(root, res);
        System.out.println(res);
    }


}
