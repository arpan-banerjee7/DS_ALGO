package problems;

import java.util.PriorityQueue;

// https://www.youtube.com/watch?v=XKA22PecuMQ
// https://leetcode.com/problems/process-tasks-using-servers/
// https://leetcode.com/problems/process-tasks-using-servers/discuss/1239780/Java-Two-heaps-time%3A-O((N%2BM)*logN).-Detailed-explanation

// TC- O((N+M)*logN)
public class ProcessTasksUsingServers {

	public static int[] assignTasks(int[] servers, int[] tasks) {
		int[] res = new int[tasks.length];
		int idx = 0;
		// [weight,index]
		PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);

		// [timeToBeFree,weight,index]
		PriorityQueue<int[]> working = new PriorityQueue<>((a, b) -> {
			if (a[0] != b[0]) {
				return a[0] - b[0];
			} else {
				if (a[1] != b[1]) {
					return a[1] - b[1];
				} else {
					return a[2] - b[2];
				}
			}

		});

		// fill the free queue first and then start processing
		for (int i = 0; i < servers.length; i++) {
			free.add(new int[] { 0, servers[i], i });
		}
		// processing start
		for (int i = 0; i < tasks.length; i++) {
			while (!working.isEmpty() && working.peek()[0] <= i) {
				free.add(working.poll());
			}
			if (!free.isEmpty()) {
				int[] curr = free.poll();
				res[idx++] = curr[2];
				curr[0] = i + tasks[i];
				working.add(curr);
			} else {
				int[] curr = working.poll();
				res[idx++] = curr[2];
				curr[0] += tasks[i];
				working.add(curr);
			}

		}
		return res;
	}

	public static void main(String[] args) {
		int[] servers = { 3, 3, 2 };
		int[] tasks = { 1, 2, 3, 2, 1, 2 };

		int[] res = assignTasks(servers, tasks);
		for (int i : res)
			System.out.print(i + " ");

	}

}
