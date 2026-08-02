package google.recursion;

/*
LC 394: Decode String

Grammar:
  decoded := chunk+
  chunk   := letters              -> append as-is
           | number [ decoded ]   -> repeat the inner decoded `number` times

Recursive-descent template (same as NumberOfAtoms / ParseLispExpression):
  '['     -> recurse (open a new scope)
  ']'     -> return  (close the scope)
  letter  -> append
  digit   -> read full number, then recurse on what's inside the brackets

Shared index `idx` walks the string across recursive calls.

Time:  O(output length) - every character of the result is built once.
Space: O(depth) recursion + O(output) for the strings.
*/
public class DecodeString {

    private int idx;

    public String decodeString(String s) {
        // Active: instance-field version
        idx = 0;
        return parse(s);
        // Alternative: pass-index + return-index (Result) version
        // return parseIdx(s, 0).str;
    }

    // returns the decoded string for the current scope (until ']' or end)
    private String parse(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        while (idx < n && s.charAt(idx) != ']') {
            char c = s.charAt(idx);

            if (Character.isDigit(c)) {
                // 1. read the full number
                int num = 0;
                while (idx < n && Character.isDigit(s.charAt(idx))) {
                    num = num * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                idx++; // 2. skip '['
                String inner = parse(s); // 3. recurse -> decoded contents (shares idx)
                idx++; // 4. skip ']'
                // 5. append inner repeated num times
                for (int k = 0; k < num; k++) {
                    sb.append(inner);
                }
            } else {
                // letter: append and advance
                sb.append(c);
                idx++;
            }
        }
        return sb.toString();
    }

    // ----- FIXED version: child returns its end index so parent can resume -----
    private static class Result {
        String str;  // decoded string for this scope
        int next;    // index of the ']' that closed this scope (or n at end)
        Result(String str, int next) {
            this.str = str;
            this.next = next;
        }
    }

    private Result parseIdx(String s, int idx) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        while (idx < n && s.charAt(idx) != ']') {
            char c = s.charAt(idx);

            if (Character.isDigit(c)) {
                // 1. read the full number
                int num = 0;
                while (idx < n && Character.isDigit(s.charAt(idx))) {
                    num = num * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                idx++; // 2. skip '['
                Result inner = parseIdx(s, idx); // 3. recurse -> string + where it stopped
                idx = inner.next + 1; // 4. resume PAST the inner scope's ']'  (the fix!)
                // 5. append inner repeated num times
                for (int k = 0; k < num; k++) {
                    sb.append(inner.str);
                }
            } else {
                // letter: append and advance
                sb.append(c);
                idx++;
            }
        }
        // idx now points at this scope's ']' (or n). Hand it back to the parent.
        return new Result(sb.toString(), idx);
    }

    public static void main(String[] args) {
        DecodeString solver = new DecodeString();

        String[] inputs = {
                "3[a]2[bc]",
                "3[a2[c]]",
                "2[abc]3[cd]ef",
                "abc",
                "10[a]",
                "4[3[a]2[bc]]"
        };
        String[] expected = {
                "aaabcbc",
                "accaccacc",
                "abcabccdcdcdef",
                "abc",
                "aaaaaaaaaa",
                "aaabcbcaaabcbcaaabcbcaaabcbc"
        };

        for (int i = 0; i < inputs.length; i++) {
            String res = solver.decodeString(inputs[i]);
            System.out.println("\"" + inputs[i] + "\" = " + res + " | Expected: " + expected[i] + " | " + (res.equals(expected[i]) ? "PASS" : "FAIL"));
        }
    }
}
