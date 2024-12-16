package google;

import java.util.*;

public class GreaterThanPairs {
    static class Result {
        boolean isPossible;
        Integer index;
        List<Character> res;

        public Result(boolean isPossible, Integer index, List<Character> res) {
            this.isPossible = isPossible;
            this.index = index;
            this.res = res;
        }
    }

    private boolean findTopoSortBfs(List<List<Character>> pairs, List<Character> res, int m, int totalNodes) {

        // build graph taking m elements at a time from the given pairs
        Map<Character, List<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        Set<Character> uniqueNodes = new HashSet<>();
        for (int i = 0; i <= m; i++) {
            uniqueNodes.add(pairs.get(i).get(0));
            uniqueNodes.add(pairs.get(i).get(1));
            adj.computeIfAbsent(pairs.get(i).get(0), k -> new ArrayList<>()).add(pairs.get(i).get(1));
            indegree.put(pairs.get(i).get(1), indegree.getOrDefault(pairs.get(i).get(1), 0) + 1);
        }

        // setting default indegree values for other nodes
        for (Character ch : uniqueNodes) {
            if (!indegree.containsKey(ch)) {
                indegree.put(ch, 0);
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        res.clear();
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            Character parent = queue.poll();
            res.add(parent);
            visitedCount++;
            if (adj.get(parent) != null) {
                for (Character child : adj.get(parent)) {
                    indegree.put(child, indegree.get(child) - 1);
                    if (indegree.get(child) == 0) {
                        queue.add(child);
                    }
                }
            }
        }
        return visitedCount == totalNodes;
    }

    private boolean isTopologicalOrderUnique(List<Character> order, Map<Character, List<Character>> adj) {
        // Create a set for quick edge existence checking
        Set<String> edges = new HashSet<>();
        for (Map.Entry<Character, List<Character>> entry : adj.entrySet()) {
            char from = entry.getKey();
            for (char to : entry.getValue()) {
                edges.add(from + "#" + to);
            }
        }

        // Check for direct edges between consecutive nodes
        for (int i = 0; i < order.size() - 1; i++) {
            char from = order.get(i);
            char to = order.get(i + 1);
            if (!edges.contains(from + "#" + to)) {
                return false; // Multiple topological orders possible
            }
        }
        return true; // Unique topological order
    }

    public Result findVariableOrder(List<List<Character>> pairs) {
        List<Character> res = new ArrayList<>();

        Set<Character> totalNodes = new HashSet<>();
        for (int i = 0; i < pairs.size(); i++) {
            totalNodes.add(pairs.get(i).get(0));
            totalNodes.add(pairs.get(i).get(1));
        }
        if (!findTopoSortBfs(pairs, res, pairs.size() - 1, totalNodes.size())) {
            return new Result(false, null, null);
        }

        // Build the full adjacency list for edge checking
        Map<Character, List<Character>> fullAdj = new HashMap<>();
        for (List<Character> pair : pairs) {
            char from = pair.get(0);
            char to = pair.get(1);
            fullAdj.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }

        if (!isTopologicalOrderUnique(res, fullAdj)) {
            return new Result(true, null, res);
        }

        // if we can get a valid topo sort, then we will try to find the min index which can give us a valid topo sort
        // for this we will use binary search
        int low = 0;
        int high = pairs.size() - 1;
        int ans = 0;
        List<Character> finalRes = null;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (findTopoSortBfs(pairs, res, mid, totalNodes.size())) {
                finalRes = new ArrayList<>(res);
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        Collections.reverse(finalRes);
        return new Result(true, ans, finalRes);
    }


    public static void main(String[] args) {
        GreaterThanPairs greaterThanPairs = new GreaterThanPairs();
        //1 Input: Random
        List<List<Character>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList('a', 'c'));
        pairs.add(Arrays.asList('a', 'b'));
        pairs.add(Arrays.asList('b', 'c'));


        Result result = greaterThanPairs.findVariableOrder(pairs);
        System.out.print(result.isPossible + ", ");
        System.out.print(result.index + ", ");
        System.out.println(result.res);

        //2 Input: Unique
        List<List<Character>> pairs2 = new ArrayList<>();
        pairs2.add(Arrays.asList('a', 'b'));
        pairs2.add(Arrays.asList('b', 'c'));
        pairs2.add(Arrays.asList('c', 'd'));
        pairs2.add(Arrays.asList('a', 'd'));
        pairs2.add(Arrays.asList('b', 'd'));

        Result result2 = greaterThanPairs.findVariableOrder(pairs2);
        System.out.print(result2.isPossible + ", ");
        System.out.print(result2.index + ", ");
        System.out.println(result2.res);

        //3 Input: Unique
        List<List<Character>> pairs3 = new ArrayList<>();
        pairs3.add(Arrays.asList('a', 'b'));
        pairs3.add(Arrays.asList('b', 'c'));

        Result result3 = greaterThanPairs.findVariableOrder(pairs3);
        System.out.print(result3.isPossible + ", ");
        System.out.print(result3.index + ", ");
        System.out.println(result3.res);

        //4 Input: Non Unique
        List<List<Character>> pairs4 = new ArrayList<>();
        pairs4.add(Arrays.asList('a', 'b'));
        pairs4.add(Arrays.asList('a', 'c'));

        Result result4 = greaterThanPairs.findVariableOrder(pairs4);
        System.out.print(result4.isPossible + ", ");
        System.out.print(result4.index + ", ");
        System.out.println(result4.res);


        //5 Input: Negative case
        List<List<Character>> pairs5 = new ArrayList<>();
        pairs5.add(Arrays.asList('a', 'b'));
        pairs5.add(Arrays.asList('c', 'a'));
        pairs5.add(Arrays.asList('b', 'c'));

        Result result5 = greaterThanPairs.findVariableOrder(pairs5);
        System.out.print(result5.isPossible + ", ");
        System.out.print(result5.index + ", ");
        System.out.println(result5.res);

    }
}


