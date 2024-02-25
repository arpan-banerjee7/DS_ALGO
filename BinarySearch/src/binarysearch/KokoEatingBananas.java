package binarySearch;

// 875. Koko Eating Bananas
// https://leetcode.com/problems/koko-eating-bananas/description/
// pattern of finding answers. whenever we have this pattern a loop can be reduced using binary search
// TC- O(n*log(max(n)))

public class KokoEatingBananas {
    private int binarySearch(int[] piles, int low, int high, int h) {
        int minTimeTaken = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int timeTaken = canEatAllBananas(piles, mid);
            if (timeTaken > h) {
                low = mid + 1; // increase speed
            } else {
                high = mid - 1;
                minTimeTaken = mid;
            }
        }
        return minTimeTaken;
    }

    private int canEatAllBananas(int[] piles, int rate) {
        int totalTimeTaken = 0;
        for (int p : piles) {
            totalTimeTaken += Math.ceil((double) p / rate);
        }
        return totalTimeTaken;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (int p : piles) {
            max = Math.max(max, p);
        }
        int low = 1;
        return binarySearch(piles, low, max, h);
    }

    public static void main(String[] args) {
        /*
        Input: piles = [3,6,7,11], h = 8
        Output: 4
         */
    }

}
