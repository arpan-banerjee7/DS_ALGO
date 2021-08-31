package array.questions;

// Quick Select
// https://leetcode.com/problems/kth-largest-element-in-an-array/
// https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/

//TC--- Avg- o(n^2)
//      BC-  o(n)
public class FindKthLargestElement {
	private static int findPivot(int[] nums, int low, int high) {
		int i = low - 1;
		int pivot = nums[high];
		for (int j = low; j < high; j++) {
			if (nums[j] <= pivot) {
				i++;
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			}
		}

		int temp = nums[high];
		nums[high] = nums[i + 1];
		nums[i + 1] = temp;
		return i + 1;
	}

	private static int quickSelect(int[] nums, int low, int high, int k) {
		int pivot = findPivot(nums, low, high);
		if (pivot == k) {
			return nums[pivot];
		} else if (pivot < k) {
			return quickSelect(nums, pivot + 1, high, k);
		} else {
			return quickSelect(nums, low, pivot - 1, k);
		}

	}

	public static void main(String[] args) {
		int[] nums= {3,2,1,5,6,4};
		int k =2;;
		int n = nums.length;
        System.out.println(quickSelect(nums,0,n-1,n-k));

	}

}
