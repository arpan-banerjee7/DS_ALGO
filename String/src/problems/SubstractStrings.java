package problems;

/*
 * 
// https://www.geeksforgeeks.org/difference-of-two-large-numbers/

1. Reverse both strings.
2. Keep subtracting digits one by one from 0’th index (in reversed strings) to the end of a smaller string, append the diff if it’s positive to end of the result. If difference(diff) is negative then add 10 and keep track of carry as 1 if it’s positive then carry is 0.
3. Finally, reverse the result.

 */
public class SubstractStrings {
	private static String substractStrings(String num1, String num2) {
		int n1 = num1.length();
		int n2 = num2.length();
		if (n1 < n2) {
			return substractStrings(num2, num1);
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
		int[] res = new int[n1];
		int i;
		for (i = 0; i < n2; i++) {
			int diff = (char1[i] - '0') - (char2[i] - '0');
			if (diff < 0) {
				res[i] = (10 + diff) - res[i]; // res[i] is the carry generated in prev step
				res[i + 1] = 1;// carry is generated here
			} else {
				res[i] = diff;
			}

		}
		// substract the extra numbers of the bigger string
		while (i < n1) {
			res[i] = (char1[i] - '0') - res[i];
			i++;
		}

		// build ans in reverse form
		StringBuilder ans = new StringBuilder();
		for (int k = res.length - 1; k >= 0; k--) {
			ans.append(res[k]);
		}
		return ans.toString();

	}

	public static void main(String[] args) {
		String num1 = "0", num2 = "0";
		System.out.println(substractStrings(num1, num2));

	}

}
