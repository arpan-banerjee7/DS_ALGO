package google;

import java.util.*;

public class SubarrayLargestMinMax {
    public static int findMaxMinPlusMax(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0; // Since we need subarrays with more than one number
        }

        // Create a list of pairs (value, index)
        List<int[]> valueIndexList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            valueIndexList.add(new int[]{nums[i], i});
        }

        // Sort the list in decreasing order of values
        valueIndexList.sort((a, b) -> Integer.compare(b[0], a[0]));

        int maxSum = 0;
        Set<Integer> processedIndices = new HashSet<>();

        int value = valueIndexList.get(0)[0];
        int index = valueIndexList.get(0)[1];

        // Check left neighbor
        if (index - 1 >= 0 && !processedIndices.contains(index - 1)) {
            int neighborValue = nums[index - 1];
            int minVal = Math.min(value, neighborValue);
            int maxVal = Math.max(value, neighborValue);
            int sum = minVal + maxVal;
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        // Check right neighbor
        if (index + 1 < n && !processedIndices.contains(index + 1)) {
            int neighborValue = nums[index + 1];
            int minVal = Math.min(value, neighborValue);
            int maxVal = Math.max(value, neighborValue);
            int sum = minVal + maxVal;
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        // Mark the current index as processed
        processedIndices.add(index);


        return maxSum;
    }


    public static void main(String[] args) {
        int[] nums1 = {4, 6, 2, 8, 10};
        System.out.println("Maximum min + max: " + findMaxMinPlusMax(nums1)); // Output: 18

        int[] nums2 = { 6, 2, 9, 1, 7};
        System.out.println("Maximum min + max: " + findMaxMinPlusMax(nums2)); // Output: 11

        int[] nums3 = {1, 5, 3, 4, 2};
        System.out.println("Maximum min + max: " + findMaxMinPlusMax(nums3)); // Output: 8
        int[] nums4={1, 2, 100, 2, 1, 200, 1, 2};
        System.out.println("Maximum min + max: " + findMaxMinPlusMax(nums4)); // Output: 201
    }
}


