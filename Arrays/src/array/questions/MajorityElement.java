package array.questions;

/*169. Majority Element
https://leetcode.com/problems/majority-element/
Bit magic - https://www.youtube.com/watch?v=0s3zqYaDInE&t=2s
Check Strivers intuition for Moore's Voting algo-
All 3 solutions-
https://leetcode.com/problems/majority-element-ii/discuss/1614484/Java-or-o(n2)-Brute-Force-or-o(n)-single-pass-HashMap-or-Boyer-Moore-Algo
*/

public class MajorityElement {
	// using bit masking, since it i integer for 32 bits check for all the numbers
	// which bits are set, if there is a majority element, then for it these bits
	// will also be
	// set
	public int majorityElement(int[] nums) {
		int majority = 0;

		for (int i = 0; i < 32; i++) {
			int count = 0;
			// check the count i th set bit for all the numbers
			for (int n : nums) {
				if ((n & (1 << i)) != 0) {
					count++;
				}
			}
			// if ith bit is set for more than n/2 times that it will also be set
			// in the majority element, so setting it in the majority element
			if (count > nums.length / 2) {
				majority += (1 << i);
			}
		}
		return majority;
	}

	// Moore's voting algorithm
	public int majorityElement1(int[] nums) {
		int majority = nums[0];
		int count = 0;

		for (int i : nums) {
			if (i == majority) {
				count++;
			} else {
				count--;
				if (count == 0) {
					majority = i;
					count = 1;
				}
			}
		}
		return majority;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [3,2,3] Output: 3
		 */
	}

}
