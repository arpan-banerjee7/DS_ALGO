package google.recursion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/discuss/post/2328651/google-phone-interview-question-by-anony-xgf3/
https://leetcode.com/playground/WuABqN7x
https://leetcode.ca/2025-04-04-3481-Apply-Substitutions/
https://leetcode.com/discuss/post/4831503/google-questions-by-anonymous_user-jokp/

Given map {X=>123, Y=456}
Input: %X%_%Y%
Output: 123_456
Given map {USER=>admin, HOME=>/%USER%/home}
Input: I am %USER% My home is %HOME%
Output: I am admin My home is /admin/home
USER= bob
HOME= /home/%USER% should be substituted as : /home/bob ex2:
home/ %USER% -> /home/bob
Hello %USER%! -> Hello bob!
ex3:
The user %USER% is at 50%% -> The user bob is at 50%

Approach:
- Parse input char by char.
- On '%': if next char is also '%', emit literal '%' (escape).
         Otherwise, find matching closing '%', extract key, resolve it.
- resolveVar(key) looks up the key in the map, recursively calls substitute()
  on the value to expand nested references. Results are cached.
- Cycle detection via a "resolving" set.

Time:  O(T + L + S) — OPTIMAL
       T = input text length, L = total length of all map values, S = output length.
       Each value is resolved exactly once (cached), input is scanned once, output is written once.
Space: O(L + S) for resolved cache + output StringBuilder
 */
public class ApplySubstitution {

    private final Map<String, String> map;
    private final Map<String, String> resolved; // cache fully-resolved values
    private final Set<String> resolving;         // cycle detection

    public ApplySubstitution(Map<String, String> map) {
        this.map = map;
        this.resolved = new HashMap<>();
        this.resolving = new HashSet<>();
    }

    private String resolveVar(String key) {
        if (resolved.containsKey(key)) return resolved.get(key);
        if (resolving.contains(key)) {
            throw new IllegalArgumentException("Circular reference detected for key: " + key);
        }
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("Undefined variable: " + key);
        }

        resolving.add(key);
        String value = substitute(map.get(key)); // recursively expand the value
        resolving.remove(key);

        resolved.put(key, value);
        return value;
    }

    public String substitute(String input) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = input.length();

        while (i < n) {
            if (input.charAt(i) == '%') {
                // %% => literal '%'
                if (i + 1 < n && input.charAt(i + 1) == '%') {
                    sb.append('%');
                    i += 2;
                } else {
                    // find closing '%'
                    int j = input.indexOf('%', i + 1);
                    if (j == -1) {
                        throw new IllegalArgumentException(
                                "Unmatched '%' at position " + i + " in: " + input);
                    }
                    String key = input.substring(i + 1, j);
                    sb.append(resolveVar(key));
                    // sb.append(substitute(map.get(key));
                    i = j + 1;
                }
            } else {
                sb.append(input.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    // -------------------- driver / tests --------------------
    public static void main(String[] args) {

        Map<String, String> map_1 = new HashMap<>();
        map_1.put("USER", "%ADMIN%");
        map_1.put("ADMIN", "%USER%");
        // ApplySubstitution solver_1 = new ApplySubstitution(map_1);
        //  System.out.println(solver_1.substitute("%USER%"));
        // Test 1: simple substitution
        Map<String, String> map1 = new HashMap<>();
        map1.put("X", "123");
        map1.put("Y", "456");
        ApplySubstitution solver1 = new ApplySubstitution(map1);
        System.out.println(solver1.substitute("%X%_%Y%"));
        // Expected: 123_456

        // Test 2: recursive / nested substitution
        Map<String, String> map2 = new HashMap<>();
        map2.put("USER", "admin");
        map2.put("HOME", "/%USER%/home");
        ApplySubstitution solver2 = new ApplySubstitution(map2);
        System.out.println(solver2.substitute("I am %USER% My home is %HOME%"));
        // Expected: I am admin My home is /admin/home

        // Test 3: recursive + literal %%
        Map<String, String> map3 = new HashMap<>();
        map3.put("USER", "bob");
        map3.put("HOME", "/home/%USER%");
        ApplySubstitution solver3 = new ApplySubstitution(map3);
        System.out.println(solver3.substitute("%HOME%"));
        // Expected: /home/bob
        System.out.println(solver3.substitute("Hello %USER%!"));
        // Expected: Hello bob!
        System.out.println(solver3.substitute("The user %USER% is at 50%%"));
        // Expected: The user bob is at 50%
    }
}
