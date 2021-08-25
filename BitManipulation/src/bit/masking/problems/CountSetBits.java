package bit.masking.problems;

public class CountSetBits {

	private static int calSetBits(int n) {
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n >>= 1;
		}
		return count;
	}

	public static void main(String[] args) {
		int n = 9;
		System.out.println(calSetBits(n));

	}

}
