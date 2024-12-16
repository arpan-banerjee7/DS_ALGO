package google;

import java.util.*;

/*
This problem is definitely Hard. It's not trivial to figure out how to apply DP here.

The idea is to break this problem into two parts:

Find all possible ways to build a single row.
We can use DFS for that, and store each row as a bit mask.
Arrange rows into a wall, counting valid row arrangements.
We can use DP, and I think that a top-down DP is a bit easier to understand.
 */
public class NumberOfWaysToBuildASturdyBrickWall {

    // Top-down DP (DFS + memoization)
// O(n*log(n) + height * 2^width) time, where n == bricks.length.
// O(height * 2^width + height * width) space.
    public int buildWall(int height, int width, int[] bricks) {
        Arrays.sort(bricks);    // Sort so that we can stop early while placing bricks.
        return helper(new Integer[height + 1][1 << width], bricks, height, width, 1, 0, 0, 0);
    }

    private int helper(Integer cache[][], int[] bricks, int targetHeight, int targetWidth, int currHeight, int currWidth, int prevJoins, int currJoins) {
        if (currHeight == targetHeight && currWidth == targetWidth) return 1;

        // Start with the next row if we are done with the current one.
        if (currWidth == targetWidth) {
            return helper(cache, bricks, targetHeight, targetWidth, currHeight + 1, 0, currJoins, 0);
        }
        // Use a cached solution if avaialble.
        if (currWidth == 0 && cache[currHeight][prevJoins] != null) {
            return cache[currHeight][prevJoins];
        }

        int result = 0;
        for (int i = 0; i < bricks.length; ++i) {
            int endOfCurrentBrick = currWidth + bricks[i];
            if (targetWidth < endOfCurrentBrick) break;  // We can stop early if the current brick doesn't fit.
            if ((prevJoins & (1 << endOfCurrentBrick)) == 0) {
                if (endOfCurrentBrick < targetWidth) currJoins |= (1 << endOfCurrentBrick);     // Set i-th bit to 1.
                result += helper(cache, bricks, targetHeight, targetWidth, currHeight, endOfCurrentBrick, prevJoins, currJoins) % 1_000_000_007;
                result %= 1_000_000_007;
                if (endOfCurrentBrick < targetWidth)
                    currJoins &= ~(1 << endOfCurrentBrick);    // Backtrack. Set i-th bit to 0.

            }
        }
        return currWidth == 0 ? cache[currHeight][prevJoins] = result : result;
    }
}
/*
You are given integers height and width which specify the dimensions of a brick wall you are building. You are also given a 0-indexed array of unique integers bricks, where the ith brick has a height of 1 and a width of bricks[i]. You have an infinite supply of each type of brick and bricks may not be rotated.

Each row in the wall must be exactly width units long. For the wall to be sturdy, adjacent rows in the wall should not join bricks at the same location, except at the ends of the wall.

Return the number of ways to build a sturdy wall. Since the answer may be very large, return it modulo 109 + 7.

Input: height = 2, width = 3, bricks = [1,2]
Output: 2
Explanation:
The first two walls in the diagram show the only two ways to build a sturdy brick wall.
Note that the third wall in the diagram is not sturdy because adjacent rows join bricks 2 units from the left.
 */
