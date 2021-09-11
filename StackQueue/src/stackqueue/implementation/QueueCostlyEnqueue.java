package stackqueue.implementation;

import java.util.Stack;

// https://www.geeksforgeeks.org/queue-using-stacks/
// https://www.youtube.com/watch?v=xSa0sD-RqMg&list=PLEJXowNB4kPzEvxN8ed6T13Meet7HP3h0&index=2 Techdose

// TC-enQueue - O(n)
// TC-deQueue - O(1)
public class QueueCostlyEnqueue {
	public static void main(String[] args) {
		Queue q = new Queue();
		q.enQueue(1);
		q.enQueue(5);
		q.enQueue(3);

		System.out.println(q.deQueue());
		q.enQueue(2);
		System.out.println(q.deQueue());
	}

	static class Queue {
		static Stack<Integer> s1 = new Stack<Integer>();
		static Stack<Integer> s2 = new Stack<Integer>();

		void enQueue(int x) {
			// Move all elements from s1 to s2
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			// Push item into s1
			s1.push(x);

			// Push everything back to s1
			while (!s2.isEmpty()) {
				s1.push(s2.pop());
			}
		}

		int deQueue() {

			while (s1.isEmpty()) {
				System.out.println("Q is Empty");
			}

			int x = s1.peek();
			s1.pop();
			return x;

		}
	}

}
