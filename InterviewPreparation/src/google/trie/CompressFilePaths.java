package google.trie;

import java.util.*;

public class CompressFilePaths {
    static class TrieNode {
        Map<String, TrieNode> children;
        boolean isFile;
        int childrenCount;

        TrieNode() {
            children = new HashMap<>();
            isFile = false;
            childrenCount = 0;
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String[] words) {
            TrieNode ws = root;
            for (String word : words) {
                ws.childrenCount += 1;
                if (!ws.children.containsKey(word)) {
                    ws.children.put(word, new TrieNode());
                }
                ws = ws.children.get(word);
            }
            ws.isFile = true;
        }

        public void decrementChildren(String[] words) {
            TrieNode ws = root;
            for (String word : words) {
                if (!ws.children.containsKey(word)) {
                    return;
                }
                ws.childrenCount -= 1;
                ws = ws.children.get(word);
            }
        }

        public void search(String[] words) {
            TrieNode ws = root;
            for (String word : words) {
                if (!ws.children.containsKey(word)) {
                    return;
                }
                ws = ws.children.get(word);
                // System.out.println(word + ":" + ws.childrenCount);
            }
        }

        public String getPath(String[] words) {
            TrieNode ws = root;
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                ws = ws.children.get(word);
                if (ws.childrenCount == 0 || ws.isFile) {
                    sb.append(word);
                    return sb.toString();
                }
                sb.append(word + "/");
            }
            return "";
        }


    }

    public static List compressInput(List<String> all_files, List<String> selected_files) {
        Trie trie = new Trie();
        for (String filePath : all_files) {
            String[] words = filePath.split("/");
            trie.insert(words);
        }
        for (String filePath : selected_files) {
            String[] words = filePath.split("/");
            trie.decrementChildren(words);
        }
//        for (String filePath : selected_files) {
//            String[] words = filePath.split("/");
//            trie.search(words);
//        }
        List result = new ArrayList<>();
        for (String filePath : selected_files) {
            String[] words = filePath.split("/");
            String finalPath = trie.getPath(words);
            if (finalPath.length() != 0 && !result.contains(finalPath))
                result.add(finalPath);
        }
        return result;
    }

    public static void main(String[] args) {
//        List all_files = Arrays.asList("a/b.txt", "b/c.txt",
//                "b/d.txt", "c/e.txt", "c/f/a.txt", "c/f/b.txt", "c/g.txt", "d/a/b.txt");
//        List selected_files = Arrays.asList("b/c.txt", "b/d.txt", "c/e.txt", "c/f/a.txt", "c/f/b.txt", "d/a/b.txt");
        List<String> all_files = Arrays.asList(
                "/a",
                "a/b/c/d.txt",
                "a/b/c/e.txt",
                "a/b/b.txt",
                "a/b/e.txt",
                "b/c/d.txt"
        );

        List<String> selected_files = Arrays.asList(
                "a/b/c/d.txt",
                "a/b/c/e.txt",
                "a/b/b.txt",
                "b/c/d.txt"
        );

        List<String> res = compressInput(all_files, selected_files);
        for (String r : res) {
            System.out.println(r);
        }


    }
}
