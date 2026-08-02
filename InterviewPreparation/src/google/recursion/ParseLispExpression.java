package google.recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC 736: Parse Lisp Expression

An expression is one of:
  1. an integer           -> e.g. "12", "-3"
  2. a variable           -> e.g. "x", "y2"  (look up in scope)
  3. (add e1 e2)          -> eval(e1) + eval(e2)
  4. (mult e1 e2)         -> eval(e1) * eval(e2)
  5. (let v1 e1 ... vn en expr) -> assign sequentially, then eval(expr)

Scope rule: innermost parentheses checked first, then outer.
We model this by passing a COPY of the parent scope into each let,
so assignments inside a let don't leak out, and inner names shadow outer.

Approach: recursive descent.
- If no '(' -> it's an integer or a variable (base case).
- Else strip outer '()', tokenize the inside at TOP-LEVEL spaces only
  (respecting nested parentheses), then branch on the keyword.

Time:  O(n) per level of nesting for tokenize/substring -> O(n * depth) overall.
Space: O(n * depth) for scope copies + recursion stack.
*/

// Recursive solution
/*
1. integer        -> parse it
2. variable       -> scope.get(it)
3. (add e1 e2)    -> eval(e1) + eval(e2)
4. (mult e1 e2)   -> eval(e1) * eval(e2)
5. (let v1 e1 ... vn en body) -> bind pairs, then eval(body)
 */
public class ParseLispExpression {

