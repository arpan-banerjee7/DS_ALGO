package uber;

import java.util.*;

public class GenerateAllCombinationsRest {

    public static void generateWords(String input, char mandatory) {
        // Split the input string into characters
        char[] letters = input.toCharArray();

        // List to store all valid words
        List<String> validWords = new ArrayList<>();

        // Helper function for backtracking
        backtrack(letters, mandatory, new StringBuilder(), validWords, 0);

        // Print all valid words
        for (String word : validWords) {
            System.out.println(word);
        }
    }

    private static void backtrack(char[] letters, char mandatory, StringBuilder current, List<String> validWords, int index) {
        // Base case: Add valid words (at least 4 characters and includes mandatory letter)
        if (current.length() >= 4 && current.toString().contains(String.valueOf(mandatory))) {
            validWords.add(current.toString());
        }

        // Iterate through each character
        for (int i = index; i < letters.length; i++) {
            // Add the current letter to the combination
            current.append(letters[i]);

            // Recurse to generate further combinations
            backtrack(letters, mandatory, current, validWords, i + 1);

            // Backtrack: Remove the last added character
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        // Input: 7 distinct letters and one mandatory character
        String input = "TWADLNI"; // Example input
        char mandatory = 'I';     // Mandatory letter

        System.out.println("All valid words:");
        generateWords(input, mandatory);
    }
}



