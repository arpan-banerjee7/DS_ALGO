package linkedin;

import java.util.*;
// 364. Nested List Weight Sum II
// 339. Nested List Weight Sum


interface NestedInteger {
    // Returns true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // Returns the single integer that this NestedInteger holds, if it holds a single integer.
    // Returns null if this NestedInteger holds a nested list.
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a NestedInteger to it.
    public void add(NestedInteger ni);

    // Returns the nested list that this NestedInteger holds, if it holds a nested list.
    // Returns empty list if this NestedInteger holds a single integer.
    public List<NestedInteger> getList();
}

class NestedIntegerImpl implements NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    public NestedIntegerImpl() {
        // Start as an empty list
        this.list = new ArrayList<>();
    }

    public NestedIntegerImpl(int value) {
        // Start as a single integer
        this.value = value;
    }

    @Override
    public boolean isInteger() {
        return value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public void setInteger(int value) {
        this.value = value;
    }

    @Override
    public void add(NestedInteger ni) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(ni);
    }

    @Override
    public List<NestedInteger> getList() {
        return list == null ? new ArrayList<>() : list;
    }
}

public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int total = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                total += nested.getInteger() * depth;
            } else {
                total += dfs(nested.getList(), depth + 1);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        // Constructing the nested list [1,[2,[3,4]]]
        // Top-level is a list containing:
        //  1) An integer 1
        //  2) A Nested list [2,[3,4]]

        NestedIntegerImpl topLevel1 = new NestedIntegerImpl(1); // single integer 1

        // Construct [2,[3,4]]
        NestedIntegerImpl innerList = new NestedIntegerImpl();
        // add 2
        innerList.add(new NestedIntegerImpl(2));
        // add [3,4]
        NestedIntegerImpl innerInnerList = new NestedIntegerImpl();
        innerInnerList.add(new NestedIntegerImpl(3));
        innerInnerList.add(new NestedIntegerImpl(4));
        innerList.add(innerInnerList);

        // Create the top-level list
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(topLevel1);
        nestedList.add(innerList);

        // Calculate depth sum
        NestedListWeightSum solution = new NestedListWeightSum();
        int result = solution.depthSum(nestedList);
        System.out.println("Result: " + result); // Expected: 14
    }
}









/* FOLLOW UP QUESTION */

// Time complexity: O(N)
class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = findMaxDepth(nestedList);
        return weightedSum(nestedList, 1, maxDepth);
    }

    private int findMaxDepth(List<NestedInteger> list) {
        int maxDepth = 1;

        for (NestedInteger nested : list) {
            if (!nested.isInteger() && nested.getList().size() > 0) {
                maxDepth = Math.max(maxDepth, 1 + findMaxDepth(nested.getList()));
            }
        }

        return maxDepth;
    }

    private int weightedSum(List<NestedInteger> list, int depth, int maxDepth) {
        int answer = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                answer += nested.getInteger() * (maxDepth - depth + 1);
            } else {
                answer += weightedSum(nested.getList(), depth + 1, maxDepth);
            }
        }
        return answer;
    }
}

