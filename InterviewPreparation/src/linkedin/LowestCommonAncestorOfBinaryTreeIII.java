package linkedin;

import java.util.*;

public class LowestCommonAncestorOfBinaryTreeIII {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<Node>();
        Node temp = p;
        while (temp != null) {
            set.add(temp);
            temp = temp.parent;
        }
        temp = q;
        while (temp != null) {
            if (set.contains(temp))
                break;
            else
                temp = temp.parent;
        }
        return temp;
    }
}
