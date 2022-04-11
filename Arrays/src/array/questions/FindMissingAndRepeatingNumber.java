package array.questions;

// Find the repeating and the missing | Added 3 new methods
// https://practice.geeksforgeeks.org/problems/find-missing-and-repeating2512/1/#
// https://www.youtube.com/watch?v=Ztt-VtNyqL8
// https://www.youtube.com/watch?v=5nMGY4VUoRY&t=811s

public class FindMissingAndRepeatingNumber {
	int[] findTwoElement(int arr[], int n) {
		// code here
		int[] res = new int[2];
		// [1,3,3,4]
		// xor of all elements of the array
		// 1^3^3^4
		int xor = arr[0];
		for (int i = 1; i < n; i++) {
			xor = xor ^ arr[i];
		}
		// xor of all elements from 1 to n
		// 1^2^3^4
		for (int i = 1; i <= n; i++) {
			xor = xor ^ i;
		}

		// we get xor=repeating num^missing num

		// set the LSS
		// take a mask and right shift xor by 1 each time to find out the
		// rightmost set bit and keep it set
		int count = 0;
		int mask = 1;
		while (xor != 0) {
			if ((xor & mask) != 0) {
				break;
			}
			xor >>= 1;
			count++;
		}
		int setBit = mask <<= count;

		// seggregate the arr elements into two groups having 1 at that bit and 0 at
		// that bit
		int x = 0;
		int y = 0;
		// in x-> 0^1^3^3
		// in y->0^4
		for (int i = 0; i < n; i++) {
			if ((arr[i] & setBit) != 0) {
				x = x ^ arr[i];
			} else {
				y = y ^ arr[i];
			}
		}

		// do a similar op again now with all the numbers
		// 0^1^3^3^1^3= 3
		// 0^4^2^4= 2
		for (int i = 1; i <= n; i++) {
			if ((i & setBit) != 0) {
				x = x ^ i;
			} else {
				y = y ^ i;
			}
		}

		for (int i = 0; i < n; i++) {
			if (arr[i] == x) {
				res[0] = x;
				res[1] = y;
				return res;
			}
		}
		res[0] = y;
		res[1] = x;
		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: N = 2 Arr[] = {2, 2} Output: 2 1 Explanation: Repeating number is 2
		 * and smallest positive missing number is 1.
		 */
	}

}
