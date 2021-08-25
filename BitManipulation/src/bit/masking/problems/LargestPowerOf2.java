package bit.masking.problems;

//Find largest power of two which is less than or equal to n
public class LargestPowerOf2 {

	// & with 1 and right shift number till its 0. Ans will be 1 left shifted by
	// number of shifts
	private static int findLargestPowerOf2(int n) {
		int shift = 0;
		while (n > 0) {
			n >>= 1;
			shift++;
		}
		return 1 << (shift - 1);
	}

	private static int findLargestPowerOf2_1(int n) {
		while ((n & n - 1) != 0) {
			n = n & n - 1;
		}
		return n;
	}

	private static int findLargestPowerOf2_2(int n) {
		int digits = (int) (Math.log(n) / Math.log(2));
		return 1 << digits;
	}

	public static void main(String[] args) {
		int n = 18;
		System.out.println(findLargestPowerOf2(n));
		System.out.println(findLargestPowerOf2_1(n));
		System.out.println(findLargestPowerOf2_2(n));
	}

}
