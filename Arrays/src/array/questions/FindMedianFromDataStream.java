package array.questions;

import java.util.ArrayList;
import java.util.PriorityQueue;
// 295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/
// 2 approaches brute and two heaps
// https://www.youtube.com/watch?v=1LkOrc-Le-Y&t=5s Techdose

public class FindMedianFromDataStream {
	class MedianFinder {
		PriorityQueue<Integer> maxHeap;
		PriorityQueue<Integer> minHeap;
		int size;

		public MedianFinder() {
			maxHeap = new PriorityQueue<>((a, b) -> b - a);
			minHeap = new PriorityQueue<>();
			size = 0;
		}

		public void addNum(int num) {
			if (maxHeap.isEmpty() && minHeap.isEmpty()) {
				maxHeap.add(num);
			} else if (maxHeap.size() > minHeap.size()) {
				if (maxHeap.peek() > num) {// this top should lie in minheap and num in max heap
					minHeap.add(maxHeap.poll());
					maxHeap.add(num);
				} else {
					minHeap.add(num);
				}
			} else if (maxHeap.size() == minHeap.size()) {
				if (num < minHeap.peek()) {// should go in maxHeap
					maxHeap.add(num);
				} else {
					maxHeap.add(minHeap.poll());
					minHeap.add(num);
				}
			}
			size++;
		}

		public double findMedian() {
			if (size % 2 == 0) {
				return (maxHeap.peek() + minHeap.peek() + 0.0) / 2;
			} else {
				return maxHeap.peek() + 0.0;
			}
		}
	}

	/**
	 * Your MedianFinder object will be instantiated and called as such:
	 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
	 * obj.findMedian();
	 */

	class MedianFinder1 {

		ArrayList<Integer> a;

		public MedianFinder1() {
			a = new ArrayList<>();
		}

		public void addNum(int num) {
			if (a.size() == 0) {
				a.add(num);
				return;
			}
			a.add(num);
			int j = a.size() - 2;
			while (j >= 0 && a.get(j) > num) {
				a.set(j + 1, a.get(j));
				j--;
			}
			a.set(j + 1, num);
		}

		public double findMedian() {
			if (a.size() % 2 == 0) {
				return (a.get(a.size() / 2) + a.get((a.size() - 1) / 2) + 0.0) / 2;
			} else {
				return a.get(a.size() / 2) + 0.0;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
