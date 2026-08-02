package google.recursion;

/*
LC 439: Ternary Expression Parser

Grammar:
  expr  := value                      -> a lone char (base case)
         | cond ? expr : expr         -> recurse on both branches, pick by cond
  value := 'T' | 'F' | '0'..'9'

Recursive-descent template (same as DecodeString / NumberOfAtoms):
  - read the condition char, advance
  - if no '?' follows -> base case, it's a lone value, return it
  - else: skip '?', recurse for TRUE branch, skip ':', recurse for FALSE branch
  - return the branch chosen by the condition

KEY INSIGHT (your sticking point): you DON'T compute how far a branch extends.
You recurse, and the call stops on its own at the ':' that ends its value.
You also recurse on BOTH branches so the shared idx advances past both,
then just RETURN the one the condition selects.

Right-associative falls out naturally: the first char is the top-level
condition, and the branches nest to the right via recursion.
*/
public class TernaryOperator {

    private int idx;

    public char parseTernary(String expression) {
        idx = 0;
        return eval(expression);
    }

    private char eval(String s) {
        char cond = s.charAt(idx);
        idx++; // consume this char (a value or a condition)

        // base case: a lone value, no '?' follows
        if (idx >= s.length() || s.charAt(idx) != '?') {
            return cond;
        }

        // it's: cond ? trueBranch : falseBranch
        idx++;                    // skip '?'
        char trueVal = eval(s);   // recurse TRUE branch (stops on its ':')
        idx++;                    // skip ':'
        char falseVal = eval(s);  // recurse FALSE branch

        return cond == 'T' ? trueVal : falseVal;
    }

    public static void main(String[] args) {
        TernaryOperator solver = new TernaryOperator();

        String[] inputs = {
                "T?2:3",
                "F?1:T?4:5",
                "T?T?F:5:3",
                "T",
                "F?T?1:2:F?3:4"
        };
        char[] expected = {'2', '4', 'F', 'T', '4'};

        for (int i = 0; i < inputs.length; i++) {
            char res = solver.parseTernary(inputs[i]);
            System.out.println("\"" + inputs[i] + "\" = " + res + " | Expected: " + expected[i] + " | " + (res == expected[i] ? "PASS" : "FAIL"));
        }
    }
}
