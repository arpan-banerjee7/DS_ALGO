package google;

import java.util.*;

public class NPersonsMPairsDSU {

    // DisjointSet class implementation
    static class DisjointSet {
        int[] parent;
        int[] rank;
        int[] size;
        int n;

        public DisjointSet(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
            makeSet();
        }

        private void makeSet() {
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findParent(int u) {
            if (parent[u] == u) {
                return u;
            }
            return parent[u] = findParent(parent[u]); // Path compression
        }

        public void unionBySize(int u, int v) {
            int pU = findParent(u);
            int pV = findParent(v);

            if (pU == pV) return;

            if (size[pU] < size[pV]) {
                parent[pU] = pV;
                size[pV] += size[pU];
            } else {
                parent[pV] = pU;
                size[pU] += size[pV];
            }
        }
    }


    public static int countNotKnowingPairs(int n, int[][] meetings) {
        if (n == 0) {
            return 0;
        }

        // Initialize the DisjointSet
        DisjointSet dsu = new DisjointSet(n);

        // Perform union operations for each meeting pair
        for (int[] meeting : meetings) {
            dsu.unionBySize(meeting[0], meeting[1]);
        }

        // Find sizes of all connected components
        Set<Integer> uniqueParents = new HashSet<>();
        List<Integer> componentSizes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int parent = dsu.findParent(i);
            if (!uniqueParents.contains(parent)) {
                uniqueParents.add(parent);
                componentSizes.add(dsu.size[parent]); // Use size[parent] directly
            }
        }

        // Compute the number of pairs that do not "know" each other
        int totalPairs = 0;
        int cumulativeSize = 0;

        for (int sizeComponent : componentSizes) {
            totalPairs += sizeComponent * (n - cumulativeSize - sizeComponent);
            cumulativeSize += sizeComponent;
        }

        return totalPairs;
    }

    public static void main(String[] args) {
        // Test case
        int n = 6; // Number of people
        int[][] meetings = {
                {0, 1},
                {1, 2},
                {2, 0},
                {3, 4}

        };

        // Call the function and print the result
        int result = countNotKnowingPairs(n, meetings);
        System.out.println(result); // Expected output: 9
    }

}
