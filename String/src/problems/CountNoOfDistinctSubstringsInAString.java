package problems;

import java.util.HashSet;
import java.util.Set;

/*
Count number of Distinct Substring in a String
https://www.geeksforgeeks.org/count-number-of-distinct-substring-in-a-string/
https://leetcode.ca/all/1698.html
 */
public class CountNoOfDistinctSubstringsInAString {

	public static int distinctSubstring(String str) {
		// Put all distinct substring in a HashSet
		Set<String> result = new HashSet<String>();

		// List All Substrings
		for (int i = 0; i <= str.length(); i++) {
			for (int j = i + 1; j <= str.length(); j++) {

				// Add each substring in Set
				result.add(str.substring(i, j));
			}
		}

		// Return size of the HashSet
		return result.size();
	}

	// Driver Code
	public static void main(String[] args) {
		String str = "aaaa";
		System.out.println(distinctSubstring(str));
	}
}
