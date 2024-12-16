package google;

import java.util.*;

public class FIndDuplicatesInArray {


    public static List<Integer> findDuplicates(int[] nums) {
        // Step 1: Sort the array using Radix Sort
        radixSort(nums);

        // Step 2: Traverse the sorted array to find duplicates
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] && (duplicates.isEmpty() || duplicates.get(duplicates.size() - 1) != nums[i])) {
                duplicates.add(nums[i]);
            }
        }

        return duplicates;
    }

    // Function to sort the array using Radix Sort
    private static void radixSort(int[] nums) {
        // Separate negative and non-negative numbers
        int[] negatives = Arrays.stream(nums).filter(n -> n < 0).toArray();
        int[] nonNegatives = Arrays.stream(nums).filter(n -> n >= 0).toArray();

        // Sort negatives and non-negatives separately
        radixSortHelper(nonNegatives, false);
        radixSortHelper(negatives, true);

        // Merge sorted negatives and non-negatives back into nums
        int index = 0;
        for (int i = negatives.length - 1; i >= 0; i--) {
            nums[index++] = negatives[i];
        }
        for (int num : nonNegatives) {
            nums[index++] = num;
        }
    }

    // Helper function to perform Radix Sort
    private static void radixSortHelper(int[] nums, boolean isNegative) {
        int max = Arrays.stream(nums).map(Math::abs).max().orElse(0);
        int exp = 1;

        while (max / exp > 0) {
            countingSortByDigit(nums, exp, isNegative);
            exp *= 10;
        }
    }

    // Counting sort based on the digit represented by exp
    private static void countingSortByDigit(int[] nums, int exp, boolean isNegative) {
        int[] output = new int[nums.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Count occurrences of each digit
        for (int num : nums) {
            int digit = Math.abs(num) / exp % 10;
            count[digit]++;
        }

        // Update count[] so that it contains the actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = nums.length - 1; i >= 0; i--) {
            int digit = Math.abs(nums[i]) / exp % 10;
            output[count[digit] - 1] = nums[i];
            count[digit]--;
        }

        // Copy the output array back to nums
        System.arraycopy(output, 0, nums, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = {122, 32, 1, 0, 32, -22, -32, 44, -22, 0, -22};
        List<Integer> duplicates = findDuplicates(nums);
        System.out.println(duplicates); // Expected output: [32, -22, 0]
    }
}


