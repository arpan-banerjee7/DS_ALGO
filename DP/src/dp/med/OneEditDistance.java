package dp.med;

// https://www.geeksforgeeks.org/check-if-two-given-strings-are-at-edit-distance-one/
//Ref: https://www.youtube.com/watch?v=CsP1an6DDzo

//Lintcode 640 · One Edit Distance

public class OneEditDistance {
	// between s1 and s2 is one, else false
	static boolean isEditDistanceOne(String s1, String s2) {
		// Find lengths of given strings
		int m = s1.length(), n = s2.length();

		// If difference between lengths is
		// more than 1, then strings can't
		// be at one distance
		if (Math.abs(m - n) > 1)
			return false;

		int count = 0; // Count of edits

		int i = 0, j = 0;
		while (i < m && j < n) {
			// If current characters don't match
			if (s1.charAt(i) != s2.charAt(j)) {
				if (count == 1)
					return false;

				// If length of one string is
				// more, then only possible edit
				// is to remove a character
				if (m > n)
					i++;
				else if (m < n)
					j++;
				else // Iflengths of both strings
						// is same
				{
					i++;
					j++;
				}

				// Increment count of edits
				count++;
			}

			else // If current characters match
			{
				i++;
				j++;
			}
		}

		// If last character is extra
		// in any string
		if (i < m || j < n)
			count++;

		return count == 1;
	}

	public boolean isOneEditDistance(String s, String t) {
		int n1 = s.length();
		int n2 = t.length();

		if (n1 > n2)
			return isOneEditDistance(t, s);

		for (int i = 0; i < n1; i++) {
			if (s.charAt(i) != t.charAt(i)) {

				if (n1 == n2)
					return s.substring(i + 1).equals(t.substring(i + 1));
				else
					return s.substring(i).equals(t.substring(i + 1));
			}
		}

		return (n1 + 1 == n2);
	}

	// driver code
	public static void main(String[] args) {
		String s1 = "gfg";
		String s2 = "gf";
		if (isEditDistanceOne(s1, s2))
			System.out.print("Yes");
		else
			System.out.print("No");
	}

}
