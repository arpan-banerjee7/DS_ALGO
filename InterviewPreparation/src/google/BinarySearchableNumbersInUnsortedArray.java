package google;
// 1966. Binary Searchable Numbers in an Unsorted Array
// https://algo.monster/liteproblems/1966
/*
Problem Understanding
We are given:

An unsorted array nums of unique integers.
A function that searches for a target value within nums using a binary search-like algorithm with random pivot selection.
The function behaves as follows:

While the sequence is not empty:
Randomly choose a pivot from the sequence.
If the pivot equals the target, return true.
Else if pivot < target, remove the pivot and all elements to its left from the sequence.
Else if pivot > target, remove the pivot and all elements to its right from the sequence.
If the sequence becomes empty, return false.
Our task is:

For the given array nums, determine how many values are guaranteed to be found using this function, regardless of the pivot choices made during the search.
Key Observations
An element (target) is guaranteed to be found if:

There is no possible pivot choice that can eliminate the target before it is found.
In other words, the target cannot be removed during the process, no matter which pivots are chosen.
To ensure that the target is never removed:

From the Left Side:

There should be no element to the left of the target that is greater than the target.
If there is a larger element on the left, choosing it as a pivot when pivot > target would remove the target.
From the Right Side:

There should be no element to the right of the target that is less than the target.
If there is a smaller element on the right, choosing it as a pivot when pivot < target would remove the target.
*/

public class BinarySearchableNumbersInUnsortedArray {
    public static int binarySearchableNumbers(int[] nums) {
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] minRight = new int[n];

        // Compute maxLeft
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxLeft[i] = maxSoFar;
            if (nums[i] > maxSoFar) {
                maxSoFar = nums[i];
            }
        }

        // Compute minRight
        int minSoFar = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minRight[i] = minSoFar;
            if (nums[i] < minSoFar) {
                minSoFar = nums[i];
            }
        }

        // Count guaranteed elements
        int count = 0;
        for (int i = 0; i < n; i++) {
            boolean leftCondition = nums[i] >= maxLeft[i];
            boolean rightCondition = nums[i] <= minRight[i];
            if (leftCondition && rightCondition) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 5, 4, 6};
        int result = binarySearchableNumbers(arr);
        System.out.println("Always found elements: " + result);// output=2
    }
}
/*
Consider a function that implements an algorithm similar to Binary Search. The function has two input parameters: sequence is a sequence of integers, and target is an integer value. The purpose of the function is to find if the target exists in the sequence.

The pseudocode of the function is as follows:

func(sequence, target)
  while sequence is not empty
    randomly choose an element from sequence as the pivot
    if pivot = target, return true
    else if pivot < target, remove pivot and all elements to its left from the sequence
    else, remove pivot and all elements to its right from the sequence
  end while
  return false
When the sequence is sorted, the function works correctly for all values. When the sequence is not sorted, the function does not work for all values, but may still work for some values.

Given an integer array nums, representing the sequence, that contains unique numbers and may or may not be sorted, return the number of values that are guaranteed to be found using the function, for every possible pivot selection.



Example 1:

Input: nums = [7]
Output: 1
Explanation:
Searching for value 7 is guaranteed to be found.
Since the sequence has only one element, 7 will be chosen as the pivot. Because the pivot equals the target, the function will return true.
Example 2:

Input: nums = [-1,5,2]
Output: 1
Explanation:
Searching for value -1 is guaranteed to be found.
If -1 was chosen as the pivot, the function would return true.
If 5 was chosen as the pivot, 5 and 2 would be removed. In the next loop, the sequence would have only -1 and the function would return true.
If 2 was chosen as the pivot, 2 would be removed. In the next loop, the sequence would have -1 and 5. No matter which number was chosen as the next pivot, the function would find -1 and return true.

Searching for value 5 is NOT guaranteed to be found.
If 2 was chosen as the pivot, -1, 5 and 2 would be removed. The sequence would be empty and the function would return false.

Searching for value 2 is NOT guaranteed to be found.
If 5 was chosen as the pivot, 5 and 2 would be removed. In the next loop, the sequence would have only -1 and the function would return false.

Because only -1 is guaranteed to be found, you should return 1.


Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
All the values of nums are unique.


Follow-up: If nums has duplicates, would you modify your algorithm? If so, how?
 */