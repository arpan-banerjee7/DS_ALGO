package google;

import java.util.*;

public class EvaluateExpressionAndResolve {

    public static String simplify(String expression) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);  // Initially we are in the "positive" scope
        int sign = 1;   // This tracks the current sign (+1 for positive, -1 for negative)

        StringBuilder result = new StringBuilder();
        int n = expression.length();

        for (int i = 0; i < n; i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue;  // Skip whitespace
            }

            if (ch == '+') {
                sign = stack.peek();
            } else if (ch == '-') {
                sign = -stack.peek();
            } else if (ch == '(') {
                // Push the current sign onto the stack
                stack.push(sign);
            } else if (ch == ')') {
                // Pop the last sign from the stack
                stack.pop();
            } else if (Character.isLetter(ch)) {
                // If it's a variable, append it with the current sign
                if (sign == 1) {
                    result.append('+').append(ch);
                } else {
                    result.append('-').append(ch);
                }
            }
        }

        // After building the expression, we need to simplify terms like +a and -a
        return simplifyResult(result.toString());
    }

    private static String simplifyResult(String result) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < result.length(); i++) {
            char sign = result.charAt(i);
            char variable = result.charAt(++i);  // Next character is the variable

            // Update the count of variables (+1 for '+' sign, -1 for '-' sign)
            countMap.put(variable, countMap.getOrDefault(variable, 0) + (sign == '+' ? 1 : -1));
        }

        // Build the final result
        StringBuilder finalResult = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char variable = entry.getKey();
            int count = entry.getValue();
            if (count > 0) {
                finalResult.append("+").append(variable);
            } else if (count < 0) {
                finalResult.append("-").append(variable);
            }
        }

        // Remove the leading '+' if it exists
        if (finalResult.length() > 0 && finalResult.charAt(0) == '+') {
            return finalResult.substring(1);
        }

        return finalResult.toString();
    }

    public static void main(String[] args) {
        String expression1 = "(a-(b+c-(c+d))+d)";//(a-(b+c-c-d)+d) // (a-b-c+c+d+d)

        System.out.println(simplify(expression1));  // Output: "a-b-c-c"

    }
}


