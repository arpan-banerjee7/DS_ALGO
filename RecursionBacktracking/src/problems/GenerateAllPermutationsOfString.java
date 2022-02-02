package problems;

// https://www.techiedelight.com/generate-permutations-string-java-recursive-iterative/

public class GenerateAllPermutationsOfString {

	// Utility function to swap two characters in a character array
	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	// Recursive function to generate all permutations of a string
	private static void permutations(char[] chars, int currentIndex) {
		if (currentIndex == chars.length - 1) {
			System.out.println(String.valueOf(chars));
		}

		for (int i = currentIndex; i < chars.length; i++) {
			swap(chars, currentIndex, i);
			permutations(chars, currentIndex + 1);
			swap(chars, currentIndex, i);
		}
	}

	// Recursive function to generate all permutations of a string
	private static void permutations(String candidate, String remaining) {
		// base case
		if (remaining == null) {
			return;
		}

		if (remaining.length() == 0) {
			System.out.println(candidate);
		}

		for (int i = 0; i < remaining.length(); i++) {
			String newCandidate = candidate + remaining.charAt(i);

			String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);

			permutations(newCandidate, newRemaining);
		}
	}

	// Find Permutations of a string in Java
	public static void main(String[] args) {
		String str = "ABC";
		permutations("", str);
	}
	/*
	 * output ABC ACB BAC BCA CAB CBA
	 */
}
