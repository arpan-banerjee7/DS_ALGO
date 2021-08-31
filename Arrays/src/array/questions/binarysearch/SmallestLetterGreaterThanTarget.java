package array.questions.binarysearch;

public class SmallestLetterGreaterThanTarget {

	// https://leetcode.com/problems/find-smallest-letter-greater-than-target/
	// TC--o(logn)
	public static char nextGreatestLetter(char[] letters, char target) {

		int start = 0;
		int end = letters.length - 1;

		while (start <= end) {
			// find the middle element
			// int mid = (start + end) / 2; // might be possible that (start + end) exceeds
			// the range of int in java
			int mid = start + (end - start) / 2;

			if (target < letters[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return letters[start % letters.length];
	}

	public static void main(String[] args) {
		char[] letters = { 'c', 'f', 'j' };
		char target = 'a';
		System.out.println(nextGreatestLetter(letters, target));

	}

}
