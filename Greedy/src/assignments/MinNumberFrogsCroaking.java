package assignments;

// 1419. Minimum Number of Frogs Croaking

/*
same as meeting rooms 2 and min no of platforms, but implentation is a bit difficult
	https://leetcode.com/problems/minimum-number-of-frogs-croaking/
code-1- https://leetcode.com/problems/minimum-number-of-frogs-croaking/discuss/1127569/Java-or-greedy-or-explained-and-with-picture-illustration
code 2- https://leetcode.com/problems/minimum-number-of-frogs-croaking/discuss/1569341/Java-or-Greedy-or-Simple-or-Beats-100-or-Commented
*/
public class MinNumberFrogsCroaking {
	public static int minNumberOfFrogs(String croakOfFrogs) {
		int[] letterCounts = new int[26];
		int count = 0;
		int maxCount = 0;

		for (char c : croakOfFrogs.toCharArray()) {
			letterCounts[c - 'a']++;
			// do boundary count
			if (c == 'c') { // left boundary
				count++;
				maxCount = Math.max(maxCount, count);
			} else {
				if (!isValid(c, letterCounts))
					return -1;
				if (c == 'k') { // right boundary
					count--;
				}
			}
		}
		// check if the string is finished
		return count == 0 ? maxCount : -1;
	}

	// check string validity
	private static boolean isValid(char currChar, int[] counts) {
		char prevChar;
		if (currChar == 'r')
			prevChar = 'c';
		else if (currChar == 'o')
			prevChar = 'r';
		else if (currChar == 'a')
			prevChar = 'o';
		else
			prevChar = 'a'; // before k anything can come c,r,o,a

		return counts[prevChar - 'a'] >= counts[currChar - 'a'];
	}

	public int minNumberOfFrogs2(String croakOfFrogs) {
		// We don't need 'k' because we decrement the possible number of
		// croaking frogs when we encounter it, as a frog would have finished croaking.
		int c = 0, r = 0, o = 0, a = 0;
		int maxNumFrogs = 0;
		for (char ch : croakOfFrogs.toCharArray()) {
			switch (ch) {
			case 'c':
				c++;
				// Because we decrement 'c' on finishing a croak,
				// max of 'c' would indicate the maximum overlap
				// i.e. min number of frogs croaking simultaneously.
				maxNumFrogs = Math.max(maxNumFrogs, c);
				break;
			case 'r':
				if (c > r) { // Some frog would have started croaking
					r++;
				} else {
					return -1;
				}
				break;
			case 'o':
				if (r > o) {
					o++;
				} else {
					return -1;
				}
				break;
			case 'a':
				if (o > a) {
					a++;
				} else {
					return -1;
				}
				break;
			default: // We encountered 'k', so, finish a 'croak'
				c--;
				r--;
				o--;
				a--;
				break;
			}
		}
		// c > 0 would inicate an unfinished 'croak'
		return c > 0 ? -1 : maxNumFrogs;
	}

	public static void main(String[] args) {
		String croakOfFrogs = "croakcroak";
		System.out.println(minNumberOfFrogs(croakOfFrogs));
		// Output: 1
		// Explanation: One frog yelling "croak" twice.
	}

}
/*
 * "croakcroa" "aoocrrackk"
 * 
 * own test case- "crrrk" for this case 'r':
 */
/*
                    if (c > r) { // Some frog would have started croaking
                        r++;
                    }else {
                        return -1;
                    }
                    break;
*/
