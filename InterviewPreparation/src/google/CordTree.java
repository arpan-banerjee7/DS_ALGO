package google;


public class CordTree {
    private Node root;

    public CordTree(Node root) {
        this.root = root;
    }

    static class Node {
        private int length;

        public Node(int length) {
            this.length = length;
        }
    }

    static class LeafNode extends Node {
        private String value;

        public LeafNode(int length, String value) {
            super(length);
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    static class InternalNode extends Node {
        private Node leftChild;
        private Node rightChild;

        public InternalNode(int length, Node leftChild, Node rightChild) {
            super(length);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    public Character findCordAtIndex(int index) {
        return findCordAtIndex(index, root);
    }

    public Character findCordAtIndex(int index, Node root) {
        if (index <= root.length) {
            if (root instanceof LeafNode) {
                // if a leaf node, base case
                String s = ((LeafNode) root).value;
                return s.charAt(index - 1);
            } else {
                // if an internal node
                // check left right
                InternalNode internalNode = (InternalNode) root;
                Node left = internalNode.leftChild;
                Node right = internalNode.rightChild;
                if (index <= left.length) {
                    // go to left side
                    return findCordAtIndex(index, left);
                } else {
                    // got to right side
                    return findCordAtIndex(index - left.length, right);
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        // Build tree

        Node root = new InternalNode(26, new LeafNode(5, "ABCDE"), new InternalNode(21,
                new LeafNode(10, "FGHIJKLMNO"), new LeafNode(11, "PQRSTUVWXYZ")));

        CordTree cordTree = new CordTree(root);
        System.out.println(cordTree.findCordAtIndex(10)); // J
        System.out.println(cordTree.findCordAtIndex(3)); // C
        System.out.println(cordTree.findCordAtIndex(16)); // P

    }

}
//     InternalNode, 26
//      /              \
//     /                \
//    /                  \
// Leaf(5, ABCDE)      InternalNode, 21
//                       /           \
//                     /             \
//                    /               \
//                   /                 \
//         Leaf(10, FGHIJKLMNO)     Leaf(11, PQRSTUVWXYZ)




