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
public class FiveUniqueStringsBitMask {

    static class StringWithBitmask {
        String s;
        int bitmask;

        public StringWithBitmask(String s) {
            this.s = s;
            this.bitmask = 0;
            for (char c : s.toCharArray()) {
                this.bitmask |= 1 << (c - 'a');
            }
        }
    }

    public List<String> findUniqueStrings(List<String> input) {
        // Step 1: Filter strings that have duplicate characters and compute bitmask
        List<StringWithBitmask> uniqueCharStrings = new ArrayList<>();
        for (String s : input) {
            if (hasUniqueCharacters(s)) {
                uniqueCharStrings.add(new StringWithBitmask(s));
            }
        }

        // Step 2: Generate combinations of 5 strings using optimized backtracking
        List<String> result = new ArrayList<>();
        if (findCombination(uniqueCharStrings, 5, 0, new ArrayList<>(), 0, result)) {
            return result;
        } else {
            // No valid combination found
            return new ArrayList<>();
        }
    }

    private boolean hasUniqueCharacters(String s) {
        boolean[] chars = new boolean[26];
        for (char c : s.toCharArray()) {
            if (chars[c - 'a']) {
                // Duplicate character found
                return false;
            }
            chars[c - 'a'] = true;
        }
        return true;
    }

    private boolean findCombination(List<StringWithBitmask> strings, int k, int start, List<String> current, int currentBitmask, List<String> result) {
        if (current.size() == k) {
            if (Integer.bitCount(currentBitmask) == 25) {
                result.addAll(current);
                return true;
            } else {
                return false;
            }
        }
        for (int i = start; i < strings.size(); i++) {
            StringWithBitmask swb = strings.get(i);
            if ((currentBitmask & swb.bitmask) == 0) {
                current.add(swb.s);
                int newBitmask = currentBitmask | swb.bitmask;
                if (findCombination(strings, k, i + 1, current, newBitmask, result)) {
                    return true;
                }
                current.remove(current.size() - 1);
                // No need to reset currentBitmask because integers are passed by value
            }
        }
        return false;
    }

    // Main method for testing
    public static void main(String[] args) {
        FiveUniqueStringsBitMask finder = new FiveUniqueStringsBitMask();
        List<String> input = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "ocean", "quick", "world", "jumps", "foxes", "liver");
        List<String> output = finder.findUniqueStrings(input);
        // System.out.println("Output: " + output);
        System.out.println(5/1);
        System.out.println(5%1);

    }
}
