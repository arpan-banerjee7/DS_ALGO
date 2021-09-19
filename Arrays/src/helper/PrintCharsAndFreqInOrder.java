package helper;

// https://www.geeksforgeeks.org/print-characters-frequencies-order-occurrence/

public class PrintCharsAndFreqInOrder {
	// Java implementation to print the character and
	// its frequency in order of its occurrence

	static final int SIZE = 26;

	// function to print the character and its
	// frequency in order of its occurrence
	static void printCharWithFreq(String str) {
		// size of the string 'str'
		int n = str.length();

		// 'freq[]' implemented as hash table
		int[] freq = new int[SIZE];

		// accumulate frequency of each character
		// in 'str'
		for (int i = 0; i < n; i++)
			freq[str.charAt(i) - 'a']++;

		// traverse 'str' from left to right
		for (int i = 0; i < n; i++) {

			// if frequency of character str.charAt(i)
			// is not equal to 0
			if (freq[str.charAt(i) - 'a'] != 0) {

				// print the character along with its
				// frequency
				System.out.print(str.charAt(i));
				System.out.print(freq[str.charAt(i) - 'a'] + " ");

				// update frequency of str.charAt(i) to
				// 0 so that the same character is not
				// printed again
				freq[str.charAt(i) - 'a'] = 0;
			}
		}
	}

	// Driver program to test above
	public static void main(String args[]) {
		String str = "geeksforgeeks";
		printCharWithFreq(str);
	}
}
