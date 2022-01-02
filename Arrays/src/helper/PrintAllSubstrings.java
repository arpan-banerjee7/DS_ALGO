package helper;

// https://www.geeksforgeeks.org/program-print-substrings-given-string/

public class PrintAllSubstrings {

	public static void SubString(String str, int n) {
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)

				// Please refer below article for details
				// of substr in Java
				// https://www.geeksforgeeks.org/java-lang-string-substring-java/
				System.out.println(str.substring(i, j));
	}

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
