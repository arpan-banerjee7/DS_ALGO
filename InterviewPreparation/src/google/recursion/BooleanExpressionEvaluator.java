package google.recursion;

import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/parsing-a-boolean-expression/

public class BooleanExpressionEvaluator {

    private int idx;

    public boolean parseBoolExpr(String expression) {
        idx = 0;
        return solve(expression);
    }

    // INVARIANT: solve() always leaves idx pointing JUST PAST the expression
    // it parsed (past the value, or past the operator's closing ')').
    private boolean solve(String s) {
        char ch = s.charAt(idx);

        // base case: a lone value -> consume it
        if (ch == 't') { idx++; return true; }
        if (ch == 'f') { idx++; return false; }

        // operator: '!', '&', or '|'
        idx += 2; // skip the operator AND the '('

        List<Boolean> args = new ArrayList<>();
        while (s.charAt(idx) != ')') {        // stop cleanly at this group's ')'
            args.add(solve(s));               // solve leaves idx right after the sub-expr
            if (s.charAt(idx) == ',') idx++;  // skip a separator only if present
        }
        idx++; // consume this group's ')'  (maintains the invariant)

        if (ch == '!') return !args.get(0);
        if (ch == '&') return !args.contains(false); // AND: false if any false
        return args.contains(true);                   // OR : true if any true
    }

    public static void main(String[] args) {
        BooleanExpressionEvaluator solver = new BooleanExpressionEvaluator();

        String[] inputs = {
                "&(!(f),!(f))"
        };
        boolean[] expected = {true, false, true, false, true, true, false, true, true};

        for (int i = 0; i < inputs.length; i++) {
            boolean res = solver.parseBoolExpr(inputs[i]);
            System.out.println("\"" + inputs[i] + "\" = " + res + " | Expected: " + expected[i] + " | " + (res == expected[i] ? "PASS" : "FAIL"));
        }
    }
}
