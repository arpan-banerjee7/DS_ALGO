package google;

import java.util.*;

/*
The Sum of Sum of Good Subarrays An arithmetic sequence is a list of numbers with a definite pattern. If you take any number in the sequence then subtract it from the previous one, the difference is always a constant. A good arithmetic sequence is an arithmetic sequence with a common difference of either 1 or -1. - For example, [4, 5, 6] is a good arithmetic sequence. So is [6, 5, 4], [10, 9], or [-3, -2, -1]. But, [1, 2, 1] (no common difference) or [3, 7] (common difference is 4) is NOT. Implied, any sequence that has only one element is a good arithmetic sequence. - For example, [4] is a good arithmetic sequence.
Given an integer array nums, return the sum of the sums of each subarray that is a good arithmetic sequence.
Example: Given nums = [7, 4, 5, 6, 5]. Each of the following subarrays is a good arithmetic sequence: [7], [4], [5], [6], [5], [4, 5], [5, 6], [6, 5], [4, 5, 6] The sums of these subarrays are: 7, 4, 5, 6, 5, 4 + 5 = 9, 5 + 6 = 11, 6 + 5 = 11, 4 + 5 + 6 = 15 Thus, the answer is the sum of all the sums above, which is: 7 + 4 + 5 + 6 + 5 + 9 + 11 + 11 + 15 = 73
 */
/*
The problem can be approached by considering each element's contribution to the total sum based on the number of subarrays it is part of, satisfying the given condition.

Key Concepts:

Element Contribution: Each element contributes to multiple subarrays. We can calculate its total contribution based on the number of valid subarrays it participates in.
Stack-Based Approach: Using a stack to efficiently find the boundaries (leftBad and rightBad) where the condition breaks for each element.
Handling Two Cases: Since the common difference can be either 1 or -1, we need to consider both cases separately.
 */

public class SumOfGoodSubarrays {
    private static int solve(int[] nums, int d) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] leftBad = new int[n];
        int[] rightBad = new int[n];
        int res = 0;

        // Calculate leftBad and rightBad
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] - nums[i - 1] != d) {
                // Clear the stack and set rightBad for all elements in the stack
                while (!stack.isEmpty()) {
                    rightBad[stack.pop()] = i;
                }
                leftBad[i] = i - 1;
            } else {
                // Set leftBad for the current element
                leftBad[i] = !stack.isEmpty() ? leftBad[stack.peek()] : -1;
            }
            stack.push(i);
        }

        // Handle remaining elements in the stack
        while (!stack.isEmpty()) {
            rightBad[stack.pop()] = n;
        }

        // Calculate the result using the formula
        for (int i = 0; i < n; i++) {
            int l = leftBad[i];
            int r = rightBad[i];
            int x = (i - l) * (r - i) * nums[i] - nums[i];
            res += x;
        }

        return res;
    }

    private static int sumOfGoodArithmeticSubarraySums(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int a = solve(nums, 1);  // Solve for d = 1
        int b = solve(nums, -1); // Solve for d = -1

        return a + b + sum; // Add the single element contribution
    }

    public static void main(String[] args) {
//        int[] nums1 = {7, 4, 5, 6, 5};
//        System.out.println("Sum of sums: " + sumOfGoodArithmeticSubarraySums(nums1)); // Output: 73
//
//        int[] nums2 = {5, 6, 7, 8, 9};
//        System.out.println("Sum of sums: " + sumOfGoodArithmeticSubarraySums(nums2)); // Output: 245

        int[] nums3 = {1, 2, 1};
        System.out.println("Sum of sums: " + sumOfGoodArithmeticSubarraySums(nums3)); // Output: 10
    }
}
