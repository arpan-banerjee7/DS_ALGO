package assignments;

import java.util.ArrayList;
import java.util.Arrays;
/*
================================================
919 · Meeting Rooms II (Lintcode 919) [Leetcode 253]
================================================
Description
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
Example
Example1
Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
Example2
Input: intervals = [(2,7)]
Output: 1
Explanation: 
Only need one meeting room
Tags
====================================================================================
====================================================================================
*/

// https://www.interviewbit.com/problems/meeting-rooms/
// intuition- https://www.youtube.com/watch?v=FdzJmTCVyJU

public class MeetingRoomII {
	public class Solution {
		public int solve(ArrayList<ArrayList<Integer>> A) {
			int n = A.size();
			int[] start = new int[n];
			int[] end = new int[n];
			int k = 0;
			int l = 0;
			for (int i = 0; i < n; i++) {
				start[k++] = A.get(i).get(0);
				end[l++] = A.get(i).get(1);
			}
			Arrays.sort(start);
			Arrays.sort(end);
			int temp = 0;
			int rooms = 0;
			int i = 0;
			int j = 0;
			while (i < start.length && j < end.length) {
				if (start[i] < end[j]) {
					i++;
					temp++;
				} else {
					j++;
					temp--;
				}
				rooms = Math.max(temp, rooms);
			}

			return rooms;
		}

	}

}
