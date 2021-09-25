package problems;

import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/kth-largest-element-in-a-stream/
// https://leetcode.com/problems/kth-largest-element-in-a-stream/
// https://www.youtube.com/watch?v=G5CVOjWDAmY

// small variation- https://www.youtube.com/watch?v=3DdP6Ef8YZM&list=WL&index=2&t=190s

//lets say, we get one element and sort it, and then another element again sort
//if we apply merge sort then TC o(n^2logn), insertion sort- o(n^2) 

//TC-so we introduce heap tc-o(nlogn)+o(mlogk)// leetcode sol disc
public class KthLargestInStream {
	int heapSize;
	PriorityQueue<Integer> minHeap;

	// maintain a in heap of k elements, then the top element will be the kth
	// largest
	// till that point in stream
	public KthLargestInStream(int k, int[] nums) {
	        heapSize=k;
	        minHeap=new PriorityQueue<>();
	        for(int n:nums){
	            minHeap.add(n);
	            if(minHeap.size()>k){
	                minHeap.poll();
	            }
	        }
	    }

	public int add(int val) {
		if (minHeap.size() < heapSize) {
			minHeap.add(val);
			if (minHeap.size() == heapSize) {
				return minHeap.peek();
			} else {
				return -1;
			}
		}

		// ignoring elements which are smaller than minHeap peek()
		// as they won t affect the kth largest element ans
		if (val > minHeap.peek()) {
			minHeap.poll();
			minHeap.add(val);
		}
		return minHeap.peek();
	}

	public static void main(String[] args) {
		int[] nums =  { 4, 5, 8, 2 };
		KthLargestInStream kthLargest = new KthLargestInStream(3, nums);
		System.out.println(kthLargest.add(3));   // return 4
		System.out.println(kthLargest.add(5));  // return 5
		System.out.println(kthLargest.add(10));  // return 5
		System.out.println(kthLargest.add(9));   // return 8
		System.out.println(kthLargest.add(4));   // return 8

	}

}
