package binarySearch;

// 1283. Find the Smallest Divisor Given a Threshold
// https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/

public class FindSmallestDivisorGivenAThreshold {
    private int binarySearch(int[] nums, int threshold, int low, int high) {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = findSum(nums, mid);
            if (sum > threshold) {
                low = mid + 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    private int findSum(int[] nums, int mid) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Math.ceil((double) nums[i] / mid);
        }
        return sum;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        return binarySearch(nums, threshold, 0, max);
    }

    public static void main(String[] args) {
        /*
        Input: nums = [1,2,5,9], threshold = 6
        Output: 5
        Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
        If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
         */
    }
}
