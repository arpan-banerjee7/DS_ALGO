package array.questions;

/*4. Median of Two Sorted Arrays
https://leetcode.com/problems/median-of-two-sorted-arrays/
https://www.youtube.com/watch?v=NTop3VTjmxk striver
https://www.youtube.com/watch?v=NTop3VTjmxk
extremely dfficult to write the code, can t write it even after seeing the sol
lot of edge cases concept is simple*/

public class MedianOfTwoSortedArrays {

	// TLE
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int n1 = nums1.length;
		int n2 = nums2.length;
		int count = 0;
		int i = 0;
		int j = 0;
		double m = 0;
		double n = 0;

		if ((n1 + n2) % 2 != 0) {
			while (i < n1 && j < n2) {
				if ((nums1[i] <= nums2[j]) && (count <= (n1 + n2) / 2)) {
					m = nums1[i++];
					count++;
				} else if ((nums1[i] >= nums2[j]) && (count <= (n1 + n2) / 2)) {
					m = nums2[j++];
					count++;
				}
			}
			while (i < n1 && (count <= (n1 + n2) / 2)) {
				m = nums1[i++];
				count++;
			}
			while (j < n2 && (count <= (n1 + n2) / 2)) {
				m = nums2[j++];
				count++;
			}

			return m;
		} else if ((n1 + n2) % 2 == 0) {

			while (i < n1 && j < n2) {
				n = m;
				if ((nums1[i] <= nums2[j]) && (count <= (n1 + n2) / 2)) {
					m = nums1[i++];
					count++;
				} else if ((nums1[i] >= nums2[j]) && (count <= (n1 + n2) / 2)) {
					m = nums2[j++];
					count++;
				}
			}

			while (i < n1 && (count <= (n1 + n2) / 2)) {
				n = m;
				m = nums1[i++];
				count++;
			}
			while (j < n2 && (count <= (n1 + n2) / 2)) {
				n = m;
				m = nums2[j++];
				count++;
			}

			return (m + n) / 2;
		}
		return -1;
	}

	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		if (nums2.length < nums1.length)// [1,2] [3]
			return findMedianSortedArrays(nums2, nums1);
		int n1 = nums1.length;
		int n2 = nums2.length;
		int low = 0, high = n1;

		while (low <= high) {
			int cut1 = (low + high) >> 1;
			int cut2 = (n1 + n2 + 1) / 2 - cut1;

			int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
			int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];// [1,2] [3, 4]

			int right1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
			int right2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];

			if (left1 <= right2 && left2 <= right1) {
				if ((n1 + n2) % 2 == 0)
					return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
				else
					return Math.max(left1, left2);
			} else if (left1 > right2) {
				high = cut1 - 1;
			} else {
				low = cut1 + 1;
			}
		}
		return 0.0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
