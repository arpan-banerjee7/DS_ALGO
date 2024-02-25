package binarySearch;

// 1011. Capacity To Ship Packages Within D Days
// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
// if we take the low to be the max value in the array then we wll not need to do this  if(weights[i]>capacity) return Integer.MAX_VALUE;
// here our mid, that is capacity won t ever fall below the value of an element of the weights
// test case //[1,2,3,1,1] days=4
public class CapacityToShipPackageInDDays {
    private int binarySearch(int[] weights, int low, int high, int days) {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int shippedInDays = findDaysToShip(weights, mid);
            if (shippedInDays > days) {
                low = mid + 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    private int findDaysToShip(int[] weights, int capacity) {
        int sum = 0;
        int days = 1; // to count the last element
        for (int i = 0; i < weights.length; i++) {
            // if(weights[i]>capacity) return Integer.MAX_VALUE;
            if (sum + weights[i] > capacity) {
                sum = 0;
                days++;
            }
            sum += weights[i];
        }
        return days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        int max = 0;
        for (int i : weights) {
            max = Math.max(max, i);
            sum += i;
        }
        return binarySearch(weights, max, sum, days);
    }

    public static void main(String[] args) {
        /*
        Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
        Output: 15
        Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
        1st day: 1, 2, 3, 4, 5
        2nd day: 6, 7
        3rd day: 8
        4th day: 9
        5th day: 10

        Note that the cargo must be shipped in the order given,
        so using a ship of capacity 14 and splitting the packages into
        parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
         */
    }
}

