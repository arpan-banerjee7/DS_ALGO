package dp.digitdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfDigitsOfARangeParentProb {
	static int digdp[][]; // pos...sum

	private static int sumOfDigits(List<Integer> digits, int pos, int sum, boolean restricted) {
		if (pos == -1)
			return sum;// all digits are processed :)
		if (digdp[pos][sum] != -1 && !restricted)
			return digdp[pos][sum];

		int ans = 0;
		int limit = restricted ? digits.get(pos) : 9;
		for (int i = 0; i <= limit; ++i) {
			boolean currRestricted = digits.get(pos) == i ? restricted : false;
			ans += sumOfDigits(digits, pos - 1, sum + i, currRestricted);
		}
		if (!restricted)
			digdp[pos][sum] = ans;
		return ans;
	}

	private static List<Integer> getDigits(int n) {
		List<Integer> digits = new ArrayList<>();
		while (n > 0) {
			digits.add(n % 10);
			n /= 10;
		}
		return digits;
	}

	public static void main(String[] args) {
		int a = 254;
		int b = 7658;

		// set all unknown values to -1
		digdp = new int[10][100]; // 10 digits...9*10=90
		for (int[] r : digdp)
			Arrays.fill(r, -1);

		List<Integer> digitsA = getDigits(a - 1); // a-1 = 253
		List<Integer> digitsB = getDigits(b);

		System.out.println("DigA: ");
		for (int ele : digitsA)
			System.out.print(ele + " ");
		System.out.println();
		System.out.println("DigB: ");
		for (int ele : digitsB)
			System.out.print(ele + " ");
		System.out.println();

		int sum_0toA = sumOfDigits(digitsA, digitsA.size() - 1, 0, true);
		System.out.println("sum_0toA-> " + sum_0toA);
		int sum_0toB = sumOfDigits(digitsB, digitsB.size() - 1, 0, true);
		System.out.println("sum_0toB-> " + sum_0toB);

		System.out.println("Answer = " + (sum_0toB - sum_0toA));// Answer = 125414
	}

}
