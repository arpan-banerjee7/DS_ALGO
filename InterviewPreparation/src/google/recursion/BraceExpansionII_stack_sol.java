package google.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/*
LC 1096: Brace Expansion II — ITERATIVE, two-stack (NO recursion).

Idea: it's operator-precedence expression evaluation where the OPERANDS are
Set<String>:
    ','        -> '+'  = UNION       (precedence 1, lower)
    adjacency  -> '*'  = PRODUCT     (precedence 2, higher)  [implicit operator]
    '{' '}'    -> '(' ')'            (precedence 0, a barrier)

This is the shunting-yard / Basic Calculator pattern:
  - vals : stack of Set<String> operands
  - ops  : stack of operators ('+', '*', '{')
  - when pushing an operator, first resolve all ops with >= precedence
  - '}' resolves everything back to the matching '{'

The only subtlety vs a normal calculator is INSERTING the implicit '*' between
adjacent factors. We track `prevWasFactor`: if the previous thing produced a
value (a letter, or a closed '}'), and the next thing starts a factor (a letter
or '{'), we inject a '*' first.
*/
public class BraceExpansionIIStack {

    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();
        boolean prevWasFactor = false; // did the previous token produce a value?

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '{') {
                if (prevWasFactor) pushOperator(vals, ops, '*'); // adjacency before a group
                ops.push('{');
                prevWasFactor = false;
            } else if (c == '}') {
                while (ops.peek() != '{') applyTop(vals, ops);
                ops.pop();                 // discard the '{'
                prevWasFactor = true;      // the group produced a value
            } else if (c == ',') {
                pushOperator(vals, ops, '+');
                prevWasFactor = false;
            } else {                       // a lowercase letter = a single-word factor
                if (prevWasFactor) pushOperator(vals, ops, '*'); // adjacency before a letter
                Set<String> leaf = new HashSet<>();
                leaf.add(String.valueOf(c));
                vals.push(leaf);
                prevWasFactor = true;
            }
        }
        while (!ops.isEmpty()) applyTop(vals, ops);

        return new ArrayList<>(new TreeSet<>(vals.pop())); // sorted + de-duped
    }

    // resolve all stacked operators whose precedence >= the incoming op, then push it
    private void pushOperator(Stack<Set<String>> vals, Stack<Character> ops, char op) {
        while (!ops.isEmpty() && prec(ops.peek()) >= prec(op)) {
            applyTop(vals, ops);
        }
        ops.push(op);
    }

    private void applyTop(Stack<Set<String>> vals, Stack<Character> ops) {
        char op = ops.pop();
        Set<String> b = vals.pop();
        Set<String> a = vals.pop();
        if (op == '*') {
            vals.push(product(a, b)); // concatenation
        } else {                       // '+'
            a.addAll(b);               // union
            vals.push(a);
        }
    }

    private int prec(char op) {
        if (op == '*') return 2;
        if (op == '+') return 1;
        return 0; // '{' acts as a barrier
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

    // ==========================================================================
    // Test harness (same cases as the recursive versions).
    // ==========================================================================
    public static void main(String[] args) {
        BraceExpansionIIStack solver = new BraceExpansionIIStack();

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
