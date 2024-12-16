package google.linesweep;

//https://codeforces.com/contest/295/problem/A
// Difference array with prefix sum

public class FindFinalStateOfArrayAfterRangeOps {
    public static void main(String[] args) {
        // Input parameters
        int n = 3; // Size of the array
        int m = 3; // Number of operations
        int k = 3; // Number of queries

        long[] array = {0, 1, 2, 3}; // Initial array (1-based indexing)
        int[][] operations = {
                {0, 0, 0},    // Dummy for 1-based indexing
                {1, 2, 1},    // Operation 1: (l=1, r=2, d=1)
                {1, 3, 2},    // Operation 2: (l=1, r=3, d=2)
                {2, 3, 4}     // Operation 3: (l=2, r=3, d=4)
        };
        int[][] queries = {
                {1, 2},       // Query 1: Apply operations 1 and 2
                {1, 3},       // Query 2: Apply operations 1, 2, and 3
                {2, 3}        // Query 3: Apply operations 2 and 3
        };

        // Call the function to compute the result
        long[] result = computeFinalArray(n, m, k, array, operations, queries);

        // Print the result
        for (int i = 1; i <= n; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static long[] computeFinalArray(int n, int m, int k, long[] array, int[][] operations, int[][] queries) {
        // Step 1: Process queries to calculate operation usage
        long[] opCount = new long[m + 2];
        for (int i = 0; i < k; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            opCount[x]++;
            opCount[y + 1]--;
        }

        // Prefix sum to calculate actual usage of each operation
        for (int i = 1; i <= m; i++) {
            opCount[i] += opCount[i - 1];
        }

        // Step 2: Apply operations to array using difference array
        long[] diffArray = new long[n + 2];
        for (int i = 1; i <= m; i++) {
            int l = operations[i][0];
            int r = operations[i][1];
            int d = operations[i][2];
            long effect = opCount[i] * d;

            diffArray[l] += effect;
            diffArray[r + 1] -= effect;
        }

        // Prefix sum to apply all effects to the array
        for (int i = 1; i <= n; i++) {
            diffArray[i] += diffArray[i - 1];
            array[i] += diffArray[i];
        }

        return array;
    }
}


