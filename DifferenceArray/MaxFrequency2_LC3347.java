import java.util.*;

/*
https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii/
 * It same as LC 3346, o(n) is possible only when the length is <=10^7. 
 * But here the nums[i] and k can be upto 10^9. So we need to minimise the diference array,
 * as we are iterating through it and taking unnecessary space, now instead of taking the 
 * entire array we wil just store the elements at the difference points, and then do the same logic.

Also we need to include the elements of the given nums.
 */
public class MaxFrequency2_LC3347 {
    public static int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        // prepare freq map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        Map<Integer, Integer> diffMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int left = Math.max(nums[i] - k, 0);
            int right = nums[i] + k;
            diffMap.put(nums[i], diffMap.getOrDefault(nums[i], 0) + 0);
            diffMap.put(left, diffMap.getOrDefault(left, 0) + 1);
            diffMap.put(right + 1, diffMap.getOrDefault(right + 1, 0) - 1);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : diffMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            sum += val;
            diffMap.put(key, sum);
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : diffMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            int maxFreqPossible = val;
            int freqAlreadyPresent = freqMap.getOrDefault(key, 0);
            int numOfConverstionsReq = maxFreqPossible - freqAlreadyPresent;
            int maxFreqObtained = Math.min(numOfConverstionsReq, numOperations);
            result = Math.max(result, maxFreqObtained + freqAlreadyPresent); // imp
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 5, 11, 20, 20 };
        int k = 5;
        int numOperations = 1;

        int res = maxFrequency(nums, k, numOperations);
        System.out.println(
                "Input: nums = " + Arrays.toString(nums) + ", k = " + k + ", numOperations = " + numOperations);
        System.out.println("Output: " + res);
    }
}