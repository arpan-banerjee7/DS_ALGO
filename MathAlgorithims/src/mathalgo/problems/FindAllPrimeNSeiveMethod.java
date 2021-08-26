package mathalgo.problems;

// TC--- o(n log(log n))
public class FindAllPrimeNSeiveMethod {

	public static int countPrimesSieveOfEratosthenes(int n) {
		// make an array upto n-1 and set it to false
		boolean[] notPrimes = new boolean[n];
		int count = 0;

		for (int i = 2; i <= (int) Math.floor(Math.sqrt(n)); i++) {
			if (!notPrimes[i]) {
				for (int j = 2; i * j < n; j++) {
					notPrimes[i * j] = true;
				}
			}
		}

		for (int i = 2; i < n; i++) {
			if (!notPrimes[i]) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int n = 15;
		System.out.println(countPrimesSieveOfEratosthenes(n));

	}

}
