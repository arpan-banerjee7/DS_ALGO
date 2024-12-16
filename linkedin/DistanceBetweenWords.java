package linkedin;

import java.util.*;

public class DistanceBetweenWords {
    private Map<String, List<Integer>> store = new HashMap<>();

    public DistanceBetweenWords(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            store.computeIfAbsent(word, k -> new ArrayList<>()).add(i);
        }
    }

    public int distance(String wordOne, String wordTwo) {
        if (wordOne == null || wordTwo == null) {
            return -1;
        }
        if (!store.containsKey(wordOne) || !store.containsKey(wordTwo)) {
            return -1;
        }
        if (wordOne.equals(wordTwo)) {
            return 0;
        }

        List<Integer> list1 = store.get(wordOne);
        List<Integer> list2 = store.get(wordTwo);

        int minDistance = Integer.MAX_VALUE;

        for (int idx : list1) {
            int closestIdx = findClosest(list2, idx);
            minDistance = Math.min(minDistance, Math.abs(idx - closestIdx));
            if (minDistance == 1) { // Early exit if minimum possible distance is found
                break;
            }
        }

        return minDistance;
    }

    // TC- O(n*logk)
    private int findClosest(List<Integer> lst, int val) {
        int low = 0;
        int high = lst.size() - 1;

        if (val <= lst.get(0)) {
            return lst.get(0);
        }
        if (val >= lst.get(high)) {
            return lst.get(high);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = lst.get(mid);

            if (midVal == val) {
                return midVal;
            } else if (midVal < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // After the loop, low is the insertion point
        int leftNeighbor = lst.get(high);
        int rightNeighbor = lst.get(low);

        // Return the closer of the two neighbors
        if (Math.abs(leftNeighbor - val) <= Math.abs(rightNeighbor - val)) {
            return leftNeighbor;
        } else {
            return rightNeighbor;
        }
    }

    //TC- o(n+k)
    public int distanceTwoPointers(String wordOne, String wordTwo) {
        if (wordOne == null || wordTwo == null) {
            return -1;
        }
        if (!store.containsKey(wordOne) || !store.containsKey(wordTwo)) {
            return -1;
        }
        if (wordOne.equals(wordTwo)) {
            return 0;
        }

        List<Integer> list1 = store.get(wordOne);
        List<Integer> list2 = store.get(wordTwo);

        int i = 0, j = 0;
        int minDistance = Integer.MAX_VALUE;

        while (i < list1.size() && j < list2.size()) {
            int idx1 = list1.get(i);
            int idx2 = list2.get(j);
            minDistance = Math.min(minDistance, Math.abs(idx1 - idx2));

            if (idx1 < idx2) {
                i++;
            } else {
                j++;
            }
        }

        return minDistance;
    }


    public static void main(String[] args) {
        DistanceBetweenWords finder = new DistanceBetweenWords(Arrays.asList(
                "the", "java", "skill", "is", "important", "for", "java", "development"
        ));
        System.out.println(finder.distance("important", "the")); // Output: 4
        System.out.println(finder.distance("important", "java")); // Output: 2
        System.out.println(finder.distance("java", "doesntExist")); // Output: -1

    }
}


/*
  Random random = new Random();
        random.nextInt(8);
        int[] arr = {4,5,6,7};
        int[] clonedArr = arr.clone();
 */