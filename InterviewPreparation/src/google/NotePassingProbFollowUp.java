package google;

import java.util.*;

public class NotePassingProbFollowUp {

    static class Cell {
        int row, col;
        double probability;

        Cell(int row, int col, double probability) {
            this.row = row;
            this.col = col;
            this.probability = probability;
        }
    }

    public static void main(String[] args) {
        // Grid dimensions
        int N = 4, M = 4;

        // Starting and ending coordinates
        int startRow = 0, startCol = 0;
        int endRow = 3, endCol = 3;

        // Initial probabilities
        double initialP = 0.5, initialQ = 0.2;

        // Calculate the maximum probability to reach (endRow, endCol) from (startRow, startCol)
        double maxProbability = calculateMaxProbability(N, M, startRow, startCol, endRow, endCol, initialP, initialQ);
        System.out.printf("Maximum probability to reach destination: %.5f%n", maxProbability);
    }

    public static double calculateMaxProbability(int N, int M, int startRow, int startCol, int endRow, int endCol, double p, double q) {
        // Directions for moving (Up, Down, Left, Right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Priority queue to process cells in order of maximum probability
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));

        // Visited array to track maximum probability for each cell
        double[][] maxProb = new double[N][M];
        for (double[] row : maxProb) {
            Arrays.fill(row, 0.0);
        }

        // Start with the initial cell
        pq.add(new Cell(startRow, startCol, 1.0));
        maxProb[startRow][startCol] = 1.0;

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            int row = current.row, col = current.col;
            double prob = current.probability;

            // If we reached the destination, return the probability
            if (row == endRow && col == endCol) {
                return prob;
            }

            // Process neighbors
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if the neighbor is within bounds
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M) {
                    // Calculate new probability based on the direction
                    double newProb = prob;
                    if (direction[0] == -1) {
                        // Moving up (row decreases)
                        newProb *= (1 - q * 2);
                    } else if (direction[0] == 1) {
                        // Moving down (row increases)
                        newProb *= (1 - q / 2);
                    } else {
                        // Moving left or right
                        newProb *= (1 - p / 2);
                    }

                    // Update the probability if we find a better path
                    if (newProb > maxProb[newRow][newCol]) {
                        maxProb[newRow][newCol] = newProb;
                        pq.add(new Cell(newRow, newCol, newProb));
                    }
                }
            }
        }

        // If we exhaust the priority queue and never reach the destination
        return 0.0;
    }
}


