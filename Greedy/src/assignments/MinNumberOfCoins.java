package assignments;

import java.util.Vector;

// https://www.geeksforgeeks.org/greedy-algorithm-to-find-minimum-number-of-coins/
// Todo Dp version coin change problem

/*https://www.youtube.com/watch?v=mVg9CfJvayM&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=5
https://www.geeksforgeeks.org/greedy-algorithm-to-find-minimum-number-of-coins/
*/

public class MinNumberOfCoins {

	// Java program to find minimum
	// number of denominations

	// All denominations of Indian Currency
	static int deno[] = { 1, 2, 5, 10, 20, 50, 100, 500, 1000 };
	static int n = deno.length;

	static void findMin(int V) {
		// Initialize result
		Vector<Integer> ans = new Vector<>();

		// Traverse through all denomination
		for (int i = n - 1; i >= 0; i--) {
			// Find denominations
			while (V >= deno[i]) {
				V -= deno[i];
				ans.add(deno[i]);
			}
		}

		// Print result
		for (int i = 0; i < ans.size(); i++) {
			System.out.print(" " + ans.elementAt(i));
		}
	}

	// Driver code
	public static void main(String[] args) {
		int n = 93;
		System.out.print("Following is minimal number " + "of change for " + n + ": ");
		findMin(n);
	}
}
/*
if (V >= deno[i])
{
int number=V/deno[i];
V-=deno[i]*number;
for(int j=0;j<number;j++){
ans.add(deno[i]);
}
*/