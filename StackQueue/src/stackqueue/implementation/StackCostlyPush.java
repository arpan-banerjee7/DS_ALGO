package stackqueue.implementation;

import java.util.LinkedList;
import java.util.Queue;

// https://www.geeksforgeeks.org/implement-stack-using-queue/

// TC-push - O(n)
// TC-pop - O(1)
public class StackCostlyPush {

	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(1);
		s.push(5);
		s.push(3);

		System.out.println("current size: " + s.size());
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());

	}

	static class Stack {
		static Queue<Integer> q1 = new LinkedList<Integer>();
		static Queue<Integer> q2 = new LinkedList<Integer>();

		// To maintain current number of elements
		static int curr_size;

		Stack() {
			curr_size = 0;
		}

		void push(int x) {
			curr_size++;
			while (!q1.isEmpty()) {
				q2.add(q1.peek());
				q1.remove();
			}

			q1.add(x);
			while (!q2.isEmpty()) {
				q1.add(q2.peek());
				q2.remove();
			}
		}

		public int size() {
			return curr_size;
		}

		public int top() {
			if (q1.isEmpty())
				return -1;
			return q1.peek();
		}

		void pop() {
			// if no elements are there in q1
			if (q1.isEmpty())
				return;
			q1.remove();
			curr_size--;
		}
	}

}
