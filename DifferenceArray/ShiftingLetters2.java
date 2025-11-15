//https://leetcode.com/problems/shifting-letters-ii/?envType=problem-list-v2&envId=2fdm7o03
//Approach (Using Difference Array Technique)
//T.C : O(m+n), m = length of queries, n = length of array
//S.C : O(n)
/**
 * IMPORTANT: This problem requires THREE critical operations:
 * 
 * 1. FIRST MODULO (Line ~42): During cumulative sum calculation in difference
 * array
 * - Purpose: Prevent integer overflow when shifts are very large
 * - Example: shift values can be in millions (505870226), summing them causes
 * overflow
 * - Since we only care about final position in 26-letter alphabet, we can mod
 * early
 * 
 * 2. NEGATIVE SHIFT HANDLING (Line ~71): Convert negative shifts to positive
 * equivalents
 * - Problem: In Java, negative % 26 returns negative value (-12 % 26 = -12, not
 * 14)
 * - Solution: Add 26 to convert negative to positive equivalent
 * - Example: String "ezb", diffArray [23, 5, -12]
 * Position 2: 'b' + (-12)
 * Without: 'b'(1) + (-12) = -11 → -11 % 26 = -11 → invalid!
 * With: -12 + 26 = 14 → 'b'(1) + 14 = 15 → 'p' ✓
 * Key: Shifting left by 12 = Shifting right by 14 (26 - 12 = 14)
 * 
 * 3. SECOND MODULO (Line ~86): When converting position back to character
 * - Purpose: Handle wrap-around past 'z' (circular alphabet behavior)
 * - Process:
 * 1. (ch - 'a'): Convert character to position in alphabet (0-25)
 * 2. + shift: Add the shift amount
 * 3. % 26: Wrap around if position > 25 (past 'z' wraps to 'a')
 * 4. + 'a': Convert position back to character
 * - Example: 'e'(4) + 23 = 27 → 27%26 = 1 → 'b'
 * 
 * COMPLETE EXAMPLE for String "ezb", diffArray [23, 5, -12] after negative
 * handling [23, 5, 14]:
 * Position 0: 'e'(4) + 23 = 27 → 27%26 = 1 → 'b'
 * Position 1: 'z'(25) + 5 = 30 → 30%26 = 4 → 'e'
 * Position 2: 'b'(1) + 14 = 15 → 15%26 = 15 → 'p'
 * Result: "bep"
 */
public class ShiftingLetters2 {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n]; // Difference array with size n

        // Step 1: Populate the difference array
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            if (direction == 1) { // Forward shift
                diff[start] += 1;
                if (end + 1 < n) {
                    diff[end + 1] -= 1;
                }
            } else { // Backward shift
                diff[start] -= 1;
                if (end + 1 < n) {
                    diff[end + 1] += 1;
                }
            }
        }

        // Step 2: Compute the prefix sum to get the net shifts for each character
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1]; // Add the value from the previous index
        }

        // Step 3: Apply the shifts to the string
        StringBuilder result = new StringBuilder(s);
        for (int i = 0; i < n; i++) {
            int shift = diff[i] % 26; // Ensure shift is within the range [0, 25]
            if (shift < 0) {
                shift += 26; // Handle negative shifts (backward)
            }

            // Apply the shift to character
            char newChar = (char) (((result.charAt(i) - 'a' + shift) % 26) + 'a');
            result.setCharAt(i, newChar);
        }

        return result.toString();
    }

    // Example runner
    public static void main(String[] args) {
        ShiftingLetters2 solver = new ShiftingLetters2();
        String s = "abc";
        int[][] shifts = { { 0, 1, 0 }, { 1, 2, 1 }, { 0, 2, 1 } };

        String res = solver.shiftingLetters(s, shifts);
        System.out.println(res);
        // Expected output: "ace"
    }
}
