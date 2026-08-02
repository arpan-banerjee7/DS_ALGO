package google.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
LC 1096: Brace Expansion II — SPLIT / SUBSTRING style (NO shared index).

Contrast with BraceExpressionII (shared-idx single pass):
  - Here every recursive call receives its OWN self-contained substring and
    uses a purely LOCAL index. Nothing is shared, nothing is returned as a
    position. The parent never needs to know "where did the child stop?"
    because it handed the child a complete slice.
  - Cost vs shared-idx: extra substring copies + a balance scan to find each
    matching '}' and each top-level comma. Same answer, more allocation.

Two-level grammar, handled by two helpers:
  - splitTopLevel(s)  -> split on depth-0 commas  => UNION the parts
  - within one term   -> walk factors (letters / {..} groups) => PRODUCT them
*/
public class BraceExpansionIISplit {

    private Set<String> solve(String s) {
        // Level 1: union of comma-separated terms (top-level commas only).
        List<String> terms = splitTopLevel(s);
        Set<String> result = new HashSet<>();
        if (terms.size() > 1) {
            for (String t : terms) {
                result.addAll(solve(t)); // each term is a self-contained substring
            }
            return result;
        }

        // Level 2: a single term = PRODUCT of adjacent factors.
        Set<String> current = new HashSet<>();
        current.add(""); // identity for concatenation
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '{') {
                int j = matchingBrace(s, i);                 // index of the matching '}'
                Set<String> inner = solve(s.substring(i + 1, j)); // recurse on inner slice
                current = product(current, inner);
                i = j + 1;                                   // jump past the '}'
            } else {                                         // a lowercase letter
                current = product(current, Collections.singleton(String.valueOf(c)));
                i++;
            }
        }
        result.addAll(current);
        return result;
    }

    // split s on commas that are at brace-depth 0
    private List<String> splitTopLevel(String s) {
        List<String> parts = new ArrayList<>();
        int balance = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') balance++;
            else if (c == '}') balance--;
            else if (c == ',' && balance == 0) {
                parts.add(s.substring(start, i));
                start = i + 1;
            }
        }
        parts.add(s.substring(start));
        return parts;
    }

    // given s.charAt(open) == '{', return the index of its matching '}'
    private int matchingBrace(String s, int open) {
        int balance = 0;
        for (int j = open; j < s.length(); j++) {
            if (s.charAt(j) == '{') balance++;
            else if (s.charAt(j) == '}') {
                balance--;
                if (balance == 0) return j;
            }
        }
        return -1; // valid input guarantees this never happens
    }

    private Set<String> product(Set<String> a, Set<String> b) {
        Set<String> res = new HashSet<>();
        for (String x : a) {
            for (String y : b) {
                res.add(x + y);
            }
        }
        return res;
    }

    public List<String> braceExpansionII(String expression) {
        return new ArrayList<>(new TreeSet<>(solve(expression))); // sorted + de-duped
    }

    // ==========================================================================
    // Test harness (same cases as BraceExpressionII).
    // ==========================================================================
    public static void main(String[] args) {
        BraceExpansionIISplit solver = new BraceExpansionIISplit();

        String[] inputs = {
                "{a,b}{c,{d,e}}",
                "{{a,b},{b,c}}",
                "{a,b}{b,c}",
                "{a,{b,c}}{d,{e,f}}",
                "abcd",
                "a",
                "{a,b,c}",
                "{a}{b}{c}",
        };

        @SuppressWarnings("unchecked")
        List<String>[] expected = new List[] {
                Arrays.asList("ac", "ad", "ae", "bc", "bd", "be"),
                Arrays.asList("a", "b", "c"),
                Arrays.asList("ab", "ac", "bb", "bc"),
                Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
                Arrays.asList("abcd"),
                Arrays.asList("a"),
                Arrays.asList("a", "b", "c"),
                Arrays.asList("abc"),
        };

        int pass = 0;
        for (int i = 0; i < inputs.length; i++) {
            List<String> got;
            try {
                got = new ArrayList<>(new TreeSet<>(solver.braceExpansionII(inputs[i])));
            } catch (Exception e) {
                System.out.println("\"" + inputs[i] + "\" -> THREW " + e + " | FAIL");
                continue;
            }
            List<String> exp = new ArrayList<>(new TreeSet<>(expected[i]));
            boolean ok = got.equals(exp);
            if (ok) pass++;
            System.out.println("\"" + inputs[i] + "\"\n   got      = " + got
                    + "\n   expected = " + exp + "\n   " + (ok ? "PASS" : "FAIL") + "\n");
        }
        System.out.println("==== " + pass + "/" + inputs.length + " passed ====");
    }
}
