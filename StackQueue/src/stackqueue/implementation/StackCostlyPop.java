package stackqueue.implementation;

import java.util.LinkedList;
import java.util.Queue;

// https://www.geeksforgeeks.org/implement-stack-using-queue/
// https://www.youtube.com/watch?v=ww5Ac232WEU&list=PLEJXowNB4kPzEvxN8ed6T13Meet7HP3h0&index=3

//TC-push - O(1)
//TC-pop - O(n)
public class StackCostlyPop {

	static class Stack {
		static Queue<Integer> q1 = new LinkedList<Integer>();
		static Queue<Integer> q2 = new LinkedList<Integer>();

		// To maintain current number of elements
		static int curr_size;

		Stack() {
			curr_size = 0;
		}

		void push(int x) {
			q1.add(x);
			curr_size++;
		}

		public int size() {
			return curr_size;
		}

		public int top() {
			if (q1.isEmpty())
				return -1;

			while (q1.size() != 1) {
				q2.add(q1.peek());
				q1.remove();
			}

			// last pushed element
			int temp = q1.peek();
			q1.remove();

			while (!q2.isEmpty()) {
				q1.add(q2.peek());
				q2.remove();
			}
			q1.add(temp);

			return temp;
		}

		void pop() {
			if (q1.isEmpty())
				return;

			while (q1.size() != 1) {
				q2.add(q1.peek());
				q1.remove();
			}

			// Pop the only left element from q1
			q1.remove();
			curr_size--;

			while (!q2.isEmpty()) {
				q1.add(q2.peek());
				q2.remove();
			}
		}
	}

	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);

		System.out.println("current size: " + s.size());
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		System.out.println("current size: " + s.size());

	}

}
