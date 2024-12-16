package google;

import java.util.*;

public class RearrangeNeighborhoodHouses2 {


    public static List<List<Integer>> rearrangeHouses(List<List<Integer>> neighborhoods) {
        // Step 1: Count frequencies of each house number
        Map<Integer, Integer> freq = new HashMap<>();
        for (List<Integer> neighborhood : neighborhoods) {
            for (int house : neighborhood) {
                freq.put(house, freq.getOrDefault(house, 0) + 1);
            }
        }

        // Step 2: Create a max heap based on frequencies
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            maxHeap.add(new int[]{entry.getValue(), entry.getKey()});
        }

        // Step 3: Distribute houses to neighborhoods
        List<List<Integer>> result = new ArrayList<>();
        int[] capacities = new int[neighborhoods.size()];

        // Initialize result and capacities
        for (int i = 0; i < neighborhoods.size(); i++) {
            result.add(new ArrayList<>());
            capacities[i] = neighborhoods.get(i).size();
        }

        // Distribute houses
        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll(); // Get the most frequent house
            int count = current[0];
            int house = current[1];

            for (int i = 0; i < neighborhoods.size(); i++) {
                if (capacities[i] > 0) {
                    result.get(i).add(house);
                    capacities[i]--;
                    count--;

                    if (count == 0) {
                        break;
                    }
                }
            }

            // If we still have houses to distribute, put them back in the heap
            if (count > 0) {
                maxHeap.add(new int[]{count, house});
            }
        }

        // Step 4: Sort each neighborhood
        for (List<Integer> neighborhood : result) {
            Collections.sort(neighborhood);
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> neighborhoods = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(4, 4, 7, 8),
                Arrays.asList(4, 9, 9, 9)
        );

        List<List<Integer>> rearranged = rearrangeHouses(neighborhoods);

        // Print the result
        for (List<Integer> neighborhood : rearranged) {
            System.out.println(neighborhood);
        }
    }
}

