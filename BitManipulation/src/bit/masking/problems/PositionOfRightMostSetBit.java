package bit.masking.problems;

// https://www.geeksforgeeks.org/position-of-rightmost-set-bit/
// Find position of rightmost set bit
public class PositionOfRightMostSetBit {

	// trivial approach
	private static int findPosRightMostSetBit(int n) {
		int count = 0;
		while (n > 0) {
			if ((n & 1) != 0) {
				count++;
				break;
			}
			count++;
			n >>= 1;
		}
		return count;

	}

	// XOR trick
	private static int findPosRightMostSetBit_1(int n) {
		int temp = (n & (n - 1));
		return n ^ temp;

	}

	public static void main(String[] args) {
		int n = 18;
		System.out.println(findPosRightMostSetBit(n));
		System.out.println(findPosRightMostSetBit_1(n));
	}

}
