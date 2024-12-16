package google;

import java.util.*;

public class BlindRobotMaze {

    static class Pair {
        int row;
        int col;

        Pair(int r, int c) {
            this.row = r;
            this.col = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return this.row == p.row && this.col == p.col;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    // Directions and their corresponding moves
    static char[] directions = {'U', 'D', 'L', 'R'};
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};

    public static String findCommandSequence(char[][] maze, int rows, int cols, Pair exitPos) {
        Set<Pair> possiblePositions = new HashSet<>();

        // Initialize possible positions to all open cells (including the exit)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == '.' || maze[r][c] == 'E') {
                    possiblePositions.add(new Pair(r, c));
                }
            }
        }

        StringBuilder commandSequence = new StringBuilder();

        // Keep reducing possible positions until only the exit remains
        while (possiblePositions.size() > 1) {
            int minSize = Integer.MAX_VALUE;
            char bestMove = ' ';
            Set<Pair> bestNextPositions = new HashSet<>();

            // Try each possible move
            for (int i = 0; i < 4; i++) {
                char move = directions[i];
                int dr = dRow[i];
                int dc = dCol[i];

                Set<Pair> nextPositions = new HashSet<>();
                for (Pair pos : possiblePositions) {
                    int newRow = pos.row + dr;
                    int newCol = pos.col + dc;

                    // Check if move is within bounds and not a wall
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                            && maze[newRow][newCol] != '#') {
                        nextPositions.add(new Pair(newRow, newCol));
                    } else {
                        // If there's a wall, the robot stays in the same position
                        nextPositions.add(new Pair(pos.row, pos.col));
                    }
                }

                // Choose the move that minimizes the possible positions
                if (nextPositions.size() < minSize) {
                    minSize = nextPositions.size();
                    bestMove = move;
                    bestNextPositions = nextPositions;
                }
            }

            // Update possible positions and command sequence
            possiblePositions = bestNextPositions;
            commandSequence.append(bestMove);
        }

        return commandSequence.toString();
    }

    public static void main(String[] args) {
        // Define multiple maze examples
        char[][] maze1 = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', '#', 'E', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#'}
        };

        char[][] maze2 = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '#', 'E', '#', '.', '#'},
                {'#', '.', '.', '.', '#', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        char[][] maze3 = {
                {'#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '#', '.', 'E', '#'},
                {'#', '.', '#', '.', '#', '.', '.', '#'},
                {'#', '.', '#', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#'}
        };

        char[][] maze4 = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '#', '#', '#', '#', '#', '.', '#'},
                {'#', '.', '#', '.', '.', '.', '#', '.', '#'},
                {'#', '.', '#', '.', 'E', '.', '#', '.', '#'},
                {'#', '.', '#', '.', '.', '.', '#', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        // Call the method for each maze and print the results
        runMaze(maze1, "Maze 1");
        runMaze(maze2, "Maze 2");
        runMaze(maze3, "Maze 3");
        runMaze(maze4, "Maze 4");
    }

    private static void runMaze(char[][] maze, String mazeName) {
        int rows = maze.length;
        int cols = maze[0].length;
        Pair exitPos = null;

        // Find the exit position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'E') {
                    exitPos = new Pair(i, j);
                    break;
                }
            }
        }

        // Find the command sequence
        String commandSequence = findCommandSequence(maze, rows, cols, exitPos);
        System.out.println(mazeName + " Command Sequence: " + commandSequence);
    }
}
