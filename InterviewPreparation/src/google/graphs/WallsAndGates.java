package google;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/walls-and-gates/

public class WallsAndGates {
    /**
     * Fill each empty room with distance to its nearest gate using multi-source BFS.
     *
     * ALGORITHM:
     * 1. Find all gates (cells with value 0) and add them to the queue
     * 2. Perform BFS from all gates simultaneously
     * 3. For each cell, update adjacent empty rooms (INF) with distance + 1
     * 4. Once a room's distance is set, it acts as "visited" and won't be updated again
     *
     * TIME COMPLEXITY: O(m×n)
     *
     * Analysis:
     * - If you are having difficulty to derive the time complexity, start simple.
     * - Let us start with the case with only one gate. The breadth-first search takes
     * at most m×n steps to reach all rooms, therefore the time complexity is O(m×n).
     * - But what if you are doing breadth-first search from k gates?
     * - Once we set a room's distance, we are basically marking it as visited, which
     * means each room is visited at most once.
     * - Therefore, the time complexity does not depend on the number of gates and is O(m×n).
     *
     * SPACE COMPLEXITY: O(m×n)
     *
     * Analysis:
     * - The space complexity depends on the queue's size.
     * - We insert at most m×n points into the queue.
     *
     * @param rooms m×n grid with -1 (wall), 0 (gate), or INF (empty room)
     */
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] directions = {{1,0},{0,-1},{0,1},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();

// Find all gates and add to queue for multi-source BFS
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j] == 0){ // Found a gate
                    queue.add(new int[]{i,j});
                }
            }
        }

// Multi-source BFS from all gates
        while(!queue.isEmpty()){
            int[] currPos = queue.poll();
            int currDist = rooms[currPos[0]][currPos[1]];

            for(int[] dir : directions){
                int newRow = currPos[0] + dir[0];
                int newCol = currPos[1] + dir[1];

// Check bounds and if it's an empty room (INF)
                if(newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && rooms[newRow][newCol] == Integer.MAX_VALUE){
                    rooms[newRow][newCol] = currDist + 1;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }
    }

/* ==================================================================================
* OFFICIAL OPTIMAL SOLUTION - Multi-Source BFS (Same approach as above)
* ==================================================================================
*
* TIME COMPLEXITY: O(mn)
* SPACE COMPLEXITY: O(mn)
*
* This is functionally identical to our implementation above, just with different
* coding style (constants, continue pattern vs positive conditions)
*
* ==================================================================================

private static final int EMPTY = Integer.MAX_VALUE;
private static final int GATE = 0;
private static final List<int[]> DIRECTIONS = Arrays.asList(
new int[] { 1, 0},
new int[] {-1, 0},
new int[] { 0, 1},
new int[] { 0, -1}
);

public void wallsAndGates(int[][] rooms) {
int m = rooms.length;
if (m == 0) return;
int n = rooms[0].length;
Queue<int[]> q = new LinkedList<>();
for (int row = 0; row < m; row++) {
for (int col = 0; col < n; col++) {
if (rooms[row][col] == GATE) {
q.add(new int[] { row, col });
}
}
}
while (!q.isEmpty()) {
int[] point = q.poll();
int row = point[0];
int col = point[1];
for (int[] direction : DIRECTIONS) {
int r = row + direction[0];
int c = col + direction[1];
if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
continue;
}
rooms[r][c] = rooms[row][col] + 1;
q.add(new int[] { r, c });
}
}
}

================================================================================== */

/* ==================================================================================
* BRUTE FORCE SOLUTION - BFS from Each Empty Room
* ==================================================================================
*
* APPROACH: For each empty room, perform BFS to find nearest gate
*
* TIME COMPLEXITY: O(m²n²) - Much slower than multi-source BFS
* - For each empty cell O(mn), we perform BFS that can visit O(mn) cells
*
* SPACE COMPLEXITY: O(mn) - Distance array for each BFS
*
* This approach is less efficient because:
* 1. We perform separate BFS for each empty room
* 2. Many cells are visited multiple times across different BFS calls
* 3. Multi-source BFS visits each cell only once
*
* ==================================================================================

private static final int EMPTY = Integer.MAX_VALUE;
private static final int GATE = 0;
private static final int WALL = -1;
private static final List<int[]> DIRECTIONS = Arrays.asList(
new int[] { 1, 0},
new int[] {-1, 0},
new int[] { 0, 1},
new int[] { 0, -1}
);

public void wallsAndGates(int[][] rooms) {
if (rooms.length == 0) return;
for (int row = 0; row < rooms.length; row++) {
for (int col = 0; col < rooms[0].length; col++) {
if (rooms[row][col] == EMPTY) {
rooms[row][col] = distanceToNearestGate(rooms, row, col);
}
}
}
}

private int distanceToNearestGate(int[][] rooms, int startRow, int startCol) {
int m = rooms.length;
int n = rooms[0].length;
int[][] distance = new int[m][n];
Queue<int[]> q = new LinkedList<>();
q.add(new int[] { startRow, startCol });
while (!q.isEmpty()) {
int[] point = q.poll();
int row = point[0];
int col = point[1];
for (int[] direction : DIRECTIONS) {
int r = row + direction[0];
int c = col + direction[1];
if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] == WALL
|| distance[r][c] != 0) {
continue;
}
distance[r][c] = distance[row][col] + 1;
if (rooms[r][c] == GATE) {
return distance[r][c];
}
q.add(new int[] { r, c });
}
}
return Integer.MAX_VALUE;
}

================================================================================== */
}
