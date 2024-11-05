package tree.narytree;

import java.util.*;

class NaryTreeNode {
    int val;
    List<NaryTreeNode> children;

    public NaryTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

}

public class IsSameNaryTree {
    public boolean isSameTree(NaryTreeNode p, NaryTreeNode q) {
        // If both nodes are null, they are identical
        if (p == null && q == null) {
            return true;
        }

        // If one is null and the other isn't, they are not identical
        if (p == null || q == null) {
            return false;
        }

        // If the values of the nodes are different, they are not identical
        if (p.val != q.val) {
            return false;
        }

        // If the number of children is different, they are not identical
        if (p.children.size() != q.children.size()) {
            return false;
        }

        // Recursively check each pair of corresponding children
        for (int i = 0; i < p.children.size(); i++) {
            NaryTreeNode childP = p.children.get(i);
            NaryTreeNode childQ = q.children.get(i);

            if (!isSameTree(childP, childQ)) {
                return false;
            }
        }

        // All checks passed; the trees are identical
        return true;
    }

    public static void main(String[] args) {
        // Construct first n-ary tree
        NaryTreeNode root1 = new NaryTreeNode(1);
        root1.children.add(new NaryTreeNode(2));
        root1.children.add(new NaryTreeNode(3));
        root1.children.add(new NaryTreeNode(4));
        root1.children.get(0).children.add(new NaryTreeNode(5));
        root1.children.get(0).children.add(new NaryTreeNode(6));
        root1.children.get(0).children.add(new NaryTreeNode(7));
        root1.children.get(2).children.add(new NaryTreeNode(8));

        // Construct second n-ary tree
        NaryTreeNode root2 = new NaryTreeNode(1);
        root2.children.add(new NaryTreeNode(2));
        root2.children.add(new NaryTreeNode(3));
        root2.children.add(new NaryTreeNode(4));
        root2.children.get(0).children.add(new NaryTreeNode(5));
        root2.children.get(0).children.add(new NaryTreeNode(6));
        root2.children.get(0).children.add(new NaryTreeNode(7));
        root2.children.get(2).children.add(new NaryTreeNode(8));


        IsSameNaryTree solution = new IsSameNaryTree();
        boolean result = solution.isSameTree(root1, root2);

        System.out.println(result);

    }


}
