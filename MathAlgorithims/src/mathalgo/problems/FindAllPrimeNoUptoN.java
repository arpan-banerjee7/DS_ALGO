package mathalgo.problems;

import java.util.ArrayList;
import java.util.List;

// TC-- o(n sqrt(n))
// https://leetcode.com/problems/count-primes/
public class FindAllPrimeNoUptoN {

	private static boolean isPrime(int n) {
		// loop till root n, factors occur in pairs
		for (int i = 2; (i * i) <= n; i++)
			if (n % i == 0) {
				return false;
			}
		return true;
	}

	private static List<Integer> finAllPrimeTilLN(int n) {
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i * i <= n; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

	public static void main(String[] args) {
		int n = 15;
		List<Integer> primes = finAllPrimeTilLN(n);
		primes.forEach(e -> System.out.print(e + " "));

	}

}
