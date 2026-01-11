package google.trie;

import java.util.*;

/*
Q. Given:
- allFiles: list of all files in system
- selectedFiles: subset selected by user

Rules:
- If all files under a folder are selected → output only the folder
- If partially selected → go deeper
- If it's a file and selected → output the file path

Approach:
1. Build Trie of all files and count total files in each subtree
2. Mark selected files and count selected files in each subtree
3. DFS:
   - If totalFiles == selectedFiles → compress to folder
   - Else traverse children
*/

public class CompressFilePaths {

    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        int totalFiles = 0;     // Total files in subtree
        int selectedFiles = 0;  // Selected files in subtree
        boolean isFile = false;
        String name;

        TrieNode(String name) {
            this.name = name;
        }
    }

    public static List<String> compressPaths(List<String> allFiles, List<String> selectedFiles) {
        TrieNode root = new TrieNode("");

        // 1. Build trie with all files
        for (String filePath : allFiles) {
            insertPath(root, filePath);
        }

        // 2. Mark selected files
        for (String filePath : selectedFiles) {
            markSelected(root, filePath);
        }

        // 3. Generate compressed output
        List<String> result = new ArrayList<>();
        generateCompressedPaths(root, "", result);
        return result;
    }

    // Insert a file path into the trie and increment totalFiles count
    private static void insertPath(TrieNode root, String filePath) {
        String[] parts = filePath.split("/");
        TrieNode current = root;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            current.children.putIfAbsent(part, new TrieNode(part));
            current = current.children.get(part);

            if (i == parts.length - 1) {
                current.isFile = true;
            }
        }

        // Increment totalFiles for all nodes in path
        current = root;
        for (String part : parts) {
            current = current.children.get(part);
            current.totalFiles++;
        }
    }

    // Mark a file as selected and increment selectedFiles count
    private static void markSelected(TrieNode root, String filePath) {
        String[] parts = filePath.split("/");
        TrieNode current = root;

        // Navigate to file node
        for (String part : parts) {
            if (!current.children.containsKey(part)) {
                return; // Should not happen for valid input
            }
            current = current.children.get(part);
        }

        // Increment selectedFiles for all nodes in path
        current = root;
        for (String part : parts) {
            current = current.children.get(part);
            current.selectedFiles++;
        }
    }

    // DFS to generate compressed output
    private static void generateCompressedPaths(TrieNode node, String currentPath, List<String> result) {

        if (!node.name.isEmpty()) {
            currentPath = currentPath.isEmpty()
                    ? node.name
                    : currentPath + "/" + node.name;
        }

        // If it's a file and selected
        if (node.isFile) {
            if (node.selectedFiles > 0) {
                result.add(currentPath);
            }
            return;
        }

        // If folder is fully selected → compress
        if (node.totalFiles > 0 && node.totalFiles == node.selectedFiles && !currentPath.isEmpty()) {
            result.add(currentPath);
            return;
        }

        // Else recurse
        for (TrieNode child : node.children.values()) {
            if (child.selectedFiles > 0) {
                generateCompressedPaths(child, currentPath, result);
            }
        }
    }

    // ---------------- TESTING ----------------

    public static void main(String[] args) {

        System.out.println("=== Test Case 1: Original Example ===");

        List<String> allFiles1 = Arrays.asList(
                "a/b.txt",
                "b/c.txt",
                "b/d.txt",
                "c/e.txt",
                "c/f/a.txt",
                "c/f/b.txt",
                "c/g.txt",
                "d/a/b.txt"
        );

        List<String> selectedFiles1 = Arrays.asList(
                "b/c.txt",
                "b/d.txt",
                "c/e.txt",
                "c/f/a.txt",
                "c/f/b.txt",
                "d/a/b.txt"
        );

        List<String> result1 = compressPaths(allFiles1, selectedFiles1);
        System.out.println("Expected: [b, c/e.txt, c/f, d]");
        System.out.println("Got     : " + result1);

        // --------------------------------------------------------

        System.out.println("\n=== Test Case 2: Similar Paths Edge Case ===");

        List<String> allFiles2 = Arrays.asList(
                "d/ab/somefile.txt",
                "da/b/somefile.txt"
        );

        System.out.println("\n2a) Both selected:");
        List<String> selectedFiles2a = Arrays.asList(
                "d/ab/somefile.txt",
                "da/b/somefile.txt"
        );
        System.out.println(compressPaths(allFiles2, selectedFiles2a));

        System.out.println("\n2b) Only d/ab selected:");
        List<String> selectedFiles2b = Arrays.asList(
                "d/ab/somefile.txt"
        );
        System.out.println(compressPaths(allFiles2, selectedFiles2b));

        System.out.println("\n2c) Only da/b selected:");
        List<String> selectedFiles2c = Arrays.asList(
                "da/b/somefile.txt"
        );
        System.out.println(compressPaths(allFiles2, selectedFiles2c));
    }
}
