package google;

import java.util.*;

/*
Give a list of string, where every string in the list is of size 5.
Return the list of 5 string such that all the characters in each of the strings are unique
i.e if we combine all the strings(not nnecessary) we will have 25 unique characters)
eg
Input explanation
List of string with length of 5 each
intput = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "ocean", "quick", "world", "jumps", "foxes", "liver"]


all the 5 string have unique character for both of the way
ouput = ["abcde", "fghij", "klmno", "pqrst", "uvwxy"] or ["fghij", "klmno", "pqrst", "uvwxy", "zabcd"]
 */
public class FiveUniqueStrings {

    public static List<String> findUniqueStrings(List<String> input) {
        List<String> result = new ArrayList<>();
        Set<Character> usedChars = new HashSet<>();

        // Filter valid strings (length 5 with unique chars)
        List<String> validStrings = new ArrayList<>();
        for (String str : input) {
            if (isValidString(str)) {
                validStrings.add(str);
            }
        }

        // Try to find combination of 5 strings
        backtrack(validStrings, 0, new ArrayList<>(), usedChars, result);

        return result;
    }

    private static boolean isValidString(String str) {
        if (str.length() != 5) return false;

        Set<Character> chars = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!chars.add(c)) return false;
        }
        return true;
    }

    private static boolean backtrack(List<String> validStrings, int start,
                                     List<String> current, Set<Character> usedChars,
                                     List<String> result) {
        // If we found 5 strings, we're done
        if (current.size() == 5) {
            result.addAll(current);
            return true;
        }

        // Try each remaining string
        for (int i = start; i < validStrings.size(); i++) {
            String str = validStrings.get(i);

            // Check if this string's characters overlap with used characters
            boolean canUse = true;
            Set<Character> tempChars = new HashSet<>();
            for (char c : str.toCharArray()) {
                if (usedChars.contains(c)) {
                    canUse = false;
                    break;
                }
                tempChars.add(c);
            }

            if (canUse) {
                // Try using this string
                current.add(str);
                usedChars.addAll(tempChars);

                if (backtrack(validStrings, i + 1, current, usedChars, result)) {
                    return true;
                }

                // Backtrack
                current.remove(current.size() - 1);
                usedChars.removeAll(tempChars);
            }
        }

        return false;
    }


    // Main method for testing
    public static void main(String[] args) {
        FiveUniqueStrings finder = new FiveUniqueStrings();
        List<String> input = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "ocean", "quick", "world", "jumps", "foxes", "liver");
        List<String> output = finder.findUniqueStrings(input);
        System.out.println("Output: " + output);
    }
}
