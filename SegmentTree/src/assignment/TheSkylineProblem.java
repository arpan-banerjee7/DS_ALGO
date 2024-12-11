package segmentTree;

import java.util.*;
// 218. The Skyline Problem
// https://leetcode.com/problems/the-skyline-problem/description/
/*
Range Max Segment tree with Lazy Propagation for updates, as well as coordinate compression to accommodate larger ranges.

1. Understanding the Problem
Given buildings with specific ranges and heights, we need to return the outer contour of the skyline,
which means the highest points in the silhouette formed by all buildings.

Each building is represented as an interval [left, right] with a height. The skyline consists of
points where there’s a change in height.

2. Observations
The skyline changes at the start (left) or end (right) of a building.
We need to find the maximum height at each x-coordinate covered by buildings.
We want to be efficient in updating ranges (from left to right) with a height and querying the maximum height at
each x-coordinate.

3. Segment Tree for Range Updates and Queries
A segment tree can be used here to handle:

Range updates: Update a range of x-coordinates with the building height.
Range queries: Query the maximum height in a range to identify changes in the skyline.
The segment tree will allow us to efficiently update height ranges and query maximum heights over intervals.

IMPLEMENTATION DETAILS

1) First we will do coordinate compression.
As building coordinates can represent a wide range of start and end points
We create a sorted list of unique x-coordinates (sortedCoords) and map each x-coordinate to an index (coordMap).
This lets us use a smaller range for segment tree operations.

2) Update ranges in segment tree
We iterate over the buildings array, map the range to our compressed coordinate and then update that range with max in the segment
tree.

3) After updating all buildings, we traverse each x-coordinate in sortedCoords and query the segment tree for the maximum height at that x-coordinate.
If the height at a point is different from the previous height, we add a new point [x, height] to the result, marking a change in the skyline.
*/
// range max query

class STree {
    private int[] tree, lazy;
    private int size;

    public STree(int size) {
        this.size = size;
        this.tree = new int[4 * size];
        this.lazy = new int[4 * size];
    }

    // range max segment tree with lazy propagation(for each range of building we keep track of the max heights)
    public void update(int start, int end, int height, int idx, int left, int right) {
        if (lazy[idx] != 0) {
            tree[idx] = Math.max(tree[idx], lazy[idx]);
            if (left != right) { // propagate to children
                lazy[2 * idx + 1] = Math.max(lazy[2 * idx + 1], lazy[idx]);
                lazy[2 * idx + 2] = Math.max(lazy[2 * idx + 2], lazy[idx]);
            }
            lazy[idx] = 0; // clear lazy value after propagation
        }
        if (left > end || right < start) {
            return;
        }
        if (left >= start && right <= end) {
            tree[idx] = Math.max(tree[idx], height);
            if (left != right) { // propagate to children
                lazy[2 * idx + 1] = Math.max(lazy[2 * idx + 1], height);
                lazy[2 * idx + 2] = Math.max(lazy[2 * idx + 2], height);
            }
            return;
        }
        int mid = left + (right - left) / 2;
        update(start, end, height, 2 * idx + 1, left, mid);
        update(start, end, height, 2 * idx + 2, mid + 1, right);
        tree[idx] = Math.max(tree[2 * idx + 1], tree[2 * idx + 2]);
    }

    // Query for a specific point in segment tree
    public int query(int pos, int idx, int left, int right) {
        if (lazy[idx] != 0) {
            tree[idx] = Math.max(tree[idx], lazy[idx]);
            if (left != right) { // propagate to children
                lazy[2 * idx + 1] = Math.max(lazy[2 * idx + 1], lazy[idx]);
                lazy[2 * idx + 2] = Math.max(lazy[2 * idx + 2], lazy[idx]);
            }
            lazy[idx] = 0; // clear lazy value after propagation
        }
        if (left == right) { // If this is a leaf node
            return tree[idx];
        }
        int mid = left + (right - left) / 2;
        if (pos <= mid) {
            return query(pos, 2 * idx + 1, left, mid);
        } else {
            return query(pos, 2 * idx + 2, mid + 1, right);
        }
    }
}

public class TheSkyLineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Set<Integer> coords = new HashSet<>();
        for (int[] building : buildings) {
            coords.add(building[0]);
            coords.add(building[1]);
        }
        List<Integer> sortedCoords = new ArrayList<>(coords);
        Collections.sort(sortedCoords);

        Map<Integer, Integer> coordMap = new HashMap<>();
        for (int i = 0; i < sortedCoords.size(); i++) {
            coordMap.put(sortedCoords.get(i), i);
        }

        int n = sortedCoords.size();
        STree segTree = new STree(n);

        for (int[] building : buildings) {
            int left = coordMap.get(building[0]);
            int right = coordMap.get(building[1]) - 1; // exclude the right index
            segTree.update(left, right, building[2], 0, 0, n - 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        int prevHeight = 0;
        for (int x : sortedCoords) {
            int currentHeight = segTree.query(coordMap.get(x), 0, 0, n - 1);
            if (currentHeight != prevHeight) {
                result.add(Arrays.asList(x, currentHeight));
                prevHeight = currentHeight;
            }
        }
        return result;
    }

}
/*
Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 */