package bit.masking.problems;

public class CheckPowerOf2 {

	// TC O(1) n&(n-1) concept
	// create a mask for the MSB, and check if it is 0 or 1.
	public static boolean isPowerOfTwo1(int n) {
		return n > 0 && ((n & (n - 1)) == 0);
	}

	// TC O(logn)
	public static boolean isPowerOfTwo2(int n) {
		while (n > 1) {
			int lastDigit = n % 2;
			if (lastDigit == 1) {
				return false;
			}
			n = n / 2;
		}
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isPowerOfTwo3(int n) {
		while (n > 1) {
			if((n & 1)==1) {
				return false;
			}
			n >>= 1;
		}
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}


	public static void main(String[] args) {
		int n = 6;
		System.out.println(isPowerOfTwo1(n));
		System.out.println(isPowerOfTwo2(n));
		System.out.println(isPowerOfTwo3(n));

	}

}
