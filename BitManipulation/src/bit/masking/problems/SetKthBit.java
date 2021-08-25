package bit.masking.problems;

public class SetKthBit {
	private static int setKthBit(int n, int k) {
		// kth bit of n is being set by this operation
	    return ((1 << k) | n);
	}
	public static void main(String[] args) {
		int n=10;
		int k =2;
		System.out.println(setKthBit(n, k));

	}

}
