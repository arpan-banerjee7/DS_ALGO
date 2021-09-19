package helper;

// https://www.geeksforgeeks.org/program-print-substrings-given-string/

public class PrintAllSubstrings {

	// Function to print all sub strings
	static void subString(char str[], int n) {
		// Pick starting point
		for (int len = 1; len <= n; len++) {
			// Pick ending point
			for (int i = 0; i <= n - len; i++) {
				// Print characters from current
				// starting point to current ending
				// point.
				int j = i + len - 1;
				for (int k = i; k <= j; k++) {
					System.out.print(str[k]);
				}

				System.out.println();
			}
		}
	}

	// Driver program to test above function
	public static void main(String[] args) {
		char str[] = { 'a', 'b', 'c' };
		subString(str, str.length);

	}
}
