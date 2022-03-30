package assignments;

// 1326. Minimum Number of Taps to Open to Water a Garden
// https://www.youtube.com/watch?v=Pk128gC_sdw&t=561s
// Similar questions jump game 1, jump game 2

public class MinTapsToWaterAGarden {
	public static int minTaps(int n, int[] ranges) {
		// we need to find a tap which can water from 0
		int min = 0; // garden starts from 0
		int max = 0;
		int taps = 0;

		int index = 0;
		while (max < n) {

			// in each iteration we will find a tap, which covers
			// <=min limit and >= max
			// so ideal best tap will be found in each iteration
			// doing this we will start moving forward
			// and anytime we reach max we have covered the entire garden we stop
			for (int i = index; i <= n; i++) {
				if (i - ranges[i] <= min && i + ranges[i] >= max) {
					max = i + ranges[i];
					index++;
				}
			}
			if (min == max)
				return -1;

			taps++;
			min = max; // start finding tap which can cover the rest
		}
		return taps;
	}

	public static void main(String[] args) {
		int[] ranges= {3,2,1,1,0,0};
		System.out.println(minTaps(5, ranges));
		/*Input: n = 5, ranges = [3,4,1,1,0,0]
				Output: 1
				Explanation: The tap at point 0 can cover the interval [-3,3]
				The tap at point 1 can cover the interval [-3,5]
				The tap at point 2 can cover the interval [1,3]
				The tap at point 3 can cover the interval [2,4]
				The tap at point 4 can cover the interval [4,4]
				The tap at point 5 can cover the interval [5,5]
				Opening Only the second tap will water the whole garden [0,5]
				*/
	}

}
