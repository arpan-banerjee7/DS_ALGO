package google.recursion;

public class SerializeDeserializeBinaryTree {

    // ── TreeNode ─────────────────────────────────────────────────────────────
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ── Shared-idx solution ───────────────────────────────────────────────────
    // Encode: preorder DFS — root val, then left subtree, then right subtree.
    // "n" marks a null node.
    // Example: [1, 2, 3, null, null, 4, 5]
    //   serialize → "1 2 n n 3 4 n n 5 n n "
    //   deserialize reads left-to-right, each call consuming exactly its slice.

    private int idx;   // shared cursor — child advances it, parent never touches it

    // ── serialize ────────────────────────────────────────────────────────────
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("n ");
            return;
        }
        sb.append(node.val).append(" ");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // ── deserialize (shared-idx) ─────────────────────────────────────────────
    // CONTRACT: build() reads exactly one full subtree starting at tokens[idx]
    //           and leaves idx exactly one past the last token it owned.
    //
    // BRANCHES:
    //   "n"  → null leaf; consume one token (idx++), return null.
    //   digit → create node; recurse for left (left call advances idx),
    //           then recurse for right (right call advances idx further).
    //
    // STOP: no explicit stop token — each null node is self-delimiting via "n".
    //
    // WHO advances idx? Always the child (idx++ is inside build, not the caller).

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] tokens = data.trim().split(" ");
        idx = 0;
        return build(tokens);
    }

    private TreeNode build(String[] tokens) {
        String tok = tokens[idx++];           // consume THIS node's token

        if (tok.equals("n")) return null;     // null leaf — done, idx already past it

        TreeNode node = new TreeNode(Integer.parseInt(tok));
        node.left  = build(tokens);           // left subtree consumes its own slice
        node.right = build(tokens);           // right subtree consumes its own slice
        return node;
    }

    // ── helpers for printing ─────────────────────────────────────────────────
    private static String inorder(TreeNode node) {
        if (node == null) return "n";
        return "[" + inorder(node.left) + " " + node.val + " " + inorder(node.right) + "]";
    }

    // ── main ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SerializeDeserializeBinaryTree codec = new SerializeDeserializeBinaryTree();

        //        1
        //       / \
        //      2   3
        //         / \
        //        4   5
        TreeNode root = new TreeNode(1);
        root.left        = new TreeNode(2);
        root.right       = new TreeNode(3);
        root.right.left  = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = codec.serialize(root);
        System.out.println("Serialized : " + serialized);
        // Expected  : 1 2 n n 3 4 n n 5 n n

        TreeNode recovered = codec.deserialize(serialized);
        System.out.println("Inorder original  : " + inorder(root));
        System.out.println("Inorder recovered : " + inorder(recovered));
        // Both should match

        // Edge case: single node
        TreeNode single = new TreeNode(42);
        String s2 = codec.serialize(single);
        System.out.println("\nSingle serialized : " + s2);
        System.out.println("Single recovered  : " + codec.deserialize(s2).val);

        // Edge case: null root
        String s3 = codec.serialize(null);
        System.out.println("\nNull serialized   : " + s3);
        System.out.println("Null recovered    : " + codec.deserialize(s3));
    }
}
