package bit.masking.problems;

public class CaseConversion {

	public static void main(String[] args) {
		char upper = 'A';
		char lower = 'b';

		System.out.println((char) (upper ^ (1 << 5)));
		System.out.println((char) (lower ^ (1 << 5)));
	}
}
