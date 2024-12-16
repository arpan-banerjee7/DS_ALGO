package google;

import java.util.*;

// total TC- O(n*mlogm+klogk)
public class RearrangeNeighborhoodHouses {
    private static int[][] reArrangeNeigborhoodHouses(int[][] houses) {

        // Frequency Map
        // TC- O(n*m), n=row, m=col
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < houses.length; i++) {
            for (int j = 0; j < houses[i].length; j++) {
                freqMap.put(houses[i][j], freqMap.getOrDefault(houses[i][j], 0) + 1);
            }
        }

        //TC- O(klogk), where k is the unique number of houses
        // Add the freq in pq and sort on the basis of freq.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            pq.add(new int[]{key, freq});
        }

        // TC- O(n)
        int maxCol = 0;
        for (int i = 0; i < houses.length; i++) {
            maxCol = Math.max(maxCol, houses[i].length);
        }
        int[] curr = pq.remove();

        // TC- O(n*m)
        // Going from top to bottom first row wise and assigning value first which are higher in freq.
        for (int j = 0; j < maxCol; j++) {
            for (int i = 0; i < houses.length; i++) {
                if (j >= houses[i].length)
                    continue;
                if (curr[1] == 0) {
                    curr = pq.remove();
                }
                houses[i][j] = curr[0];
                curr[1]--;
            }
        }

        //TC-O(n*mlogm)
        // Sort each row.
        for (int i = 0; i < houses.length; i++) {
            Arrays.sort(houses[i]);
        }

        return houses;
    }

    public static void main(String[] args) {
        int[][] houses = {
                {1, 2},
                {4, 4, 7, 8},
                {4, 9, 9, 9}
        };

        int[][] rearrangedHouses = reArrangeNeigborhoodHouses(houses);
        for (int i = 0; i < rearrangedHouses.length; i++) {
            for (int j = 0; j < rearrangedHouses[i].length; j++) {
                System.out.print(rearrangedHouses[i][j] + " ");
            }
            System.out.println();
        }
    }
}
