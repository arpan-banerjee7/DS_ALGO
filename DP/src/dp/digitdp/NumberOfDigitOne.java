package dp.digitdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// https://leetcode.com/problems/number-of-digit-one/

public class NumberOfDigitOne {
	int[][] digdp;

	private int noOfOnes(List<Integer> digits, int pos, int sum, boolean restricted) {
		if (pos == -1)
			return sum;// all digits are processed :)
		if (digdp[pos][sum] != -1 && !restricted)
			return digdp[pos][sum];

		int ans = 0;
		int limit = restricted ? digits.get(pos) : 9;
		for (int i = 0; i <= limit; ++i) {
			boolean currRestricted = digits.get(pos) == i ? restricted : false;
			int is1 = (i == 1) ? 1 : 0;
			ans += noOfOnes(digits, pos - 1, sum + is1, currRestricted);
		}
		if (!restricted)
			digdp[pos][sum] = ans;
		return ans;
	}

	public int countDigitOne(int n) {
		digdp = new int[10][10];
		for (int i = 0; i < 10; i++) {
			Arrays.fill(digdp[i], -1);
		}
		List<Integer> digits = new ArrayList<>();
		while (n > 0) {
			digits.add(n % 10);
			n = n / 10;
		}
		return noOfOnes(digits, digits.size() - 1, 0, true);
	}

	public static void main(String[] args) {
		int n = 13;
		NumberOfDigitOne obj = new NumberOfDigitOne();
		System.out.println(obj.countDigitOne(n)); // Output- 6
	}

}
