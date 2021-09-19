package kmp;

// https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/
// todo - brute force sol

public class MinCharsToBeAddedFrontToMakePalindrome {

	// Java program for getting minimum character to be
	// added at front to make string palindrome

	// returns vector lps for given string str
	public static int[] computeLPSArray(String s) {
		int n = s.length();
		int[] lps = new int[n];
		lps[0] = 0;
		for (int i = 1; i < n; i++) {
			int x = lps[i - 1];
			while (s.charAt(x) != s.charAt(i)) {
				if (x == 0) {
					x = -1;
					break;
				}
				x = lps[x - 1];
			}
			lps[i] = x + 1;
		}
		return lps;
	}

	// Method returns minimum character to be added at
	// front to make string palindrome
	static int getMinCharToAddedToMakeStringPalin(String str) {
		StringBuilder s = new StringBuilder();
		s.append(str);

		// Get concatenation of string, special character
		// and reverse string
		String rev = s.reverse().toString();
		s.reverse().append("$").append(rev);

		// Get LPS array of this concatenated string
		int lps[] = computeLPSArray(s.toString());
		return str.length() - lps[s.length() - 1];
	}

	// Driver Code
	public static void main(String args[]) {
		String str = "AACECAAAA";
		System.out.println(getMinCharToAddedToMakeStringPalin(str)); // output - 2
	}
}
