package binarySearch;

// 1482. Minimum Number of Days to Make m Bouquets
// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/solutions/4739917/java-binary-search-sliding-window/

public class MinDaysToMakeMBouquets {
    private int binarySearch(int[] bloomDay, int low, int high, int m, int k) {
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int noOfBouquetsMade = makeBouquets(bloomDay, mid, k);
            if (noOfBouquetsMade < m) {
                low = mid + 1; // increase blooming days threshold so that more flowers get a chance to bloom
            } else {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    // sliding window to figure out if we can make m bouquets with k adjacent flowers
    private int makeBouquets(int[] bloomDay, int mid, int k) {
        int bouquet = 0;
        int start = 0;
        for (int end = 0; end < bloomDay.length; end++) {
            if (bloomDay[end] > mid) { // flower did not bloom, skip that flower start finding a group of k from next position
                start = end + 1;
            }
            // k flowers have bloomed, increase bouquet size and go to next pos for finding next group
            if (end - start + 1 == k) {
                bouquet++;
                start = end + 1;
            }
        }
        return bouquet;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n) return -1;

        int max = Integer.MIN_VALUE;
        for (int b : bloomDay) {
            max = Math.max(max, b);
        }
        return binarySearch(bloomDay, 1, max, m, k);
    }

    public static void main(String[] args) {
        /*
        Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
        Output: 3
        Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
        We need 3 bouquets each should contain 1 flower.
        After day 1: [x, _, _, _, _]   // we can only make one bouquet.
        After day 2: [x, _, _, _, x]   // we can only make two bouquets.
        After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
         */
    }
}
