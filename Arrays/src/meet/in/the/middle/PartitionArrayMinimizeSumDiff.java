package meet.in.the.middle;

import java.util.TreeSet;
// 2035. Partition Array Into Two Arrays to Minimize Sum Difference

/*Similar kind of bt a lot easier version-https://www.geeksforgeeks.org/minimum-sum-two-numbers-formed-digits-array/
https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/discuss/1622681/Java-or-Detailed-Explanation-with-Intuition-or-Meet-in-the-Middle-or-Similar-Questions
https://www.youtube.com/watch?v=UoKGRTIZvM0&t=783s
*/

/*Read the basics of meet in the middle algo and why we use it before
attempting this question. And I would recommend to solve Leetcode 1755, which
is an easier version of this problem. Meet in the middle algo.
This is similar to 1755. Closest Subsequence Sum, here we have an extra
parameter which accounts for the length of the subsets While partitioning and
finding out the optimal answer we need to take care of the fact that the both
the parts have length n/2 and n/2. if n is the total length of the array.

Steps- 
1.  Partition the array int two halves- 0 to n/2(excluded) and n/2 to
n(excluded). 

2. Enumerate all the subsets for the first half, and store the
sums of all subsets along with the number of elements. For eg- take the
array- [2,-1,0,4,-2,-9] First half- [2,-1,0]- It will be stored in an array
of TreeSet<Integer> as follows- Index 0 will have [0] 1->[-1,0,2] 2->[-1,1,2]
3->[1] We are taking an array of TreeSet not just an array of array because
we can efficiently find out the lower bound and upper bounds of the target. I
will explain in a while, what is target and what is the intuition behind this
algorithm.

3.  *Intuition* sum=total sum of all elements in the given array n=total
number of elements in the given array S1- sum of a subset with n/2 elements.
S2- sum of another subset with n/2 elements.

Our goal is to divide the array into two equal parts such that the diff of
their sum is minimum. Now, when can the diff of their sum be min? When the
sum of the two subsets are exactly sum/2. We will try to find a sum as close
to sum/2 as possible from a subset formed from both the halves or from either
of the halves, such that the total number of element is n/2. We are doing
this because at last we want to minimize S2-S1. We have S1= sum/2(or a sum
very close to sum/2, formed using n/2 number of elements). We can simply
minimize sum-2*(S1). Hope the idea is clear.Now, in the below steps I will
explain how will you find S1.

4. We will make use of this concept. We have all sums mapped to the number of
elements from the first half, we will find the sum of subsets from the next
half one by one along with the number of elements forming that sum. Why are
we always mapping the subset sum to the number of elements forming the sum?
We are doing so to enable us to divide the array into two equal halves. So,
lets say sum of a subset formed from `m` elements from the second half is
`X`. Our goal is to find a sum which is as close to sum/2 using half the
total number of elemnts, i.e n/2. So, we have to find a sum of subset from
the first part(which we have already prepared earlier) such that
abs(sum/2-X)[lets say this target] is as small as possible, keeping in mind
the total number of elements from both the halves adds upto n/2, so that in
the next step we can do this- sum-2*(sum from second half(X)+sum from first
half(target(or sum as close to abs(sum/2-x)))

5. Now how are wer finding target. Lets take an example- total sum of all
elements=10 total elements 6.

From second half lets say we have a sum as 4, using 2 elements. From the
first half we need to find a subset formed from(6/2-2)=1 element having sum
as close to 10/2-4 as possible.such that toal sum of these two subsets is as
close to 10/2 as possible.

We already have mapped the number of elements to thier sum in the first half,
and from second half we got required number of elements(6/2-2)=1, and target
sum=10/2-4=1, so we will go to the first index of the array of part1 and from
its TreeSet we will find the floor and ceiling of the given target, and if we
find the exact target then our ans woud be 0, means we got the exact sum/2.

6. Find min of all sum-2*(X+target);*/

public class PartitionArrayMinimizeSumDiff {
	public int minimumDifference(int[] nums) {
		int n = nums.length;
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}

		// TC- o(2^n/2 * n/2 * log(2^n/2))= O(2^n/2 * n/2 * n/2)
		// will contain the subset sums along with the number of elements forming the
		// sum
		TreeSet<Integer>[] sets = new TreeSet[n / 2 + 1];
		for (int i = 0; i < (1 << (n / 2)); ++i) {
			int curSum = 0;
			int m = 0;
			for (int j = 0; j < n / 2; ++j) {
				if ((i & (1 << j)) > 0) {
					curSum += nums[j];
					m++;
				}
			}
			if (sets[m] == null)
				sets[m] = new TreeSet<Integer>();
			sets[m].add(curSum);
		}

		// TC- o(2^n/2 * n/2 * 2log(k))= O(2^n/2 * n/2 )
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << (n / 2)); ++i) {
			int curSum = 0;
			int m = 0;
			for (int j = 0; j < n / 2; ++j) {
				if ((i & (1 << j)) > 0) {
					curSum += nums[n / 2 + j];
					m++;
				}
			}
			int target = (sum / 2 - curSum);

			Integer left = sets[n / 2 - m].floor(target);
			Integer right = sets[n / 2 - m].ceiling(target);
			if (left != null) {
				res = Math.min(res, Math.abs(sum - 2 * (curSum + left.intValue())));
			}

			if (right != null) {
				res = Math.min(res, Math.abs(sum - 2 * (curSum + right.intValue())));
			}

			if (res == 0)
				return 0;

		}

		return res;
	}

	// Total TC- O(2^(n/2) * n/2 * n/2)s
	public static void main(String[] args) {
		/*
		 * Input: nums = [2,-1,0,4,-2,-9] Output: 0 Explanation: One optimal partition
		 * is: [2,4,-9] and [-1,0,-2]. The absolute difference between the sums of the
		 * arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
		 */
	}

}
