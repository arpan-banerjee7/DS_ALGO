package assignments;

import java.util.Arrays;

// https://www.youtube.com/watch?v=sTEPmlWBmJU   Naresh Gupta
// https://leetcode.com/problems/task-scheduler/
/*
 * Look for TC explanation at end, its o(n) and nt o(nlogn) since as per the constraints
 * n is very high compared to 26
 */
// Leetcode 621. Task Scheduler : https://leetcode.com/problems/task-scheduler/

public class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {

		if (n == 0)
			return tasks.length;
		// calculate the frequency of each tasks
		int[] freq = new int[26]; // since there are only 26 upper case characters
		for (char t : tasks) {
			freq[t - 'A']++;
		}
		Arrays.sort(freq);
		// find out the chunk size from max freq
		int chunk = freq[25] - 1;

		// calculate idle spots, it depends on chunk size and n
		// chunk is nothing by placing common characters keeping a gap
		// and number of elements that can be filed in that
		// gap depends on n
		// since that gap represents the idle spots, i.e when the cpu needs
		// to rest after completing A and before starting A again
		// but it can do other task B in bw

		int idleSpots = chunk * n;

		// start filling the idle spots
		for (int i = 24; i >= 0; i--) {

			// suppose we have A--A--A,[ 2 chunks(4 idleSpots) and 3 B's]
			// we can only fill 2 B's here, sinc after one B there should be
			// a cool down period of 2,so to accomodate that this formula is used
			idleSpots -= Math.min(chunk, freq[i]);
		}
		return idleSpots < 0 ? tasks.length : idleSpots + tasks.length;
	}
}
