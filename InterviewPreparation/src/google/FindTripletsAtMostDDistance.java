package google;

import java.util.*;
// https://leetcode.com/discuss/interview-question/4964533/Google-Phone-Interview-Question
/*
There is stream of float values (-inf, inf) which is coming as input and an integer D.


We need to find a set of 3 values which satisfy condition - |a - b| <= D, |b - c| <= D, |a - c| <= D, assuming a,b,c are
3 float values. Print these 3 values and remove them and continue ....


Constraints -
All values in stream will be unique.
D -> [0, inf)


Eg:
Input stream - [1,10,7,-2,8,....], d = 5
Output - (when 8 comes, then print "7 8 10" and remove them and continue)


class Solution {
	private int D;
	void init(int d) {
		this.D = d;
	void func(float item) {
		// implement
	}
}
What data structure should be used here, and what approach can be applied?
 */

// TC O(logN+D), where D is the number of elements within the range.-- range query
// insertion/removal - O(logN)
public class FindTripletsAtMostDDistance {
    private int D;
    private TreeSet<Float> treeSet;

    void init(int d) {
        this.D = d;
        this.treeSet = new TreeSet<>();
    }

    void func(float item) {
        // Add the current item to the TreeSet
        treeSet.add(item);

        // Find all candidates in range [item - D, item + D]
        SortedSet<Float> candidates = treeSet.subSet(item - D, true, item +  D, true);

        if (candidates.size() >= 3) {
            // Convert candidates to list for easy two-pointer manipulation
            List<Float> candidateList = new ArrayList<>(candidates);
            int left = 0;
            int right = 2;

            while (right < candidateList.size()) {
                float a = candidateList.get(left);
                float b = candidateList.get(left + 1);
                float c = candidateList.get(right);

                if (Math.abs(a - b) <= D && Math.abs(b - c) <= D && Math.abs(a - c) <= D) {
                    // Print the valid triplet
                    System.out.println(a + " " + b + " " + c);

                    // Remove the triplet from TreeSet and adjust the pointers
                    treeSet.remove(a);
                    treeSet.remove(b);
                    treeSet.remove(c);
                    return;  // Restart after removing the triplet
                }

                // Slide the window
                left++;
                right++;
            }
        }
    }

    public static void main(String[] args) {
        FindTripletsAtMostDDistance solution = new FindTripletsAtMostDDistance();
        solution.init(2); // D = 5

        // Simulating a stream of float values
//        float[] stream = {1, 10, 7, -2, 8};
//        for (float item : stream) {
//            solution.func(item);
//        }
//        float[] stream1 = { 9, 10, 14, 18, 13}; //d=5
//        for (float item : stream1) {
//            solution.func(item);
//        }

        float[] stream2 = {7, 8, 17, 9};// only left right wn t work
        for (float item : stream2) {
            solution.func(item);
        }
    }
}
