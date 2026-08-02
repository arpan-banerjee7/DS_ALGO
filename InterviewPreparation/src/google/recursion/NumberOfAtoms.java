package google.recursion;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class NumberOfAtoms {

    // -------------------- Solution 1: Iterative Stack of Maps O(n·d·log a) --------------------
    // Total: O(n · d · log a) where n = formula length, d = max nesting depth, a = distinct atoms
    public String countOfAtoms(String formula) {
        Stack<Map<String,Integer>> st = new Stack<>();
        st.push(new TreeMap<>());
        int n = formula.length();
        int i = 0;

        while(i < n){
            char ch = formula.charAt(i);
            if(ch == '('){
                // push a new scope for the group
                st.push(new TreeMap<>());
                i++;
            }else if(ch == ')'){
                i++;
                // parse the multiplier after ')'
                int num = 0;
                while(i < n && Character.isDigit(formula.charAt(i))){
                    num = num * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                if(num == 0) num = 1;
                // pop current scope and merge into parent scope
                Map<String,Integer> top = st.pop();
                for(Map.Entry<String,Integer> entry : top.entrySet()){
                    st.peek().merge(entry.getKey(), entry.getValue() * num, Integer::sum);
                }
            }else{
                // parse atom name: starts with uppercase, followed by lowercase
                StringBuilder atom = new StringBuilder();
                atom.append(ch);
                i++;
                while(i < n && Character.isLowerCase(formula.charAt(i))){
                    atom.append(formula.charAt(i));
                    i++;
                }
                // parse count
                int num = 0;
                while(i < n && Character.isDigit(formula.charAt(i))){
                    num = num * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                if(num == 0) num = 1;
                st.peek().merge(atom.toString(), num, Integer::sum);
            }
        }

        // build sorted result from the remaining map
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Integer> entry : st.peek().entrySet()){
            sb.append(entry.getKey());
            if(entry.getValue() > 1){
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    // -------------------- Solution 2: Recursive descent O(n·d·log a) --------------------
    private int idx;

    public String countOfAtomsRecursive(String formula) {
        idx = 0;
        Map<String,Integer> map = parse(formula);
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            sb.append(entry.getKey());
            if(entry.getValue() > 1){
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    private TreeMap<String,Integer> parse(String formula) {
        TreeMap<String,Integer> map = new TreeMap<>();
        int n = formula.length();
        while(idx < n){
            char ch = formula.charAt(idx);
            if(ch == '('){
                idx++;
                // recurse into the group
                TreeMap<String,Integer> inner = parse(formula);
                // parse the multiplier after ')'
                int num = 0;
                while(idx < n && Character.isDigit(formula.charAt(idx))){
                    num = num * 10 + (formula.charAt(idx) - '0');
                    idx++;
                }
                if(num == 0) num = 1;
                for(Map.Entry<String,Integer> entry : inner.entrySet()){
                    map.merge(entry.getKey(), entry.getValue() * num, Integer::sum);
                }
            }else if(ch == ')'){
                idx++;
                return map; // return to parent call
            }else{
                // parse atom name: starts with uppercase, followed by lowercase
                StringBuilder atom = new StringBuilder();
                atom.append(ch);
                idx++;
                while(idx < n && Character.isLowerCase(formula.charAt(idx))){
                    atom.append(formula.charAt(idx));
                    idx++;
                }
                // parse count
                int num = 0;
                while(idx < n && Character.isDigit(formula.charAt(idx))){
                    num = num * 10 + (formula.charAt(idx) - '0');
                    idx++;
                }
                if(num == 0) num = 1;
                map.merge(atom.toString(), num, Integer::sum);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        NumberOfAtoms solver = new NumberOfAtoms();

        String[] inputs = { "K4(ON(SO3)2)2"};
        String[] expected = {"H2O", "H2MgO2", "K4N2O14S4", "O", "H2He3Mg4O2", "H2O", "N42"};

        System.out.println("--- Solution 1: Iterative Stack ---");
        for(int i = 0; i < inputs.length; i++){
            String res = solver.countOfAtoms(inputs[i]);
            System.out.println("Test " + (i+1) + ": " + res + " | Expected: " + expected[i] + " | " + (res.equals(expected[i]) ? "PASS" : "FAIL"));
        }

        System.out.println("\n--- Solution 2: Recursive Descent ---");
        for(int i = 0; i < inputs.length; i++){
            String res = solver.countOfAtomsRecursive(inputs[i]);
            System.out.println("Test " + (i+1) + ": " + res + " | Expected: " + expected[i] + " | " + (res.equals(expected[i]) ? "PASS" : "FAIL"));
        }
    }
}
