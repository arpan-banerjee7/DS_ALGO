package mathalgo.problems;

// https://www.geeksforgeeks.org/program-to-calculate-the-value-of-ncr-efficiently/
// https://www.geeksforgeeks.org/program-calculate-value-ncr/ -- naive approach

// TC-- rlog(n)-?? how
public class FindnCrEfficiently {
	// Function to find the nCr
	static void printNcR(int n, int r) {

		// p holds the value of n*(n-1)*(n-2)...,
		// k holds the value of r*(r-1)...
		long p = 1, k = 1;

		// C(n, r) == C(n, n-r),
		// choosing the smaller value
		if (n - r < r) {
			r = n - r;
		}

		if (r != 0) {
			while (r > 0) {
				p *= n;
				k *= r;

				// gcd of p, k
				long m = __gcd(p, k);

				// dividing by gcd, to simplify
				// product division by their gcd
				// saves from the overflow
				p /= m;
				k /= m;

				n--;
				r--;
			}

			// k should be simplified to 1
			// as C(n, r) is a natural number
			// (denominator should be 1 ) .
		} else {
			p = 1;
		}

		// if our approach is correct p = ans and k =1
		System.out.println(p);
	}

	static long __gcd(long n1, long n2) {
		long gcd = 1;

		for (int i = 1; i <= n1 && i <= n2; ++i) {
			// Checks if i is factor of both integers
			if (n1 % i == 0 && n2 % i == 0) {
				gcd = i;
			}
		}
		return gcd;
	}

	// Driver code
	public static void main(String[] args)
    {
        int n = 50, r = 25;
 
        printNcR(n, r);
    }
}