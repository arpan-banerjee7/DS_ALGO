package kmp;

// https://leetcode.com/problems/shortest-palindrome/
public class ShortestPalindrome {

	// logic- find longest prefix which is palindrome(LPP)-- use KMP ans=lps[n-1]
	// number of characters need to be added = s.length()- LPP.length()
	// take a substring() from that many chars and append it to the front of the
	// string

	// KMP solution
	public static String shortestPalindrome(String s) {
		int n = s.length();
		int noOfCharsNeeded = 0;

		// aacecaaa$aaacecaa
		StringBuilder s1 = new StringBuilder(s);
		s1.append("$");
		s1.append(new StringBuilder(s).reverse().toString());

		// build lps array using KMP algo
		int[] lps = new int[s1.length()];
		lps[0] = 0;
		for (int i = 1; i < s1.length(); i++) {
			int x = lps[i - 1];
			while (s1.charAt(i) != s1.charAt(x)) {
				if (x == 0) {
					x = -1;// we need lps[0] as 0
					break;
				}
				x = lps[x - 1];
			}
			lps[i] = x + 1;
		}
		noOfCharsNeeded = n - lps[s1.length() - 1];
		StringBuilder finalAns = new StringBuilder(s.substring(n - noOfCharsNeeded));
		return finalAns.reverse().append(s).toString();

	}

	public static void main(String[] args) {
		String s = "aacecaaa";
		System.out.println(shortestPalindrome(s)); /// Output: "aaacecaaa"

	}

}
