package google;


import java.util.*;

/*Given an array of distinct elements, you can choose any subset from the array and reorder them.
        You can place following operators between them: +, -, *, /, (, ) and evaluate the value of the expression.
        Your task is to find the minimum positive number which cannot be formed using the array elements.
        You can use the elements of an array only once for an expression.*/
public class FindSubsetMinPosEvaluate {

    private void helper(int[] a, Set<Integer> res) {
        if (a.length == 1) {
            res.add(a[0]);
            return;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int[] d = new int[a.length - 1];

                for (int k = 0, index = 0; k < a.length; k++) {
                    if (k != i && k != j) {
                        d[index++] = a[k];
                    }
                }
                // Perform and store results of operations
                List<Integer> results = new ArrayList<>(Arrays.asList(a[i] * a[j], a[i] + a[j], a[i] - a[j], a[j] - a[i]));
                if (a[j] != 0) results.add(a[i] / a[j]);
                if (a[i] != 0) results.add(a[j] / a[i]);

                for (int num : results) {
                    d[d.length - 1] = num;
                    helper(d, res);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {1, 2, 3};

        FindSubsetMinPosEvaluate solution = new FindSubsetMinPosEvaluate();
        Set<Integer> res = new HashSet<>();
        solution.helper(nums1, res);
        for (int i = 1; i <= 6561; i++) {
            if (!res.contains(i)) {
                System.out.println(i);// Output: 4
                break;
            }
        }
        Set<Integer> res2 = new HashSet<>();
        solution.helper(nums2, res2);
        for (int i = 1; i <= 6561; i++) {
            if (!res2.contains(i)) {
                System.out.println(i);// Output: 10
                break;
            }
        }
    }
}