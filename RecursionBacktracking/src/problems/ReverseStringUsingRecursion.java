package problems;

// https://www.studytonight.com/java-programs/java-program-to-reverse-a-string-using-recursion

public class ReverseStringUsingRecursion {
	public static String reverseString(String str) {
		// If entered string is empty
		// Return the empty string
		if (str.isEmpty())
			return str;
		// If string consists of multiple character
		// Call the Function Recursively
		return reverseString(str.substring(1)) + str.charAt(0);
	}

	public static void main(String[] args) {
		String s="Arpan";
		System.out.println(reverseString(s));

	}

}
