package google.intervalTree;

import java.util.HashSet;
import java.util.Set;

public class IntervalTree {
    static class IntervalTreeNode<T> {
        int max;
        int start;
        int end;
        int height;
        IntervalTreeNode<T> left;
        IntervalTreeNode<T> right;
        T data;

        public IntervalTreeNode(T data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
            this.max = end;
        }
    }


    IntervalTreeNode<Set<String>> root;


    public void insert(String user, int start, int end) {
        root = insert(root, user, start, end);
    }

    private IntervalTreeNode<Set<String>> insert(IntervalTreeNode<Set<String>> root, String user, int start, int end) {

        if (root == null) {
            Set<String> users = new HashSet<>();
            users.add(user);
            return new IntervalTreeNode<>(users, start, end);
        }

        if (root.start >= start) {
            root.left = insert(root.left, user, start, end);
        } else {
            root.right = insert(root.right, user, start, end);
        }

        if (root.max < end) {
            root.max = end;
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return balance(root, start, end);
    }

    private IntervalTreeNode<Set<String>> balance(IntervalTreeNode<Set<String>> node, int start, int end) {
        int balance = getBalance(node);

        //left left scenario
        if (balance > 1 && node.left.start > start) {
            return rightRotate(node);
        }

        // left right scenario
        if (balance > 1 && node.left.start <= start) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //right right scenario
        if (balance < -1 && node.right.start < start) {
            return leftRotate(node);
        }

        //right left scenario
        if (balance < -1 && node.right.start > start) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;

    }

    private IntervalTreeNode<Set<String>> leftRotate(IntervalTreeNode<Set<String>> parent) {

        System.out.println("Rotation happened on left");
        IntervalTreeNode<Set<String>> child = parent.right;
        IntervalTreeNode<Set<String>> grandChild = child.left;

        //rotations
        child.left = parent;
        parent.right = grandChild;

        child.height = 1 + Math.max(getHeight(child.left), getHeight(child.right));
        parent.height = 1 + Math.max(getHeight(parent.left), getHeight(parent.right));

        parent.max = Math.max(parent.end, Math.max(getMax(parent.left), getMax(parent.right)));
        child.max = Math.max(child.end, Math.max(getMax(child.left), getMax(child.right)));
        return child;
    }

    private IntervalTreeNode<Set<String>> rightRotate(IntervalTreeNode<Set<String>> parent) {
        System.out.println("Rotation happened on left");
        IntervalTreeNode<Set<String>> child = parent.left;
        IntervalTreeNode<Set<String>> grandChild = child.right;

        //rotations
        child.right = parent;
        parent.left = grandChild;

        child.height = 1 + Math.max(getHeight(child.left), getHeight(child.right));
        parent.height = 1 + Math.max(getHeight(parent.left), getHeight(parent.right));

        parent.max = Math.max(parent.end, Math.max(getMax(parent.left), getMax(parent.right)));
        child.max = Math.max(child.end, Math.max(getMax(child.left), getMax(child.right)));
        return child;
    }

    private int getMax(IntervalTreeNode<Set<String>> node) {
        return node == null ? Integer.MIN_VALUE : node.max;
    }


    private int getBalance(IntervalTreeNode<Set<String>> node) {
        return node != null ? (getHeight(node.left) - getHeight(node.right)) : 0;
    }

    private int getHeight(IntervalTreeNode<Set<String>> node) {
        return node != null ? node.height : 0;
    }


    public Set<String> query(int point) {
        return query(root, point, new HashSet<>());
    }

    private Set<String> query(IntervalTreeNode<Set<String>> root, int point, Set<String> users) {

        if (root == null) {
            return users;
        }


        if (root.start <= point && root.end >= point) {
            users.addAll(root.data);
        }
        if (root.left != null && root.left.max >= point) {
            query(root.left, point, users);
        }
        if (root.right != null && root.start <= point) {
            query(root.right, point, users);

        }
        return users;
    }

    public int countOverlaps(int point) {
        return countOverlaps(root, point);
    }

    private int countOverlaps(IntervalTreeNode<Set<String>> root, int point) {

        int count = 0;
        if (root == null) {
            return count;
        }

        if (root.start <= point && root.end >= point) {
            count++;
        }
        if (root.left != null && root.left.max >= point) {
            count += countOverlaps(root.left, point);
        }
        if (root.right != null && root.start < point) {
            count += countOverlaps(root.right, point);
        }
        return count;
    }

    // Display the tree structure
    public void displayTree() {
        System.out.println("Interval Tree Structure:");
        displayTree(root, "", true);
    }

    private void displayTree(IntervalTreeNode<Set<String>> node, String indent, boolean isLast) {
        if (node != null) {
            System.out.print(indent);
            if (isLast) {
                System.out.print("└─");
                indent += "  ";
            } else {
                System.out.print("├─");
                indent += "| ";
            }
            System.out.println("[" + node.start + "," + node.end + "] (max=" + node.max + ", h=" + node.height + ", data=" + node.data + ")");
            displayTree(node.left, indent, false);
            displayTree(node.right, indent, true);
        }
    }

    public static void main(String[] args) {
        IntervalTree intervalTree = new IntervalTree();

        intervalTree.insert("USER-A", 2, 6);
        intervalTree.insert("USER-B", 3, 9);
        intervalTree.insert("USER-C", 4, 7);
        intervalTree.insert("USER-D", 5, 8);
        intervalTree.insert("USER-E", 0, 5);

        System.out.println(intervalTree.query(6));
    }

}