package problems;

/*
43. Multiply Strings
All solutions- sum, diff , multi- https://leetcode.com/problems/multiply-strings/discuss/1683513/java-1ms-faster-than-100-similar-questions-commented
https://leetcode.com/problems/multiply-strings/
https://www.youtube.com/watch?v=1vZswirL8Y8
*/

public class AddStrings {
	public String addStrings(String num1, String num2) {
		int n1 = num1.length();
		int n2 = num2.length();

		// we will first iterate through the small string
		if (n1 < n2) {
			return addStrings(num2, num1);
		}

		// fill the char arrays in reverse order
		char[] char1 = new char[n1];
		char[] char2 = new char[n2];
		for (int i = n1 - 1; i >= 0; i--) {
			char1[n1 - 1 - i] = num1.charAt(i);
		}
		for (int i = n2 - 1; i >= 0; i--) {
			char2[n2 - 1 - i] = num2.charAt(i);
		}

		int[] res = new int[Math.max(n1, n2) + 1];
		int i;
		for (i = 0; i < n2; i++) {
			int sum = char1[i] - '0' + char2[i] - '0';
			res[i] += sum;
			res[i + 1] = (res[i] / 10);
			res[i] = res[i] % 10;
		}

		// when the strings are not of the same length
		// "892"+"7"
		// follow the same logic above, here just add the char from string1
		while (i < n1) {
			res[i] += char1[i] - '0';
			res[i + 1] = res[i] / 10;
			res[i] = res[i] % 10;
			i++;
		}

		// we had assummed the max possible space, so it might not have been filled up
		// remove extra 0's
		int j;
		for (j = res.length - 1; j >= 0; j--) {
			if (res[j] != 0) {
				break;
			}
		}

		// edge case --> "0" + "0" = "0"
		if (j < 0)
			return "0";

		// build ans in reverse form
		StringBuilder ans = new StringBuilder();
		for (int k = j; k >= 0; k--) {
			ans.append(res[k]);
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		/*
		 * Input: num1 = "456", num2 = "77" Output: "533"
		 */
	}

}
