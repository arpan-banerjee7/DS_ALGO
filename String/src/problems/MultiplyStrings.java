package problems;

/*
 43. Multiply Strings
All solutions- sum, diff , multi- https://leetcode.com/problems/multiply-strings/discuss/1683513/java-1ms-faster-than-100-similar-questions-commented
https://leetcode.com/problems/multiply-strings/
https://www.youtube.com/watch?v=1vZswirL8Y8
 */

public class MultiplyStrings {
	public static String multiply(String num1, String num2) {

		int n1 = num1.length();
		int n2 = num2.length();
		int[] res = new int[n1 + n2];

		// fill the char arrays in reverse order
		char[] char1 = new char[n1];
		char[] char2 = new char[n2];
		for (int i = n1 - 1; i >= 0; i--) {
			char1[n1 - 1 - i] = num1.charAt(i);
		}
		for (int i = n2 - 1; i >= 0; i--) {
			char2[n2 - 1 - i] = num2.charAt(i);
		}

		// fill res from left to right
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < n1; j++) {
				int digit = (char1[j] - '0') * (char2[i] - '0');
				res[i + j] += digit;
				res[i + j + 1] += res[i + j] / 10;
				res[i + j] = res[i + j] % 10;
			}
		}

		// remove extra 0's
		int i;
		for (i = res.length - 1; i >= 0; i--) {
			if (res[i] != 0) {
				break;
			}
		}

		// edge case --> "0" * "0" = "0"
		if (i < 0)
			return "0";

		// build ans in reverse form
		StringBuilder ans = new StringBuilder();
		for (int k = i; k >= 0; k--) {
			ans.append(res[k]);
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		String num1 = "123", num2 = "456";
		System.out.println(multiply(num1, num2));

	}

}
