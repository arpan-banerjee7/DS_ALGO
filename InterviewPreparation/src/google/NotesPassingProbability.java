package google;
// https://leetcode.com/problems/path-with-maximum-probability/submissions/1460258966/

public class NotesPassingProbability {
    public static void main(String[] args) {
        // Example Input
        int[][] path = {{1, 1}, {1, 2}, {2, 2}, {3, 2}, {3, 3}};
        double initialP = 0.5;
        double initialQ = 0.2;

        // Grid dimensions (N * M), assumed to be large enough for the path
        int N = 4;
        int M = 4;

        // Calculate the probability of not getting caught
        double probability = calculateProbability(N, M, path, initialP, initialQ);
        System.out.printf("Probability of not getting caught: %.5f%n", probability);
    }

    public static double calculateProbability(int N, int M, int[][] path, double p, double q) {
        double probability = 1.0; // Start with a probability of 1 (not getting caught)

        // Traverse the path
        for (int i = 0; i < path.length - 1; i++) {
            int r1 = path[i][0] - 1; // Convert to 0-based indexing
            int c1 = path[i][1] - 1;
            int r2 = path[i + 1][0] - 1;
            int c2 = path[i + 1][1] - 1;

            // Determine the row-level probabilities
            double currentP = p / Math.pow(2, r1); // Reduce p based on the row
            double currentQ = q / Math.pow(2, r1); // Reduce q based on the row

            // Check movement direction
            if (r1 == r2) {
                // Moving left or right (same row)
                probability *= (1 - currentP);
            } else if (c1 == c2) {
                // Moving up or down (same column)
                probability *= (1 - currentQ);
            }
        }

        return probability;
    }
}
