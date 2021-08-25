package bit.masking.problems;

public class Swap2No {
	public static void main(String a[]) {
		int x = 10;
		int y = 5;

		// Code to swap 'x' (1010) and 'y' (0101)
		x = x ^ y; // x now becomes 15 (1111)
		y = x ^ y; // y becomes 10 (1010)
		x = x ^ y; // x becomes 5 (0101)

		System.out.println("After swap: x = " + x + ", y = " + y);
	}
}
