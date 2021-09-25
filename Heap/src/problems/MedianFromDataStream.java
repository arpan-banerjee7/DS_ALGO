package problems;

import java.util.PriorityQueue;

// https://leetcode.com/problems/find-median-from-data-stream/
// https://www.youtube.com/watch?v=1LkOrc-Le-Y&t=5s
// https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/

//Insertion sort approach - TC o(n^2)
//why insertion sort? because it always maintains the sorting order
//and using other sorting algos, the best TC we can acive is o(n^2 log n)
//If we try to solve this only using min heap or max heap then TC- o(n^2logn)

//so we consider taking first half in Max heap, next half in Min heap
//median = top of max heap if n is odd, if n is even it is (Max heap top+Min heap top)/2

//assumption max heap has an elemeny extra
//[2,3,4,5,7]- max heap [2,3,4] min heap[5,7] meadian for this would be 4
//Major conditons
//1) when min heap == max heap==0
//2) when max heap > min heap && min heap==0
//3) when max heap > min heap && min heap>0 
//eg maxHeap=[2,4] minHeap=[6] num=7, so insert in minHeap (tricky)
public class MedianFromDataStream {
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;

	/** initialize your data structure here. */
	public MedianFromDataStream() {
		this.minHeap = new PriorityQueue<>();
		this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
	}

	public void addNum(int num) {
		int leftSize = maxHeap.size();
		int rightSize = minHeap.size();

		// CASE 1 max heap is 0, since we are taking an extra value in max heap
		// so at this point we will just insert in max heap
		if (leftSize == 0) {
			maxHeap.add(num);
		}
		// CASE 2 element ideally has to be inserted in the max heap(extra value) but we
		// cannot blindly do that, we have to check if the element is smaller than min
		// heap top
		// then insert in max heap, else that element should belong to the second half
		// so we have to pop an element from min heap insert num in min heap, and insert
		// the
		// popped out element in max heap to maintain the extra count
		else if (leftSize == rightSize) {
			// Case a
			if (num < minHeap.peek()) {
				maxHeap.add(num);
			} else {// Case b - rearrange
				int temp = minHeap.poll();
				minHeap.add(num);
				maxHeap.add(temp);
			}
		}

		// CASE 3 max heap > min heap
		else {
			// Case a-ideally it should go into minHeap to maintain order(since even
			// numbermnow)
			// but before adding check if num>maxHeap.peek()-> insert in minHeap, else
			// //rearrange
			// Case- max heap > min heap && min heap==0
			if (rightSize == 0) {
				if (num > maxHeap.peek()) {
					minHeap.add(num);
				} else { // Case b- rearrange
					int temp = maxHeap.poll();
					minHeap.add(temp);
					maxHeap.add(num);
				}
			}
			// max heap > min heap && min heap>0
			// eg maxHeap=[2,4] minHeap=[6] num=7, so insert in minHeap
			else if (num >= minHeap.peek()) {
				minHeap.add(num);
			} else {
				// eg maxHeap=[2,4] minHeap=[6] num=3, so rearrangement required
				// pop from max heap insert num, push popped in min heap
				if (num < maxHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.add(num);
					minHeap.add(temp);
				} else {
					// eg maxHeap=[2,4] minHeap=[6] num=5, so insert in minHeap
					minHeap.add(num);
				}
			}

		}
	}

	public double findMedian() {
		int leftSize = maxHeap.size();
		int rightSize = minHeap.size();
		if (leftSize > rightSize) {
			return (double) (maxHeap.peek());
		} else {
			return ((maxHeap.peek() + minHeap.peek() + 0.0) / 2);
		}
	}

	public static void main(String[] args) {
		MedianFromDataStream obj = new MedianFromDataStream();
		System.out.println("Median after insertion of each data: ");
		obj.addNum(5);
		double param_2 = obj.findMedian();
		System.out.println("5 -> " + param_2);

		obj.addNum(3);
		param_2 = obj.findMedian();
		System.out.println("3 -> " + param_2);

		obj.addNum(4);
		param_2 = obj.findMedian();
		System.out.println("4 -> " + param_2);

		obj.addNum(2);
		param_2 = obj.findMedian();
		System.out.println("2 -> " + param_2);

		obj.addNum(6);
		param_2 = obj.findMedian();
		System.out.println("6 -> " + param_2);

	}

}
