package google.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
3481. Apply Substitutions
You are given a replacements mapping and a text string that may contain placeholders formatted as %var%,
where each var corresponds to a key in the replacements mapping. Each replacement value may itself contain one or
more such placeholders.Each placeholder is replaced by the value associated with its corresponding replacement key.
Return the fully substituted text string which does not contain any placeholders.

Example 1:
Input: replacements = [["A","abc"],["B","def"]], text = "%A%_%B%"

Output: "abc_def"

Explanation:
The mapping associates "A" with "abc" and "B" with "def".
Replace %A% with "abc" and %B% with "def" in the text.
The final text becomes "abc_def".

Example 2:
Input: replacements = [["A","bce"],["B","ace"],["C","abc%B%"]], text = "%A%_%B%_%C%"

Output: "bce_ace_abcace"

Explanation:

The mapping associates "A" with "bce", "B" with "ace", and "C" with "abc%B%".
Replace %A% with "bce" and %B% with "ace" in the text.
Then, for %C%, substitute %B% in "abc%B%" with "ace" to obtain "abcace".
The final text becomes "bce_ace_abcace"

Constraints:

1 <= replacements.length <= 10
Each element of replacements is a two-element list [key, value], where:
key is a single uppercase English letter.
value is a non-empty string of at most 8 characters that may contain zero or more placeholders formatted as %<key>%.
All replacement keys are unique.
The text string is formed by concatenating all key placeholders (formatted as %<key>%) randomly from the replacements mapping, separated by underscores.
text.length == 4 * replacements.length - 1
Every placeholder in the text or in any replacement value corresponds to a key in the replacements mapping.
There are no cyclic dependencies between replacement keys.

 */
public class ApplySubstituionLeetcode {

    // -------------------- Solution 1: Recursive string concat --------------------
    private String findReplacement(Map<String, String> dict, Map<String, String> memo, String text) {
        if(memo.containsKey(text)){
            return memo.get(text);
        }
        int start = text.indexOf('%');
        if (start == -1) {
            return text;
        }
        int end = text.indexOf('%', start + 1);
        if (end == -1) {
            return text;
        }
        String key = text.substring(start + 1, end);
        String value = findReplacement(dict, memo, dict.getOrDefault(key,""));
        memo.put(key,value);
        return text.substring(0, start) + value + findReplacement(dict, memo, text.substring(end + 1));
    }

    public String applySubstitutions(List<List<String>> replacements, String text) {
        Map<String, String> dict = new HashMap<>();
        for (List<String> replacement : replacements) {
            dict.put(replacement.get(0), replacement.get(1));
        }
        Map<String, String> memo = new HashMap<>();
        return findReplacement(dict, memo, text);
    }

    // -------------------- Solution 2: StringBuilder + iterative scan --------------------
    private String findReplacementSB(Map<String, String> dict, String text) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = text.length();
        while (i < n) {
            if (text.charAt(i) == '%') {
                int j = text.indexOf('%', i + 1);
                if (j == -1) {
                    sb.append(text.substring(i));
                    break;
                }
                String key = text.substring(i + 1, j);
                sb.append(findReplacementSB(dict, dict.getOrDefault(key, "")));
                i = j + 1;
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public String applySubstitutionsSB(List<List<String>> replacements, String text) {
        Map<String, String> dict = new HashMap<>();
        for (List<String> replacement : replacements) {
            dict.put(replacement.get(0), replacement.get(1));
        }
        return findReplacementSB(dict, text);
    }

    public static void main(String[] args) {
        ApplySubstituionLeetcode solver = new ApplySubstituionLeetcode();

        // Test 1: simple substitution
        List<List<String>> r1 = Arrays.asList(
                Arrays.asList("A", "abc"),
                Arrays.asList("B", "def")
        );
        System.out.println(solver.applySubstitutions(r1, "%A%_%B%"));
        // Expected: abc_def

        // Test 2: nested substitution in value
        List<List<String>> r2 = Arrays.asList(
                Arrays.asList("A", "bce"),
                Arrays.asList("B", "ace"),
                Arrays.asList("C", "abc%B%")
        );
        System.out.println(solver.applySubstitutions(r2, "%A%_%B%_%C%"));
        // Expected: bce_ace_abcace

        // Test 3: chain of nested substitutions A -> B -> C -> "hello"
        List<List<String>> r3 = Arrays.asList(
                Arrays.asList("A", "%B%"),
                Arrays.asList("B", "%C%"),
                Arrays.asList("C", "hello")
        );
        System.out.println(solver.applySubstitutions(r3, "%A%"));
        // Expected: hello

        // Test 4: single key, no nesting
        List<List<String>> r4 = Arrays.asList(
                Arrays.asList("X", "42")
        );
        System.out.println(solver.applySubstitutions(r4, "%X%"));
        // Expected: 42

        // Test 5: edge cases-- fails here
        List<List<String>> r5 = Arrays.asList(
                Arrays.asList("X", "temp"),
                Arrays.asList("Y", "50")
        );
        System.out.println(solver.applySubstitutions(r5, "%%X%_%Y%%"));

        // Test 6: edge cases-- fails here
        List<List<String>> r6 = Arrays.asList(
                Arrays.asList("X", "wrong"),
                Arrays.asList("Y", "case")
        );
        System.out.println(solver.applySubstitutions(r6, "%X%_%Y"));
    }
}
