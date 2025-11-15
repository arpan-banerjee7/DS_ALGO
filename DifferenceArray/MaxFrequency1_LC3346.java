import java.util.*;

/*
https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/
Take each number in input array and enumerate all the possible numbers which can be generated if 
we add the range -k to k to that number. Use difference array technique to find out the sum of all 
times each number can be formed from all the numbers in given range.

Lets say for this input- nums = [5,11,20,20], k = 5, numOperations = 1
we can see that we can get 15 and 16 3 times if we enumerate all the possibilities for all the 
numbers and sum the occurances.
so, maxFeqObtained=3
freqAlreadyPresent=0 (since we do not have 15, 3 numbers in the input array must have been converted 
to get 3 occurances of 15)
numOpsAllowed=1 (we are allowed to perform only 1 operation), so result for this number would be 1.

for nums[i]=20,
maxFeqObtained=2
freqAlreadyPresent=2 ( need to convert 0 numbers)
Output=2

 */
public class MaxFrequency1_LC3346 {
    public static int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        int maxVal = Arrays.stream(nums).max().getAsInt() + k;
        int[] diffArr = new int[maxVal + 2];

        // prepare freq map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        // prepare diff array
        for (int i = 0; i < n; i++) {
            int min = Math.max(nums[i] - k, 0);
            int max = nums[i] + k;
            diffArr[min]++;
            diffArr[max + 1]--;
        }
        int result = 0;
        for (int i = 0; i < diffArr.length; i++) {
            if (i > 0) {
                diffArr[i] += diffArr[i - 1];
            }
            int maxFreqPossible = diffArr[i];
            int freqAlreadyPresent = freqMap.getOrDefault(i, 0);
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