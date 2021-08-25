package bit.masking.problems;

public class ComputeXORfrom1toN {

	// Method to calculate xor
	static int computeXOR(int n) {
		// If n is a multiple of 4
		if (n % 4 == 0)
			return n;

		// If n%4 gives remainder 1
		if (n % 4 == 1)
			return 1;

		// If n%4 gives remainder 2
		if (n % 4 == 2)
			return n + 1;

		// If n%4 gives remainder 3
		return 0;
	}

	// Driver method
	public static void main(String[] args) {
		int n = 5;
		System.out.println(computeXOR(n));
	}

}
