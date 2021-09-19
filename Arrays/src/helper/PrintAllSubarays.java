package helper;

// https://java2blog.com/print-subarrays-given-array/
// https://algorithms.tutorialhorizon.com/print-all-subarrays-using-recursion/

public class PrintAllSubarays {

	void printSubArray(int arr[]) {

		int n = arr.length;
		for (int i = 0; i < n; i++) // This loop will select start element
		{
			for (int j = i; j < n; j++) // This loop will select end element
			{
				for (int k = i; k <= j; k++) // This loop will print element from start to end

				{
					System.out.print(arr[k] + " ");
				}
				System.out.println();
			}
		}
	}

	// recursive solution
	public static void printSubArray(int[] input, int currIndex) {

		if (currIndex == input.length)
			return;

		// print all the subarray from currIndex to end
		String result = "";
		for (int i = currIndex; i < input.length; i++) {
			result += " " + input[i] + " ";
			System.out.print("[" + result + "] ");
		}
		printSubArray(input, currIndex + 1);
	}

	public static void main(String args[]) {
		PrintAllSubarays psm = new PrintAllSubarays();
		int arr[] = { 1, 2, 3, 4 };
		psm.printSubArray(arr);
	}
}