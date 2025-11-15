/*https://leetcode.com/problems/zero-array-transformation-ii/?envType=problem-list-v2&envId=2fdm7o03
Intuition
We need to find the smallest number of queries that will turn the array nums into a Zero Array. Each query allows us to decrease elements in a specific range. 
Instead of applying the queries one by one, we can use binary search combined with a line sweeping technique to efficiently solve the problem.

Approach
Binary Search:
We search for the smallest number k such that applying the first k queries will make nums zero.
Line Sweeping:
Instead of applying each query directly, we use a difference array (line sweeping) to mark the range updates.
For a query [start, end, val], increment the start index arr[start] by val and decrement the index arr[end + 1] by val. This marks the range of decrement.
Check Validity:
After applying the range updates, check if all elements in nums can be reduced to zero.
Return Result:
If no valid k exists, return -1.
Complexity
Time complexity: O(n∗log(m)), where n is the size of nums and m is the number of queries.

Space complexity: O(n) for the difference array.
*/
public class ZeroArrayTransformation2 {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;

        // first check if an answer is at all possible, if so then try for min ans
        // if (!check(nums, queries, queries.length-1)) return -1;
        boolean allZero = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                allZero = false;
            }
        }
        if (allZero)
            return 0;

        int l = 0, r = queries.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (check(nums, queries, m)) {
                // if we can make all the elements 0 by selecting m elements, we will still try
                // to reduce it further
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l + 1 > queries.length ? -1 : l + 1;
    }

    private boolean check(int[] nums, int[][] queries, int m) {
        int n = nums.length;
        int[] arr = new int[n + 1];

        for (int i = 0; i <= m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            int val = queries[i][2];
            arr[start] += val;
            if (end + 1 < n)
                arr[end + 1] -= val;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum < nums[i])
                return false;
        }
        return true;
    }

    // Example runner
    public static void main(String[] args) {
        ZeroArrayTransformation2 solver = new ZeroArrayTransformation2();
        int[] nums = { 2, 0, 2 };
        int[][] queries = { { 0, 2, 1 }, { 0, 2, 1 }, { 1, 1, 3 } };

        int result = solver.minZeroArray(nums, queries);
        System.out.println(result);
        // Expected output: 2
    }

}
