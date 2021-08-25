package sorting.alogrithims;

//https://www.geeksforgeeks.org/cycle-sort/
//https://www.youtube.com/watch?v=gZNOM_yMdSQ
public class CycleSort {

	static int writes;

	public static void main(String[] args) {

		CycleSort obj = new CycleSort();
		// int arr[] = { 2, 4, 5, 1, 3 };
		int arr[] = { 1, 8, 3, 9, 9, 10, 2, 4 };
		obj.sort(arr);
		System.out.println("Sorted array using cycle sort: ");
		obj.printArray(arr);
	}

	void sort(int[] arr) {
		int n = arr.length;

		for (int cycle_start = 0; cycle_start <= (n - 2); cycle_start++) {
			int item = arr[cycle_start];

			int pos = cycle_start;
			// Find position where we put the item. We basically
			// count all smaller elements on right side of item.
			for (int i = cycle_start + 1; i < n; i++) {
				if (arr[i] < item) {
					pos++;
				}
			}
			// If item is already in correct position
			if (pos == cycle_start)
				continue;

			// ignore all duplicate elements
			while (item == arr[pos]) {
				pos = pos + 1;
			}

			// put the item to it's right position
			if (pos != cycle_start) {
				int temp = item;
				item = arr[pos];
				arr[pos] = temp;
				writes++;
			}

			// Rotate rest of the cycle
			while (pos != cycle_start) {
				pos = cycle_start;

				// Find position where we put the element
				for (int i = cycle_start + 1; i < n; i++)
					if (arr[i] < item)
						pos += 1;

				// ignore all duplicate elements
				while (item == arr[pos])
					pos += 1;

				// put the item to it's right position
				if (item != arr[pos]) {
					int temp = item;
					item = arr[pos];
					arr[pos] = temp;
					writes++;
				}
			}
		}

	}

	// Prints the array
	void printArray(int[] arr) {
		for (int i : arr)
			System.out.print(i + " ");

		System.out.println();
		System.out.println("Number of writes = " + writes);
	}

}