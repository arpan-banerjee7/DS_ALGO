package problems;

/*1328. Break a Palindrome
https://leetcode.com/problems/break-a-palindrome/
https://www.geeksforgeeks.org/make-the-string-lexicographically-smallest-non-palindromic-by-replacing-exactly-one-character/
*/
public class BreakAPalindrome {

	public String breakPalindrome(String palindrome) {
		int n = palindrome.length();
		char[] ch = palindrome.toCharArray();
		// "aba"/ "acbca"-- we need to make the change only on the first half as a non
		// 'a' char can be in the first half or it can be the middle element. if the
		// middle element is not 'a', no matter we change it to 'a' it will still be a
		// plaindrome, so this check is very imp
		for (int i = 0; i < n / 2; i++) {
			if (ch[i] != 'a') {
				ch[i] = 'a';
				return String.valueOf(ch);
			}
		}
		ch[n - 1] = 'b';
		return n <= 1 ? "" : String.valueOf(ch);
	}

	public String breakPalindrome1(String palindrome) {
		int n = palindrome.length();
		if (n <= 1)
			return "";
		for (int i = 0; i < n / 2; i++) {
			if (palindrome.charAt(i) != 'a') {
				String s = palindrome.substring(0, i) + 'a' + palindrome.substring(i + 1);
				return s;
			}
		}
		String s = palindrome.substring(0, n - 1) + 'b';
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
