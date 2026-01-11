package google;

import java.util.*;

/*
Links:
https://leetcode.com/discuss/post/4426050/google-phone-screen-interview-by-pawarak-j2ao/
https://leetcode.com/discuss/post/1369886/google-phone-filepaths-by-anonymous_user-bqno/
https://leetcode.com/discuss/post/4434504/google-phone-screen-replace-files-with-d-g5js/
https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/

Q. Given:
- List of all files
- List of selected files

SIMPLIFIED VERSION:
- Assumes single-character file and folder names.

/*
═══════════════════════════════════════════════════════════════════════════════
EXAMPLE WALKTHROUGH
═══════════════════════════════════════════════════════════════════════════════

All files: [a/b, b/c, b/d, c/e, c/f/a, c/f/b, c/g, d/a/b]
Selected:  [b/c, b/d, c/e, c/f/a, c/f/b, d/a/b]
Expected:  [b, c/e, c/f, d]

───────────────────────────────────────────────────────────────────────────────
STEP 1: Build Trie & Count totalFiles
───────────────────────────────────────────────────────────────────────────────

Insert all file paths into trie. Each node tracks totalFiles in its subtree.

                              ROOT
                 ┌──────┬──────┬──────┬──────┐
                 ▼      ▼      ▼      ▼
               a(1)   b(2)   c(4)   d(1)       ← totalFiles count
                 │    ┌┴┐    ┌─┼─┐    │
                 ▼    ▼ ▼    ▼ ▼ ▼    ▼
                [b]  [c][d] [e]  f [g]  a(1)   ← [x] = isFile=true
                               │      │
                              ┌┴┐     ▼
                              ▼ ▼    [b]
                             [a][b]

c has 3 children:
- e (file)
- f (folder with 2 files)
- g (file)

So:
c.totalFiles = 1 (e) + 2 (f) + 1 (g) = 4

Files created:
  a/b     → a(1) → b(file)
  b/c     → b(2) → c(file)
  b/d     → b(2) → d(file)
  c/e     → c(4) → e(file)
  c/f/a   → c(4) → f(2) → a(file)
  c/f/b   → c(4) → f(2) → b(file)
  c/g     → c(4) → g(file)
  d/a/b   → d(1) → a(1) → b(file)

───────────────────────────────────────────────────────────────────────────────
STEP 2: Mark Selected Files & Count selectedFiles
───────────────────────────────────────────────────────────────────────────────

For each selected file, increment selectedFiles for all ancestors.

                              ROOT
                 ┌──────┬──────┬──────┬──────┐
                 ▼      ▼      ▼      ▼
                 a      b      c      d
                0/1    2/2    3/4    1/1       ← selectedFiles / totalFiles
                 │    ┌┴┐    ┌─┼─┐    │
                 ▼    ▼ ▼    ▼ ▼ ▼    ▼
                [b]  [c][d] [e]  f [g]  a
                0/1  1/1 1/1 1/1 2/2 0/1 1/1
                               │      │
                              ┌┴┐     ▼
                              ▼ ▼    [b]
                             [a][b]
                             1/1 1/1

Key observations:
  • b:   2/2 selected → ALL selected ✓
  • c:   3/4 selected → NOT all (g missing) ✗
  • c/f: 2/2 selected → ALL selected ✓
  • d:   1/1 selected → ALL selected ✓

───────────────────────────────────────────────────────────────────────────────
STEP 3: DFS to Generate Compressed Output
───────────────────────────────────────────────────────────────────────────────

Rule:
If selectedFiles == totalFiles → output this folder and STOP recursing.

DFS Traversal:

┌─────────────────────────────────────────────────────────────────────────────┐
│ Node   │ sel/total │ Decision                          │ Output            │
├─────────────────────────────────────────────────────────────────────────────┤
│ a      │ 0/1       │ No selections → SKIP              │                   │
│ b      │ 2/2       │ ALL selected → COMPRESS           │ "b" ✓             │
│ c      │ 3/4       │ Partial → RECURSE                 │                   │
│  └─ e  │ 1/1       │ File, selected → OUTPUT           │ "c/e" ✓           │
│  └─ f  │ 2/2       │ ALL selected → COMPRESS           │ "c/f" ✓           │
│  └─ g  │ 0/1       │ No selections → SKIP              │                   │
│ d      │ 1/1       │ ALL selected → COMPRESS           │ "d" ✓             │
└─────────────────────────────────────────────────────────────────────────────┘

FINAL OUTPUT:
["b", "c/e", "c/f", "d"]

═══════════════════════════════════════════════════════════════════════════════

TIME  : O(N × M) where N = number of files, M = average path length
SPACE : O(N × M) for trie storage

═══════════════════════════════════════════════════════════════════════════════

Rules:
- If all files under a folder are selected → output folder
- Else → output individual file paths

Example:
All files:  [a/b, b/c, b/d, c/e, c/f/a, c/f/b, c/g, d/a/b]
Selected:   [b/c, b/d, c/e, c/f/a, c/f/b, d/a/b]
Output:     [b, c/e, c/f, d]

Time:  O(N × M)
Space: O(N × M)
*/

