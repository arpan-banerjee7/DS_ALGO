package assignments;

import java.util.Arrays;

// N meetings in one room-
// https://www.youtube.com/watch?v=II6ziNnub1Q&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=2 Striver	
// https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1#

public class NMeetingsInOneRoom {
	// Function to find the maximum number of meetings that can
	// be performed in a meeting room.
	public static int maxMeetings(int start[], int end[], int n) {
		// add your code here
		int[][] meetings = new int[n][2];
		for (int i = 0; i < n; i++) {
			meetings[i][0] = start[i];
			meetings[i][1] = end[i];
		}

		// sorted as per end date
		Arrays.sort(meetings, (a, b) -> a[1] - b[1]);
		int meetingEnd = Integer.MIN_VALUE;
		int count = 0;
		for (int[] m : meetings) {

			if (m[0] > meetingEnd) {
				count++;
				meetingEnd = m[1];
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int N = 6;
		int start[] = { 1, 3, 0, 5, 8, 5 };
		int end[] = { 2, 4, 6, 7, 9, 9 };
		System.out.println(maxMeetings(start, end, N));
		/*
		 * Output: 4 Explanation: Maximum four meetings can be held with given start and
		 * end timings. The meetings are - (1, 2),(3, 4), (5,7) and (8,9)
		 */

	}

}
