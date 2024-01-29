package intuit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDistinctPairs {
    private static void findDistinctPairs(int[] input, int idx, int k, Set<List<Integer>> res, List<Integer> temp) {

        if (k == 0) {
            res.add(new ArrayList<>(temp));
        }
        if (k > input.length) {
            return;
        }

        for (int i = idx; i < input.length; i++) {
            temp.add(input[i]);
            findDistinctPairs(input, i + 1, k - 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] input = {2, 3, 4};
        int k = 2;
        Set<List<Integer>> res = new HashSet<>();
        findDistinctPairs(input, 0, k, res, new ArrayList<Integer>());
        System.out.println(res);
    }
}
