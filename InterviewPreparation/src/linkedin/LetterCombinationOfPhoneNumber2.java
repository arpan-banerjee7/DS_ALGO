package linkedin;

import java.util.*;

public class LetterCombinationOfPhoneNumber2 {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    private TrieNode root = new TrieNode();
    private static final String[] MAPPING = {
            "", "", "abc", "def", "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
    };

    public List<String> findWordsForNumber(String digits, List<String> dictionary) {
        // Build the trie from the dictionary
        for (String word : dictionary) {
            insertWord(word);
        }

        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Use DFS on the trie guided by the digit string
        backtrack(digits, 0, root, new StringBuilder(), result);
        return result;
    }

    private void insertWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEndOfWord = true;
    }

    private void backtrack(String digits, int index, TrieNode node, StringBuilder sb, List<String> result) {
        if (index == digits.length()) {
            // If we've reached the end of the digit string, check if current node ends a valid word
            if (node.isEndOfWord) {
                result.add(sb.toString());
            }
            return;
        }

        int d = digits.charAt(index) - '0';
        String letters = MAPPING[d];
        for (char c : letters.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] != null) {
                sb.append(c);
                backtrack(digits, index + 1, node.children[idx], sb, result);
                sb.setLength(sb.length() - 1); // backtrack
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinationOfPhoneNumber2 sol = new LetterCombinationOfPhoneNumber2();
        List<String> dictionary = Arrays.asList("ad", "ae", "af", "hs", "kjd", "ew");
        String digits = "23";
        System.out.println(sol.findWordsForNumber(digits, dictionary));
        // Expect ["ad","ae","af","bd","be","bf","cd","ce","cf"] if these words are in the dictionary
    }
}


