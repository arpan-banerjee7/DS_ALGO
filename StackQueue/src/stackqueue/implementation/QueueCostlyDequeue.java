package stackqueue.implementation;

import java.util.Stack;

// https://www.geeksforgeeks.org/queue-using-stacks/
// https://www.youtube.com/watch?v=xSa0sD-RqMg&list=PLEJXowNB4kPzEvxN8ed6T13Meet7HP3h0&index=2 Techdose

// TC-enQueue - O(1)
// TC-deQueue - O(n)
public class QueueCostlyDequeue {

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
			// Push item into s1
			s1.push(x);
		}

		int deQueue() {

			while (s1.isEmpty()) {
				System.out.println("Q is Empty");
			}

			while (s1.size() != 1) {
				s2.push(s1.pop());
			}

			int x = s1.peek();
			s1.pop();

			while (!s2.isEmpty()) {
				s1.push(s2.pop());
			}
			return x;

		}
	}

}