    // split by spaces that are NOT inside nested parentheses
    private List<String> tokenize(String s) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (c == ' ' && balance == 0) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) tokens.add(sb.toString());
        return tokens;
    }

    private int eval(String expr, Map<String, Integer> scope) {
        // base case: not a compound expression -> integer or variable
        if (expr.charAt(0) != '(') {
            char first = expr.charAt(0);
            if (Character.isDigit(first) || first == '-') {
                return Integer.parseInt(expr);
            }
            return scope.get(expr); // variable lookup
        }

        // strip the outer parentheses and split into top-level tokens
        String inner = expr.substring(1, expr.length() - 1);
        List<String> tokens = tokenize(inner);
        String op = tokens.get(0);

        if (op.equals("add")) {
            return eval(tokens.get(1), scope) + eval(tokens.get(2), scope);
        } else if (op.equals("mult")) {
            return eval(tokens.get(1), scope) * eval(tokens.get(2), scope);
        } else {
            // let: copy parent scope so inner assignments shadow, don't leak
            Map<String, Integer> newScope = new HashMap<>(scope);
            // process (var, value) pairs sequentially; last token is the final expr
            for (int i = 1; i + 1 < tokens.size(); i += 2) {
                String var = tokens.get(i);
                int val = eval(tokens.get(i + 1), newScope);
                newScope.put(var, val);
            }
            return eval(tokens.get(tokens.size() - 1), newScope);
        }
    }

    public int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }


    // ==================================================================
    // Solution 2: ITERATIVE with stacks (no recursion)
    //
    // - scope:  Map<var, Deque<value>>  -> top of each deque = current value
    //           push on bind, pop when the defining let scope closes.
    // - frames: Deque<Frame>            -> one frame per open '('
    //           holds the operator, collected operands, and the list of
    //           vars it bound (so we know what to pop at ')').
    //
    // The tricky bit (vs BasicCalculator III): a let must BIND its vars
    // before its body is parsed, so we bind incrementally during the
    // forward scan and use 1-token lookahead to tell a var-name apart
    // from the body.
    // ==================================================================
    private static class Frame {
        String op;                          // "add", "mult", or "let"
        List<Integer> operands = new ArrayList<>(); // for add / mult
        List<String> boundVars = new ArrayList<>(); // vars bound by this let
        String pendingVar = null;           // let: var awaiting its value
        int body = 0;                       // let: the final body value

        int compute() {
            if (op.equals("add")) return operands.get(0) + operands.get(1);
            if (op.equals("mult")) return operands.get(0) * operands.get(1);
            return body; // let -> value of its body
        }
    }

    public int evaluateIterative(String expression) {
        List<String> tokens = tokenizeAll(expression);
        // top-level bare integer (no parentheses)
        if (!tokens.get(0).equals("(")) {
            return Integer.parseInt(tokens.get(0));
        }

        Deque<Frame> frames = new ArrayDeque<>();
        Map<String, Deque<Integer>> scope = new HashMap<>();
        int finalResult = 0;

        for (int i = 0; i < tokens.size(); i++) {
            String tok = tokens.get(i);

            if (tok.equals("(")) {
                Frame f = new Frame();
                f.op = tokens.get(++i); // operator always follows '('
                frames.push(f);
            } else if (tok.equals(")")) {
                Frame f = frames.pop();
                int result = f.compute();
                // closing a let scope: pop all vars it bound
                for (String v : f.boundVars) {
                    scope.get(v).pop();
                }
                if (frames.isEmpty()) finalResult = result;
                else feed(frames.peek(), scope, result);
            } else {
                Frame f = frames.peek();
                // is this a NEW let variable name? (lookahead: a var is followed
                // by its value expr, the body is followed by ')')
                if (f.op.equals("let") && f.pendingVar == null
                        && isVarName(tok) && !tokens.get(i + 1).equals(")")) {
                    f.pendingVar = tok;
                } else {
                    // a value: number or variable reference
                    int val = isNumber(tok) ? Integer.parseInt(tok) : scope.get(tok).peek();
                    feed(f, scope, val);
                }
            }
        }
        return finalResult;
    }

    // feed a resolved value into the current frame
    private void feed(Frame f, Map<String, Deque<Integer>> scope, int value) {
        if (f.op.equals("let")) {
            if (f.pendingVar != null) {
                scope.computeIfAbsent(f.pendingVar, k -> new ArrayDeque<>()).push(value);
                f.boundVars.add(f.pendingVar);
                f.pendingVar = null;
            } else {
                f.body = value; // it's the let body
            }
        } else {
            f.operands.add(value); // add / mult operand
        }
    }

    private boolean isNumber(String tok) {
        char c = tok.charAt(0);
        return Character.isDigit(c) || c == '-';
    }

    private boolean isVarName(String tok) {
        return Character.isLowerCase(tok.charAt(0));
    }

    // tokenize whole expression: '(' and ')' as separate tokens, rest split by spaces
    private List<String> tokenizeAll(String s) {
        List<String> tokens = new ArrayList<>();
        int i = 0, n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
                i++;
            } else {
                int j = i;
                while (j < n && s.charAt(j) != ' ' && s.charAt(j) != '(' && s.charAt(j) != ')') {
                    j++;
                }
                tokens.add(s.substring(i, j));
                i = j;
            }
        }
        return tokens;
    }


    public static void main(String[] args) {
        ParseLispExpression solver = new ParseLispExpression();

        String[] inputs = {
                "(let x 2 (mult x (let x 3 y 4 (add x y))))",
        };
        int[] expected = {14, 2, 5, 3, 15, -12, 4};

        System.out.println("--- Solution 1: Recursive ---");
        for (int i = 0; i < inputs.length; i++) {
            int res = solver.evaluate(inputs[i]);
            System.out.println("\"" + inputs[i] + "\" = " + res + " | Expected: " + expected[i] + " | " + (res == expected[i] ? "PASS" : "FAIL"));
        }

        System.out.println("\n--- Solution 2: Iterative (stacks) ---");
        for (int i = 0; i < inputs.length; i++) {
            int res = solver.evaluateIterative(inputs[i]);
            System.out.println("\"" + inputs[i] + "\" = " + res + " | Expected: " + expected[i] + " | " + (res == expected[i] ? "PASS" : "FAIL"));
        }
    }
}
