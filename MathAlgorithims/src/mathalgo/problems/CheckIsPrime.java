package mathalgo.problems;

// TC--- o(Sqrt n)
public class CheckIsPrime {
	public static void main(String[] args) {
		int n = 11;
		String isPrime = isPrime(n) ? "Prime" : "Not Prime";
		System.out.println("The given input is " + isPrime);
	}

	private static boolean isPrime(int n) {
		// loop till root n, factors occur in pairs
		for (int i = 2; (i * i) <= n; i++) 
			if (n % i == 0) {
				return false;
			}
		return true;
	}

}