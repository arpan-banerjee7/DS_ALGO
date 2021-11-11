package dp.mcm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/scramble-string/
// https://www.youtube.com/watch?v=SqA0o-DGmEw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=40
// https://www.geeksforgeeks.org/check-if-a-string-is-a-scrambled-form-of-another-string/

/* further optimization, check if s1 and s2 are not anagrams, if they are not anagrams then definitely 
 they are not scrambled strings*/

public class ScrambleString {
	Map<String, Boolean> dp;

	private boolean solve(String s1, String s2) {
		if (s1.equals(s2)) {
			return true;
		}
		if (s1.length() != s2.length()) {
			return false;
		}

		int n = s1.length();
		// check if one of them is empty
		// not necessary to check both as we have already compared the lengths
		if (n <= 1) {
			return false;
		}
		String key = s1 + " " + s2;
		if (dp.containsKey(key)) {
			return dp.get(key);
		}
		if (!araAnagrams(s1, s2)) {
			return false;
		}

		boolean flag = false;
		for (int i = 1; i < n; i++) {
			// two cases- no swap or swap
			boolean noSwap = (solve(s1.substring(0, i), s2.substring(0, i)) && solve(s1.substring(i), s2.substring(i)));
			boolean swap = (solve(s1.substring(0, i), s2.substring(n - i))
					&& solve(s1.substring(i), s2.substring(0, n - i)));
			if (swap || noSwap) {
				flag = true;
				// not setting dp here, we need the false values and we need to check for all
				// partitions,wg-- ategr and eatgr>> ate and eat-> check for 1=1, then for i=2
				// its true
			}
		}
		dp.put(key, flag);
		return flag;

	}

	private boolean araAnagrams(String s1, String s2) {
		// converting strings to char arrays
		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();

		// sorting both char arrays
		Arrays.sort(ch1);
		Arrays.sort(ch2);

		// Check for equality of strings
		if (Arrays.equals(ch1, ch2))
			return true;
		else
			return false;
	}

	public boolean isScramble(String s1, String s2) {
		dp = new HashMap<String, Boolean>();
		return solve(s1, s2);
	}

	public static void main(String[] args) {
		String s1 = "great", s2 = "rgeat";
		ScrambleString obj = new ScrambleString();
		System.out.println(obj.isScramble(s1, s2));// Output: true
	}

}
