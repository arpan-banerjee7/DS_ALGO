package assignments;

import java.util.Arrays;
import java.util.PriorityQueue;

// 1353. Maximum Number of Events That Can Be Attended

// https://costia.medium.com/leetcode-1353-maximum-number-of-events-that-can-be-attended-medium-7afca19b1292
// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

public class MaxNumberOfEventsAttended {

	// TC- o(n2) TLE solution
	// think of this like this-- an event occurs bw a start date to end date, and we
	// can attend it in any
	// day whithin start and end including it. and we can attend only one event in a
	// day

	// Steps-
	// 1. Sort the events first based on end date and then on start date
	// 2. get the max and min days of the events, that will be the range of days
	// available
	// to us
	// 3. start iterating in the range of days and attend the meetings which ends
	// first

	public int maxEvents(int[][] events) {

		// sort the arrays based on end dates and then on start days
		Arrays.sort(events, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

		// find max and min days from events
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < events.length; i++) {
			min = Math.min(min, events[i][0]);
			max = Math.max(max, events[i][1]);
		}

		// keep an array to keep track of the days where he is
		// already attending a meeting, so we cannot take that day anymore
		// we have to allocate next day for any other left over meetings
		int[] days = new int[max - min + 1];

		// start allocating days, events which ends first will be allocated earlier
		// if there are no days left,then no further meetings can be attended
		int attend = 0;
		for (int i = 0; i < events.length; i++) {
			// iterate over the no days all the events spans through
			for (int j = events[i][0]; j <= events[i][1]; j++) {
				if (days[j - min] == 0) {
					// j th day has been allocated for i th event
					days[j - min] = 1;
					attend++;
					break;
				}
			}
		}
		return attend;
	}

/*-----------------------------------------------------------------------------------------------------------------------*/

	// efficient approach
	// TC -o(nlogn)

	// sort array based on start date first then end day, and process based on whose
	// end date is earlier
	// iterate through the min to max days and put in minHeap the end dates of
	// meetings
	// starting on that day, so that meeting which ends first can be compeleted

	public int maxEvents1(int[][] events) {
		// sort the array based on start date
		Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

		// find max and min days from events
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < events.length; i++) {
			min = Math.min(min, events[i][0]);
			max = Math.max(max, events[i][1]);
		}

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		// count of events
		int attended = 0;
		int j = 0;
		// we are processing all the events for day i
		for (int i = min; i <= max; i++) {

			// take out events whose end date< i, means that day has already been allocated
			// so this event can t be attended
			while (!minHeap.isEmpty() && minHeap.peek() < i) {
				minHeap.poll();
			}

			// adding the end day of all the event having the same start day
			for (; j < events.length && events[j][0] == i; j++) {
				minHeap.add(events[j][1]);
			}

			// allocate days for event
			if (!minHeap.isEmpty()) {
				minHeap.poll();
				attended++;
			}
		}
		return attended;
	}

	public static void main(String[] args) {
		/*
		 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]] Output: 4
		 */

	}

}
