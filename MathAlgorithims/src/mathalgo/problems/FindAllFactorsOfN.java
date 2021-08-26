package mathalgo.problems;

// TC--- o(sqrt(n))
public class FindAllFactorsOfN {

	private static void factors(int n) {
		for (int i = 1; (i * i) <= n; i++)
			if (n % i == 0) {
				System.out.print(i + " ");
				if (i != (n / i)) {
					System.out.print((n / i) + " ");
				}
			}
	}

	public static void main(String[] args) {
		int n = 100;
		System.out.println("The factors of the given input are");
		factors(n);

	}

}
