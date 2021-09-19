package problems;

// https://leetcode.com/problems/first-unique-character-in-a-string/

public class FirstUniqueCharInString {
	public static int firstUniqChar(String s) {
		int[] freq = new int[26];
		for (int i = 0; i < s.length(); i++) {
			freq[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < s.length(); i++) {
			if (freq[s.charAt(i) - 'a'] == 1) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String s = "leetcode";
		System.out.println(firstUniqChar(s)); // output 0
	}

}
