package microsoft;

// 754. Reach a Number
// https://leetcode.com/problems/reach-a-number/
// https://www.youtube.com/watch?v=F23zJlz__SM

public class ReachANumber {
	private int reachTarget(int target, int source, int steps) {
		if (Math.abs(source) > target)
			return Integer.MAX_VALUE;

		if (Math.abs(source) == target)
			return steps;

		return Math.min(reachTarget(target, source + steps + 1, steps + 1),
				reachTarget(target, source - steps - 1, steps + 1));
	}

	public int reachNumber(int target) {
		// since we are standing at 0, so +target and -taregt would require same number
		// of steps
		return reachTarget(Math.abs(target), 0, 0);
	}

	public static void main(String[] args) {
		/*
		 * Input: target = 2 Output: 3 Explanation: On the 1st move, we step from 0 to 1
		 * (1 step). On the 2nd move, we step from 1 to -1 (2 steps). On the 3rd move,
		 * we step from -1 to 2 (3 steps).
		 */
	}

}
