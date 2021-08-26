package mathalgo.problems;

// https://www.geeksforgeeks.org/modular-exponentiation-power-in-modular-arithmetic/
// TC--- o(log(exponent))
public class ModularExponentiation {

	/* Iterative Function to calculate (x^y) in O(log y) */
	static int power(int x, int y) {
		int res = 1; // Initialize result

		while (y > 0) {
			// If y is odd, multiply x with result
			if ((y & 1) != 0) {
				res = res * x;
			}
			// y must be even now
			y = y >> 1; // y = y/2
			x = x * x; // Change x to x^2
		}
		return res;
	}

	// How will we decide the value of p?
	// How does (a*b)%p= ((a%p)*(b%p))%p apply here?
	/* Iterative Function to calculate (x^y) in O(log y) */
	static int power_mod(int x, int y, int p) {
		int res = 1; // Initialize result

		x = x % p; // Update x if it is more than or
		// equal to p

		if (x == 0)
			return 0; // In case x is divisible by p;

		while (y > 0) {

			// If y is odd, multiply x with result
			if ((y & 1) != 0)
				res = (res * x) % p;

			// y must be even now
			y = y >> 1; // y = y/2
			x = (x * x) % p;
		}
		return res;
	}

	/* Iterative Function to calculate (x^y) in O(log y) */
	static int power_recursive(int x, int y, int res) {
		if (y == 0) {
			return res;
		}
		// If y is odd, multiply x with result
		if ((y & 1) != 0) {
			res = res * x;
		}
		// y must be even now
		y = y >> 1; // y = y/2
		x = x * x; // Change x to x^2
		return power_recursive(x, y, res);
	}

	public static void main(String[] args) {
		int x = 3;
		int y = 9;
		int p = 13;
		System.out.println(power(x, y));
		System.out.println(power_mod(x, y, p));
		System.out.println(power_recursive(x, y, 1));
	}

}
