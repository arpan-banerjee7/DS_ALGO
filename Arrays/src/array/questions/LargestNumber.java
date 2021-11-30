package array.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 179. Largest Number
// https://leetcode.com/problems/largest-number/
// https://www.youtube.com/watch?v=qEIGhVtZ-sg&t=1s  Techdose

public class LargestNumber {

	// using selection sort - o(n2)
	// selects and places the smallest/largest element based on sorting criteria
	// at the begining
	public static String largestNumber(int[] nums) {

		int n = nums.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				String AB = String.valueOf(nums[i]) + String.valueOf(nums[j]);
				String BA = String.valueOf(nums[j]) + String.valueOf(nums[i]);
				if (BA.compareTo(AB) > 0) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < n; i++) {
			res.append(String.valueOf(nums[i]));
		}

		if (res.charAt(0) == '0')
			return "0";
		return res.toString();
	}

	// TC-o(nlogn)
	public String largestNumber1(int[] nums) {

		List<String> inputList = new ArrayList<String>();
		for (int i = 0; i < nums.length; i++) {
			inputList.add(String.valueOf(nums[i]));
		}

		StringBuilder res = new StringBuilder();
		Collections.sort(inputList, (a, b) -> {
			// -1 means a should come first after swap
			String AB = a + b;
			String BA = b + a;
			return AB.compareTo(BA) > 0 ? -1 : 1;
		});

		for (String s : inputList) {
			res.append(s);
		}

		if (res.charAt(0) == '0')
			return "0";

		return res.toString();
	}

	public static void main(String[] args) {
		int[] nums = { 3, 30, 34, 5, 9 };
		System.out.println(largestNumber(nums));

		// Output: "9534330"
	}

}
