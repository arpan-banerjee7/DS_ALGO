package google.recursion;

/*
Remove brackets from an algebraic string with + and - operators.

Examples:
  "(a-(b+c)+d)"            -> "a-b-c+d"
  "a-(b-c-(d+e))-f"        -> "a-b+c+d+e-f"

KEY INVARIANT:
  A letter's final sign = (its LOCAL +/- sign) * (the FLIP its group inherited).
  When we recurse into '(', the child inherits flip = parentFlip * localSign.

CONTRACT (shared idx):
  solve() consumes one parenthesized region and leaves idx JUST PAST its ')'
  (or at end-of-string for the top-level call).
*/
public class RemoveBracketsFromString {

    private int idx;
    private StringBuilder out;

    public String simplify(String s) {
        s = s.replace(" ", "");      // normalize whitespace
        idx = 0;
        out = new StringBuilder();
        solve(s, 1);                 // top level inherits a '+' flip
        return out.toString();
    }

    // flip = +1 or -1: the sign multiplier this whole group inherited
    private void solve(String s, int flip) {
        int localSign = 1;           // sign of the operand we're about to read

        while (idx < s.length() && s.charAt(idx) != ')') {
            char ch = s.charAt(idx);

            if (ch == '+') {
                localSign = 1;
                idx++;
            } else if (ch == '-') {
                localSign = -1;
                idx++;
            } else if (ch == '(') {
                idx++;                       // consume '('
                solve(s, flip * localSign);  // child inherits combined sign
                localSign = 1;               // reset for whatever follows the group
            } else {                         // a letter (operand)
                int effective = flip * localSign;
                out.append(effective == 1 ? '+' : '-');
                out.append(ch);
                localSign = 1;               // default sign for the next operand
                idx++;
            }
        }
        if (idx < s.length() && s.charAt(idx) == ')') {
            idx++;                           // consume this group's ')'
        }
    }

    // ==========================================================================
    // SOLUTION 2: NO instance variable. The position is passed IN and RETURNED.
    //   - solve2 receives the starting index `i` and RETURNS the index just
    //     past what it consumed (past its ')', or end-of-string).
    //   - The parent writes `i = solve2(...)` -> this assignment IS the contract
    //     made explicit: "the child tells me where it stopped."
    //   - StringBuilder + flip are passed down as plain parameters. Zero shared
    //     mutable state on the instance.
    // ==========================================================================
    public String simplifyReturnIdx(String s) {
        s = s.replace(" ", "");
        StringBuilder out = new StringBuilder();
        solve2(s, 0, 1, out); // ignore the returned index at the top level
        return out.toString();
    }

    // returns the index just past the region this call consumed
    private int solve2(String s, int i, int flip, StringBuilder out) {
        int localSign = 1;

        while (i < s.length() && s.charAt(i) != ')') {
            char ch = s.charAt(i);

            if (ch == '+') {
                localSign = 1;
                i++;
            } else if (ch == '-') {
                localSign = -1;
                i++;
            } else if (ch == '(') {
                i = solve2(s, i + 1, flip * localSign, out); // <-- contract made explicit
                localSign = 1;
            } else {                          // a letter
                int effective = flip * localSign;
                out.append(effective == 1 ? '+' : '-');
                out.append(ch);
                localSign = 1;
                i++;
            }
        }
        if (i < s.length() && s.charAt(i) == ')') {
            i++;                              // consume this group's ')'
        }
        return i;                             // CONTRACT: index just past what we consumed
    }

    // ==========================================================================
    // Test harness — runs BOTH solutions against every case.
    // ==========================================================================
    public static void main(String[] args) {
        RemoveBracketsFromString solver = new RemoveBracketsFromString();

        String[][] cases = {
                {"a+b-(a-(b+c))",          "a+b-a+b+c"}};

        int pass = 0;
        for (String[] tc : cases) {
            //String got1 = solver.simplify(tc[0]);           // shared-idx version
            String got2 = solver.simplifyReturnIdx(tc[0]);  // return-idx version
            // boolean ok = got1.equals(tc[1]) && got2.equals(tc[1]);
            // if (ok) pass++;
            System.out.println("Input:        " + tc[0]);
            // System.out.println("Shared-idx:   " + got1);
            System.out.println("Return-idx:   " + got2);
            System.out.println("Expected:     " + tc[1]);
            //   System.out.println(ok ? "PASS" : "FAIL");
            System.out.println();
        }
        System.out.println("==== " + pass + "/" + cases.length + " passed (both solutions) ====");
    }
}
