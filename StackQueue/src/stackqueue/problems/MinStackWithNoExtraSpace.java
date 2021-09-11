package stackqueue.problems;

import java.util.Stack;

// https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
// https://youtu.be/ZvaRHYYI0-4 Aditya Verma

public class MinStackWithNoExtraSpace extends Stack<Integer> {

	private static final long serialVersionUID = 1L;
	Integer minEle;

	void push(int x) {
		if (isEmpty() == true) {
			minEle = x;
			super.push(x);
			// System.out.println("push element "+x);
		} else {
			if (x >= minEle) {
				super.push(x);
				// System.out.println("push element "+x);
			} else {
				int newValue = 2 * x - minEle;
				super.push(newValue);
				// System.out.println("push element "+newValue);
				minEle = x; // Note: update should take place after the push, to retrieve the prev minEle
							// while poping out the element
			}
		}
	}

	public Integer pop() {
		if (isEmpty() == true) {
			return -1;
		}

		Integer t = super.peek();
		super.pop();

		if (t >= minEle) {
			return t;
		} else {
			int actualValue = minEle; // minEle is the actual value
			minEle = 2 * minEle - t; // minEle is set to the prev minEle
			return actualValue;
		}

	}

	public Integer peek() {
		if (isEmpty() == true)
			return -1;

		Integer t = super.peek();
		if (t < minEle) {
			return minEle;
		}
		return t;

	}

	public Integer getMin() {
		if (isEmpty() == true)
			return -1;

		return minEle;
	}

	public static void main(String[] args) {
		MinStackWithNoExtraSpace s = new MinStackWithNoExtraSpace();
		s.push(3);
		s.push(5);
		System.out.println(s.getMin());
		s.push(2);
		s.push(1);
		System.out.println(s.getMin());
		s.pop();
		System.out.println(s.getMin());
		s.pop();
		System.out.println(s.peek());
	}

}
