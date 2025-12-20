package google;

import java.awt.*;
import java.util.*;
// https://leetcode.com/problems/robot-room-cleaner/description/
/*
You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

*/
/**
 * Robot Room Cleaner - DFS with Backtracking
 *
 * KEY CONCEPT: The robot explores in a CLOCKWISE SPIRAL starting from whatever direction it entered.
 * Direction tracking ensures robot's physical orientation matches calculated direction.
 *
 * Directions Encoding: 0=UP, 1=RIGHT, 2=DOWN, 3=LEFT
 * directions[][] = {{-1,0}, {0,1}, {1,0}, {0,-1}}
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 * EXAMPLE TRACE
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Actual Grid (row, col): Assumed Coordinates:
 * 0 1 2 0 1 2
 * 0 [ X ] [ S ] [ # ] 0 [ ] [ S ] [ ]
 * 1 [ # ] [ O ] [ O ] 1 [ ] [ O ] [ ]
 * 2 [ # ] [ # ] [ # ] 2 [ ] [ ] [ ]
 *
 * Legend: S = Start(0,1), O = Open cell, # = Obstacle, X = Can't go
 * Robot starts at actual (0,1), we assume it's (0,0) and build relative coords
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 * LEVEL 0: dfsClean(robot, 0, 0, 0) - Robot at assumed (0,0), FACING UP
 * ═══════════════════════════════════════════════════════════════════════════════
 * Clean (0,0), mark visited
 *
 * i=0: newDir = (0+0)%4 = 0 → UP direction
 * Check (-1, 0) - cell above
 * Robot facing UP, calls move() → BLOCKED (actual grid boundary)
 * turnRight() → Robot now facing RIGHT
 *
 * i=1: newDir = (0+1)%4 = 1 → RIGHT direction
 * Check (0, 1) - cell to right
 * Robot facing RIGHT, calls move() → BLOCKED (actual grid obstacle at 0,2)
 * turnRight() → Robot now facing DOWN
 *
 * i=2: newDir = (0+2)%4 = 2 → DOWN direction
 * Check (1, 0) - cell below
 * Robot facing DOWN, calls move() → SUCCESS! (actual grid cell 1,1 is open)
 * Robot moves to (1,0) facing DOWN
 * ↓ Recursive call: dfsClean(robot, 1, 0, 2)
 *
 * ═══════════════════════════════════════════════════════════════════════════
 * LEVEL 1: dfsClean(robot, 1, 0, 2) - Robot at assumed (1,0), FACING DOWN
 * ═══════════════════════════════════════════════════════════════════════════
 * Clean (1,0), mark visited
 *
 * i=0: newDir = (2+0)%4 = 2 → DOWN direction (straight ahead)
 * Check (2, 0) - cell below
 * Robot facing DOWN, calls move() → BLOCKED
 * turnRight() → Robot now facing LEFT
 *
 * i=1: newDir = (2+1)%4 = 3 → LEFT direction
 * Check (1, -1) - cell to left
 * Robot facing LEFT, calls move() → BLOCKED
 * turnRight() → Robot now facing UP
 *
 * i=2: newDir = (2+2)%4 = 0 → UP direction (back where it came from)
 * Check (0, 0) - cell above
 * Already visited! Skip.
 * turnRight() → Robot now facing RIGHT
 *
 * i=3: newDir = (2+3)%4 = 1 → RIGHT direction
 * Check (1, 1) - cell to right
 * Robot facing RIGHT, calls move() → SUCCESS! (actual grid cell 1,2)
 * Robot moves to (1,1) facing RIGHT
 * ↓ Would continue recursion...
 * ↑ After recursion completes, goBack() returns to (1,0)
 * turnRight() → Robot now facing DOWN (back to original)
 *
 * Return from LEVEL 1
 * ═══════════════════════════════════════════════════════════════════════════
 *
 * goBack() → Robot returns from (1,0) to (0,0), facing DOWN (as it was)
 * turnRight() → Robot now facing LEFT
 *
 * i=3: newDir = (0+3)%4 = 3 → LEFT direction
 * Check (0, -1) - cell to left
 * Robot facing LEFT, calls move() → BLOCKED
 * turnRight() → Robot now facing UP (back to original)
 *
 * All 4 directions explored. Return from LEVEL 0.
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * KEY INSIGHTS:
 * 1. Robot's physical direction = (dir + i) % 4 after i turnRight() calls
 * 2. Each cell explores: straight → right → back → left (clockwise spiral)
 * 3. goBack() returns robot to previous cell facing same direction as before move
 * 4. turnRight() only executes when robot is back at current cell (after full recursion or blocked)
 *
 * interface Robot {
 * // Returns true if the cell in front is open and robot moves into the cell.
 * // Returns false if the cell in front is blocked and robot stays in the current cell.
 * public boolean move();
 *
 * // Robot will stay in the same cell after calling turnLeft/turnRight.
 * // Each turn will be 90 degrees.
 * public void turnLeft();
 * public void turnRight();
 *
 * // Clean the current cell.
 * public void clean();
 * }
 */
public class RobotRoomCleaner {
    // Directions: 0=up, 1=right, 2=down, 3=left
    private int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        dfsClean(robot, 0, 0, 0); // start at (0,0) facing up (direction 0)
    }

    private void dfsClean(Robot robot, int row, int col, int dir) {
// Mark current cell as visited and clean it
        String cell = row + "," + col;
        visited.add(cell);
        robot.clean();

// Try all 4 directions
        for (int i = 0; i < 4; i++) {
            int newDir = (dir + i) % 4; // Current direction after i right turns
            int newRow = row + directions[newDir][0];
            int newCol = col + directions[newDir][1];
            String newCell = newRow + "," + newCol;

// If not visited and can move
            if (!visited.contains(newCell) && robot.move()) {
                dfsClean(robot, newRow, newCol, newDir);
// Backtrack: return to previous cell and face original direction
                goBack(robot);
            }

// Turn right to try next direction
            robot.turnRight();
        }
    }

    /**
     * Move robot back to previous cell and restore original direction
     *
     * INTUITION:
     * After exploring a cell, we need to return to the parent cell facing the SAME direction
     * we were facing before we moved forward.
     *
     * Example:
     * - Robot at cell A, facing RIGHT (→)
     * - Moves forward to cell B, still facing RIGHT (→)
     * - After exploring B, need to return to A facing RIGHT (→)
     *
     * Steps:
     * 1. turnRight() + turnRight() = Turn 180° (now facing LEFT ←)
     * 2. move() = Move back to cell A (still facing LEFT ←)
     * 3. turnRight() + turnRight() = Turn 180° (now facing RIGHT → again)
     *
     * Result: Back at cell A, facing RIGHT (original direction restored)
     *
     * WHY THIS MATTERS:
     * After returning from recursion, the parent's for-loop continues with turnRight().
     * The parent expects the robot to be facing the same direction as when the child call started.
     * Without restoring direction, the parent's direction tracking would desync.
     *
     * Visual Trace:
     * Cell A (facing →) --move()--> Cell B (facing →)
     * |
     * [explore B]
     * |
     * goBack():
     * turn 180° (facing ←)
     * Cell A (facing ←) <--move()-- Cell B (facing ←)
     * |
     * turn 180°
     * ↓
     * Cell A (facing →) [RESTORED!]
     */
    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}

// Dummy Robot interface for compilation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
// Returns false if the cell in front is blocked and robot stays in the current cell.
    boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
// Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
}
