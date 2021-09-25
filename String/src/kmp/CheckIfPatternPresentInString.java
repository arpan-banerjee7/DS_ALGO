package kmp;

// https://www.geeksforgeeks.org/check-if-a-given-pattern-exists-in-a-given-string-or-not/

public class CheckIfPatternPresentInString {
	// JAVA program for implementation of KMP pattern
	// searching algorithm

	static void KMPSearchAlgo(String pat, String txt) {
		int m = pat.length();
		int n = txt.length();

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[m + n + 1];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)

		StringBuffer s = new StringBuffer(m + n + 1);
		s.append(pat);
		s.append("$");
		s.append(txt);
		computeLPSArray(s.toString(), lps);
		System.out.println(s);
		Boolean flag = false;
		for (int i = 0; i < m + n + 1; i++) {
			if (m == lps[i]) {
				flag = true;
			}

		}
		if (flag == true) {
			System.out.println("present");
		} else {
			System.out.println("NO");
		}

	}

	static void computeLPSArray(String pat, int lps[]) {
		int n = pat.length();

		int x = 0;

		// KMP for finding LPS array
		for (int i = 1; i < n; i++) {
			x = lps[i - 1];
			while (pat.charAt(i) != pat.charAt(x)) {
				if (x == 0) {
					x = -1;
					break;
				} else {
					x = lps[x - 1];
				}
			}
			lps[i] = x + 1;
		}

	}

	// Driver program to test above function
	public static void main(String args[]) {
		String txt = "abcded";
		String pat = "a";
		KMPSearchAlgo(pat, txt);
	}
}
