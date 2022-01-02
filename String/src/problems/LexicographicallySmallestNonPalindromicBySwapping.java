package problems;

import java.util.Arrays;

//  https://www.geeksforgeeks.org/make-the-string-lexicographically-smallest-and-non-palindromic-by-swapping-of-adjacent-pair-of-characters/

public class LexicographicallySmallestNonPalindromicBySwapping {

	// reverse string
	static String reverse(String input) {
		char[] a = input.toCharArray();
		int l, r = a.length - 1;
		for (l = 0; l < r; l++, r--) {
			char temp = a[l];
			a[l] = a[r];
			a[r] = temp;
		}
		return String.valueOf(a);
	}

	// Method to sort a string alphabetically
	static String sortString(String inputString) {
		// convert input string to char array
		char tempArray[] = inputString.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}

	// Function to find the lexographically
	// smallest String which is non-palindrome
	static void smallestNonPalindromic(String s) {
		// Sort the given String
		s = sortString(s);

		// Store reverse of sorted String
		String reverseString = reverse(s);

		// Check if the sorted String is
		// palindromic or not
		if (s != reverseString) {
			System.out.print(s);
		}

		else {
			System.out.print("-1");
		}
	}

	// Driver Code
	public static void main(String[] args) {
		// Given String str
		String str = "asmfjdeovnhekfnj";

		// Function Call
		smallestNonPalindromic(str);
	}

}

