package array.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.codingninjas.com/codestudio/problems/merge-k-sorted-arrays_975379?leftPanelTab=2
// https://www.geeksforgeeks.org/merge-3-sorted-arrays/

public class MergeKSortedArrays {

	private static List<Integer> merge(List<Integer> a, List<Integer> b) {
		List<Integer> ans = new ArrayList<Integer>();

		int n = a.size(), m = b.size();
		int i = 0, j = 0;

		// Traverse the both arrays.
		while (i < n && j < m) {
			if (a.get(i) < b.get(j)) {
				ans.add(a.get(i));
				i++;
			} else {
				ans.add(b.get(j));
				j++;
			}
		}

		// Add remaining elements of the first array.
		while (i < n) {
			ans.add(a.get(i));
			i++;
		}

		// Add remaining elements of the second array.
		while (j < m) {
			ans.add(b.get(j));
			j++;
		}

		return ans;
	}

	private static List<Integer> mergeKSortedArraysHelper(List<List<Integer>> kArrays, int start, int end) {
		// If there is only one array.
		if (start == end) {
			return kArrays.get(start);
		}

		// If there are only two arrays, merge them.
		if (start + 1 == end) {
			return merge(kArrays.get(start), kArrays.get(end));
		}

		int mid = start + (end - start) / 2;

		// Divide the arrays into two halves.
		List<Integer> first = mergeKSortedArraysHelper(kArrays, start, mid);
		List<Integer> second = mergeKSortedArraysHelper(kArrays, mid + 1, end);

		// Return the final merged array.
		return merge(first, second);

	}

	public static void main(String[] args) {
		Integer[] a = { 1, 2, 3, 5 };
		Integer[] b = { 6, 7, 8, 9 };
		Integer[] c = { 10, 11, 12 };
		Integer[] d = { 55, 58, 89, 112 };
		List<Integer> A = Arrays.asList(a);
		List<Integer> B = Arrays.asList(b);
		List<Integer> C = Arrays.asList(c);
		List<Integer> D = Arrays.asList(d);
		List<List<Integer>> lis = new ArrayList<>();
		lis.add(A);
		lis.add(B);
		lis.add(C);
		lis.add(D);
		int k = lis.size();
		List<Integer> res = mergeKSortedArraysHelper(lis, 0, k - 1);
		res.forEach(e -> System.out.print(e + " "));

	}

}
