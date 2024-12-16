package uber;
// 1428. Leftmost Column with at Least a One
// https://www.youtube.com/watch?v=K2E5fMMAf5U
import java.util.*;

interface BinaryMatrix {
    public int get(int row, int col);

    public List<Integer> dimensions();
};

public class LeftMostColumnWithAtLeastOne {

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int smallestIndex = cols;
        for (int row = 0; row < rows; row++) {
            // Binary Search for the first 1 in the row.
            int lo = 0;
            int hi = cols - 1;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (binaryMatrix.get(row, mid) == 0) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            // If the last element in the search space is a 1, then this row
            // contained a 1.
            if (binaryMatrix.get(row, lo) == 1) {
                smallestIndex = Math.min(smallestIndex, lo);
            }
        }
        // If smallest_index is still set to cols, then there were no 1's in
        // the grid.
        return smallestIndex == cols ? -1 : smallestIndex;
    }

}

/*
// ladder approach TC - O(M+N)
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);

        // start from bottom-right corner. keep moving left if we see 1 to get the first occurance of 1 in that row
        // else move up
        int lastRow = rows - 1;
        int lastCol = cols - 1;
        int res=-1;

        // Repeat the search until it goes off the grid.
        while(lastRow>=0 && lastCol>=0){
            if(binaryMatrix.get(lastRow, lastCol)==1){
                res=lastCol;
                lastCol-=1;// keep moving left until you find a one
            }else{
                lastRow-=1;// move up
            }
        }

        return res;
    }
}
 */
/*
A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
Input: mat = [[0,0],[1,1]]
Output: 0
Input: mat = [[0,0],[0,1]]
Output: 1

 */