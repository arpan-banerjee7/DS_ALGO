package problems;

// 2170. Minimum Operations to Make the Array Alternating
// https://leetcode.com/problems/minimum-operations-to-make-the-array-alternating/
// https://www.youtube.com/watch?v=k8I7F8ImzcI&t=840s striver weekly contest live

// here we are trying to replace as less number of digits as possible hence we
// are calculating these max
// idea is to find the firstMax,second max freq of all the elements at even and
// odd positions, and then aplly the formula- total
// no-firstMaxEven-firstMAxOdd(if firstMaxEven!=firstMaxOdd)
// else Math.min(total no-firstMaxEven-secondMaxOdd,total
// no-secondMaxEven-firstMaxOdd)

// eg- [1,1,1,1,1,3,2,1,2,1],
// even- firstMax=1, freq=3, secondMax=2,freq=2
// odd- firstMax=1, freq=4, secondMax=3, freq=1.
// So, Math.min((10-3-1),(10-2-4))=4

public class MinimumOperationsToMakeArrayAlternating {

	public int minimumOperations(int[] nums) {
		int[] freqEven = new int[100001];
		int[] freqOdd = new int[100001];
		int max = 0;
		// calculate the freq in even and odd positions
		for (int i = 0; i < nums.length; i++) {
			if (i % 2 == 0) {
				freqEven[nums[i]]++;
			} else {
				freqOdd[nums[i]]++;
			}
			max = Math.max(max, nums[i]);
		}

		// find firstMaxEven, freqFirstMaxEven, secondMaxEven, freqSecondMaxEven and
		// same for odd
		int firstMaxEven = 0;
		int freqFirstMaxEven = 0;
		int secondMaxEven = 0;
		int freqSecondMaxEven = 0;

		int firstMaxOdd = 0;
		int freqFirstMaxOdd = 0;
		int secondMaxOdd = 0;
		int freqSecondMaxOdd = 0;

		for (int i = 1; i <= max; i++) {
			if (freqEven[i] > freqFirstMaxEven) {
				freqSecondMaxEven = freqFirstMaxEven;
				secondMaxEven = firstMaxEven;

				freqFirstMaxEven = freqEven[i];
				firstMaxEven = i;
			} else if (freqEven[i] > freqSecondMaxEven) {
				freqSecondMaxEven = freqEven[i];
				secondMaxEven = i;
			}

			if (freqOdd[i] > freqFirstMaxOdd) {
				freqSecondMaxOdd = freqFirstMaxOdd;
				secondMaxOdd = firstMaxOdd;

				freqFirstMaxOdd = freqOdd[i];
				firstMaxOdd = i;
			} else if (freqOdd[i] > freqSecondMaxOdd) {
				freqSecondMaxOdd = freqOdd[i];
				secondMaxOdd = i;
			}
		}
		if (firstMaxEven != firstMaxOdd) {
			return nums.length - freqFirstMaxEven - freqFirstMaxOdd;
		}
		return Math.min((nums.length - freqFirstMaxEven - freqSecondMaxOdd),
				(nums.length - freqSecondMaxEven - freqFirstMaxOdd));
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [3,1,3,2,4,3] Output: 3 Explanation: One way to make the array
		 * alternating is by converting it to [3,1,3,1,3,1]. The number of operations
		 * required in this case is 3. It can be proven that it is not possible to make
		 * the array alternating in less than 3 operations.
		 */ 
	}

}
