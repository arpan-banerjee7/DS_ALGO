package problems;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/sliding-window-maximum/
// https://www.youtube.com/watch?v=xFJXtB5vSmM&lc=z23idrcigkuzthd5504t1aokgb1qrylprxrm3k4c0prdrk0h00410.1630858689113407
// https://leetcode.com/problems/merge-k-sorted-lists/discuss/10528/A-java-solution-based-on-Priority-Queue

//Dequeue solution
// TC- o(n)
// space o(n)

// TreeMap/Heap- o(n*logk)
public class SlidingWindowMaximum {
	public static int[] maxSlidingWindow(int[] nums, int k) {

		// maintain a monotonic decreasing queue
		// all smaller elements before end pointer are of no use
		// so remove them.
		int n = nums.length;
		int idx = 0;
		int start = 0;
		int[] res = new int[n - k + 1];
		Deque<Integer> dq = new LinkedList<>();

		for (int end = 0; end < n; end++) {
			// desc order
			while (!dq.isEmpty() && dq.peekLast() < nums[end]) {
				dq.pollLast();
			}
			dq.add(nums[end]);

			if (end - start + 1 == k) {
				res[idx++] = dq.peekFirst();

				if (dq.peekFirst() == nums[start]) {
					dq.pollFirst();
				}
				start++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		int[] ans = maxSlidingWindow(nums, k);
		for (int i : ans)
			System.out.print(i + " ");
	}
}
