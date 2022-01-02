package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 218. The Skyline Problem
// To Do using segment tree
// https://leetcode.com/problems/the-skyline-problem/
// https://www.youtube.com/watch?v=POUMNJou4vc

public class TheSkylineProblem {
	public List<List<Integer>> getSkyline(int[][] buildings) {
		int n = buildings.length;
		List<List<Integer>> res = new ArrayList<>();

		int[][] posArr = new int[2 * n][2];
		int posIndex = 0;

		// separate out end and starting points
		for (int[] building : buildings) {
			// start point and height(-ve)
			posArr[posIndex][0] = building[0];
			posArr[posIndex][1] = -building[2];
			posIndex++;

			// end point and height(+ve)
			posArr[posIndex][0] = building[1];
			posArr[posIndex][1] = building[2];
			posIndex++;
		}

		// now, we have only two points start and height(+ve/-ve) to determine start or
		// end points
		// sort based on start points, if same heights in descending

		Arrays.sort(posArr,
				(a, b) -> Integer.compare(a[0], b[0]) == 0 ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		// to get the max height of the active rectangle
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
		int currHeight = 0;
		maxHeap.add(0);

		for (int i = 0; i < 2 * n; i++) {
			int nextHeight = posArr[i][1];

			// if its the start of a building
			if (nextHeight < 0) {
				maxHeap.add(-(nextHeight));
			} else {
				maxHeap.remove(nextHeight);
			}

			// if the max height of the current active rectangle is != max height of active
			// box, means
			// there is a change, we will include that in out ans
			if (currHeight != maxHeap.peek()) {
				res.add(Arrays.asList(posArr[i][0], maxHeap.peek()));
				currHeight = maxHeap.peek();
			}
		}
		return res;

	}

	public static void main(String[] args) {
		/*
		 * Sample test cases- [[0,5,15],[2,5,10],[5,8,10],[6,8,15],[8,9,10]]
		 */

		/*
		 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]] Output:
		 * [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]] Explanation: Figure A
		 * shows the buildings of the input. Figure B shows the skyline formed by those
		 * buildings. The red points in figure B represent the key points in the output
		 * list.
		 */
	}

}
