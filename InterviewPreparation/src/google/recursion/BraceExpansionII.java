package google.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
LC 1096: Brace Expansion II

Grammar:
  - a single lowercase letter is a set with one word:  "a" -> {"a"}
  - comma inside braces = UNION:           {x,y} -> R(x) ∪ R(y)
  - adjacency (no comma) = CONCATENATION:  xy    -> { p+q | p in R(x), q in R(y) }
  - braces {} just group.

Return the sorted, de-duplicated list of words.
*/
public class BraceExpressionII {

    // CONTRACT: solve() parses ONE expression level (a union of comma-separated
    // terms) and leaves idx JUST PAST the '}' that closed this level
    // (or at end-of-string for the top level).
    //
    // Two accumulators per level:
    //   union   = set of all completed terms seen so far at this level
    //   current = the PRODUCT of all adjacent factors in the term being built
    //             (starts as {""}, the identity for concatenation)
    private int idx;

    private Set<String> solve(String s) {
        Set<String> union = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add(""); // identity: product({""}, X) == X

        while (idx < s.length()) {
            char ch = s.charAt(idx);

            if (ch == '{') {
                idx++;                          // consume '{'
                Set<String> inner = solve(s);   // recurse: leaves idx past matching '}'
                current = product(current, inner);
            } else if (ch == '}') {
                idx++;                          // consume '}': this level is done
                break;
            } else if (ch == ',') {
                idx++;                          // term finished -> flush into union
                union.addAll(current);
                current = new HashSet<>();
                current.add("");                // reset identity for next term
            } else {                            // a lowercase letter = a factor
                current = product(current, Collections.singleton(String.valueOf(ch)));
                idx++;
            }
        }
        union.addAll(current);                  // flush the final term
        return union;
    }

    // cross-product: { a + b | a in A, b in B }
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
        idx = 0;
        Set<String> result = solve(expression);
        return new ArrayList<>(new java.util.TreeSet<>(result)); // sorted + de-duped
    }

    // ==========================================================================
    // Test harness — do not change. Compares your output (sorted, de-duped)
    // against the expected sorted list.
    // ==========================================================================
    public static void main(String[] args) {
        BraceExpressionII solver = new BraceExpressionII();

        String[] inputs = {
                "{a,b}{c,{d,e}}",            // adjacency + nested union

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
            List<String> raw;
            try {
                raw = solver.braceExpansionII(inputs[i]);
            } catch (Exception e) {
                System.out.println("\"" + inputs[i] + "\" -> THREW " + e + " | FAIL");
                continue;
            }
            List<String> got = normalize(raw);
            List<String> exp = normalize(expected[i]);
            boolean ok = got.equals(exp);
            if (ok) pass++;
            System.out.println("\"" + inputs[i] + "\"\n   got      = " + got
                    + "\n   expected = " + exp + "\n   " + (ok ? "PASS" : "FAIL") + "\n");
        }
        System.out.println("==== " + pass + "/" + inputs.length + " passed ====");
    }

    // sort + dedup so order of your output doesn't matter
    private static List<String> normalize(List<String> in) {
        if (in == null) return Collections.singletonList("<null>");
        List<String> copy = new ArrayList<>(new java.util.TreeSet<>(in));
        return copy;
    }
}
