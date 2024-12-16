package google;

import java.util.*;

public class RearrangedNeighborHoodWithColors {

    static class Neighborhood {
        int houseNumber;
        char color;

        public Neighborhood(int houseNumber, char color) {
            this.houseNumber = houseNumber;
            this.color = color;
        }

        @Override
        public String toString() {
            return houseNumber + "" + color; // Example: "1b"
        }
    }

    public static List<List<Neighborhood>> reArrangeNeighborhoodHouses(
            List<List<Integer>> houses, List<List<Character>> colors) {

        // Step 1: Count frequencies of each house number
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (List<Integer> neighborhood : houses) {
            for (int house : neighborhood) {
                freqMap.put(house, freqMap.getOrDefault(house, 0) + 1);
            }
        }

        // Step 2: Create a max heap based on frequencies
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // Step 3: Create a map for house-to-color mapping
        Map<Integer, Character> houseColorMap = new HashMap<>();
        for (int i = 0; i < houses.size(); i++) {
            for (int j = 0; j < houses.get(i).size(); j++) {
                houseColorMap.put(houses.get(i).get(j), colors.get(i).get(j));
            }
        }

        // Step 4: Initialize the result list
        List<List<Neighborhood>> result = new ArrayList<>();
        for (List<Integer> neighborhood : houses) {
            result.add(new ArrayList<>());
        }

        // Step 5: Fill houses row-wise first
        int[] curr = pq.remove();
        for (int j = 0; j < houses.get(0).size(); j++) { // Iterate over columns
            for (int i = 0; i < houses.size(); i++) { // Iterate over rows
                if (j >= houses.get(i).size()) {
                    continue;
                }
                if (curr[1] == 0) {
                    curr = pq.remove();
                }
                int house = curr[0];
                char color = houseColorMap.get(house);
                result.get(i).add(new Neighborhood(house, color)); // Add house and color
                curr[1]--;
            }
        }

        // Step 6: Sort each row by house number
        for (List<Neighborhood> neighborhood : result) {
            neighborhood.sort(Comparator.comparingInt(h -> h.houseNumber));
        }

        return result;
    }

    public static void main(String[] args) {
        // Input neighborhoods
        List<List<Integer>> houses = Arrays.asList(
                Arrays.asList(8, 2, 9),
                Arrays.asList(4, 6, 4),
                Arrays.asList(4, 5, 1)
        );

        // Input colors
        List<List<Character>> colors = Arrays.asList(
                Arrays.asList('r', 'g', 'b'),
                Arrays.asList('w', 'c', 'b'),
                Arrays.asList('x', 'y', 'b')
        );

        // Call the function and get the rearranged result
        List<List<Neighborhood>> rearranged = reArrangeNeighborhoodHouses(houses, colors);

        // Print the result
        for (List<Neighborhood> neighborhood : rearranged) {
            System.out.println(neighborhood);
        }
    }
}



