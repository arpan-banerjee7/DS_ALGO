package problems;

// https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring/
// https://iq.opengenus.org/longest-repeating-non-overlapping-substring/ -- brute force
// Leetcode- 1044- https://leetcode.com/problems/longest-duplicate-substring/

public class LongestRepNonOverlapSubstr {

	private static String findLDS(String s) {
		int n = s.length();
		int maxLen = 0;
		String ans = null;

		// generate all subsets [n(n+1)/2]
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				String temp1 = s.substring(i, j);
				for (int k = j; k <= n - (j - i); k++) {
					String temp2 = s.substring(k, k + (j - i));
					if (temp1.equals(temp2)) {
						if (temp1.length() > maxLen) {
							maxLen = temp1.length();
							ans = temp1;
						}
					}
				}
			}
		}

		return maxLen == 0 ? "" : ans;

	}

	public static void main(String[] args) {
		String s = "aabaabaaba";
		System.out.println(findLDS(s));

	}

}