public class CompressFilePathsSimple {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int totalFiles = 0;
        int selectedFiles = 0;
        boolean isFile = false;
        char name;

        TrieNode(char name) {
            this.name = name;
        }
    }

    public static List<String> compressPaths(List<String> allFiles, List<String> selectedFiles) {
        TrieNode root = new TrieNode('\0');

        // 1. Build trie with all files
        for (String path : allFiles) {
            insertPath(root, path);
        }

        // 2. Mark selected files
        for (String path : selectedFiles) {
            markSelected(root, path);
        }

        // 3. Generate compressed output
        List<String> result = new ArrayList<>();
        generateCompressed(root, new StringBuilder(), result);
        return result;
    }

    private static void insertPath(TrieNode root, String path) {
        String[] parts = path.split("/");
        TrieNode curr = root;

        // Insert nodes
        for (int i = 0; i < parts.length; i++) {
            char c = parts[i].charAt(0);
            curr.children.putIfAbsent(c, new TrieNode(c));
            curr = curr.children.get(c);

            if (i == parts.length - 1) {
                curr.isFile = true;
            }
        }

        // Increment totalFiles for all ancestors
        curr = root;
        for (String part : parts) {
            curr = curr.children.get(part.charAt(0));
            curr.totalFiles++;
        }
    }

    private static void markSelected(TrieNode root, String path) {
        String[] parts = path.split("/");
        TrieNode curr = root;

        // Verify path exists
        for (String part : parts) {
            char c = part.charAt(0);
            if (!curr.children.containsKey(c)) return;
            curr = curr.children.get(c);
        }

        // Increment selectedFiles for all ancestors
        curr = root;
        for (String part : parts) {
            curr = curr.children.get(part.charAt(0));
            curr.selectedFiles++;
        }
    }

    private static void generateCompressed(TrieNode node, StringBuilder path, List<String> result) {

        // Build current path
        if (node.name != '\0') {
            if (path.length() > 0) path.append('/');
            path.append(node.name);
        }

        // If fully selected → compress here
        if (node.totalFiles > 0 && node.totalFiles == node.selectedFiles && node.name != '\0') {
            result.add(path.toString());
            return;
        }

        // Recurse into children
        for (TrieNode child : node.children.values()) {
            if (child.selectedFiles > 0) {
                int len = path.length();
                generateCompressed(child, path, result);
                path.setLength(len); // backtrack
            }
        }
    }

    // ---------------- TESTING ----------------

    public static void main(String[] args) {

        System.out.println("=== Single-Char File Names Version ===\n");

        // Test 1: Original example
        System.out.println("Test 1: Original Example");

        List<String> all1 = Arrays.asList(
                "a/b", "b/c", "b/d", "c/e", "c/f/a", "c/f/b", "c/g", "d/a/b"
        );

        List<String> sel1 = Arrays.asList(
                "b/c", "b/d", "c/e", "c/f/a", "c/f/b", "d/a/b"
        );

        System.out.println("All files : " + all1);
        System.out.println("Selected  : " + sel1);
        System.out.println("Expected  : [b, c/e, c/f, d]");
        System.out.println("Got       : " + compressPaths(all1, sel1));

        // Test 2: All files selected
        System.out.println("\nTest 2: All Files Selected");

        List<String> all2 = Arrays.asList("a/b", "a/c");
        List<String> sel2 = Arrays.asList("a/b", "a/c");

        System.out.println("Expected: [a]");
        System.out.println("Got     : " + compressPaths(all2, sel2));

        // Test 3: Partial selection
        System.out.println("\nTest 3: Partial Selection");

        List<String> all3 = Arrays.asList("a/b", "a/c", "a/d");
        List<String> sel3 = Arrays.asList("a/b", "a/d");

        System.out.println("Expected: [a/b, a/d]");
        System.out.println("Got     : " + compressPaths(all3, sel3));

        // Test 4: Deep nesting
        System.out.println("\nTest 4: Deep Nesting");

        List<String> all4 = Arrays.asList("a/b/c/d");
        List<String> sel4 = Arrays.asList("a/b/c/d");

        System.out.println("Expected: [a]");
        System.out.println("Got     : " + compressPaths(all4, sel4));
    }
}
