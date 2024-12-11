package segmentTree;

import java.util.*;

// 2158. Amount of New Area Painted Each Day
// https://leetcode.com/problems/amount-of-new-area-painted-each-day/description/?envType=problem-list-v2&envId=segment-tree
class SegmentTree {
    Map<Integer, Integer> vals = new HashMap();

    void update(int idx, int low, int high, int l, int r) {
        // no overlap
        // 1 4   4 5
        if (r < low || l > high) {
            return;
        }

        // complete overlap
        // l low high r
        if (low >= l && high <= r) {
            vals.put(idx, (high - low) + 1);
            return;
        }

        // partial overlap
        int mid = (low + high) >> 1;

        update(2 * idx + 1, low, mid, l, r);
        update(2 * idx + 2, mid + 1, high, l, r);

        // imp step--dry run this test case-[[1,5],[2,4]], correct output- [4,0]
        // without max check [4,-1]
        // it happens when the next range update covers a lesser area than the earlier range update, so the new max
        // area painted will not increase.
        int sum = vals.getOrDefault(2 * idx + 1, 0) + vals.getOrDefault(2 * idx + 2, 0);
        vals.put(idx, Math.max(vals.getOrDefault(idx, 0), sum));

    }
}

public class AmountOfNewAreaPaintedEachDay {
    public int[] amountPainted(int[][] paint) {
        SegmentTree segmentTree = new SegmentTree();
        int[] ans = new int[paint.length];

        int i = 0;
        int paintedSoFar = 0;
        for (int[] paintArea : paint) {
            segmentTree.update(0, 0, 100000, paintArea[0], paintArea[1] - 1);
            ans[i++] = segmentTree.vals.get(0) - paintedSoFar;
            paintedSoFar = segmentTree.vals.get(0);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] paint = {{1, 4}, {4, 7}, {5, 8}};
        AmountOfNewAreaPaintedEachDay am = new AmountOfNewAreaPaintedEachDay();
        int[] ans = am.amountPainted(paint);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
/*
2158. Amount of New Area Painted Each Day
There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array
paint of length n, where paint[i] = [starti, endi]. This means that on the ith day you need to paint the area between starti and endi.

Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting
 at most once.

Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.

Input: paint = [[1,4],[4,7],[5,8]]
Output: [3,3,1]
Explanation:
On day 0, paint everything between 1 and 4.
The amount of new area painted on day 0 is 4 - 1 = 3.
On day 1, paint everything between 4 and 7.
The amount of new area painted on day 1 is 7 - 4 = 3.
On day 2, paint everything between 7 and 8.
Everything between 5 and 7 was already painted on day 1.
The amount of new area painted on day 2 is 8 - 7 = 1.
 */
