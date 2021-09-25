package problems;

// https://www.youtube.com/watch?v=tFdBRcHLSGQ --best
// https://www.youtube.com/watch?v=63fPPOdIr2c striver
// https://www.geeksforgeeks.org/find-median-row-wise-sorted-matrix/

public class MedianRowWiseSortedMatrix {

	private static int binarySearch(int[] arr, int low, int high, int target) {
		if (low > high) {
			return low;
		}
		int mid = low + (high - low) / 2;
		if (arr[mid] <= target) {
			return binarySearch(arr, mid + 1, high, target);
		} else {
			return binarySearch(arr, low, mid - 1, target);
		}

	}

	static int median(int matrix[][], int r, int c) {
		// code here
		int low = 0;
		int high = 2000;
		int count;
		int half = (r * c) / 2;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			count = 0;
			for (int i = 0; i < r; i++) {
				count += binarySearch(matrix[i], 0, c - 1, mid);
			}
			if (count <= half) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	public static void main(String[] args) {
		int r = 3, c = 3;
		int m[][] = { { 1, 3, 5 }, { 2, 6, 9 }, { 3, 6, 9 } };

//		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
//		for (int i = 0; i < r; i++) {
//			ArrayList<Integer> rowList = new ArrayList<Integer>();
//			for (int j = 0; j < c; j++) {
//				rowList.add(m[i][j]);
//			}
//			list.add(rowList);
//		}
		int ans = median(m, r, c);
		System.out.println("Median is " + ans);

	}

}
