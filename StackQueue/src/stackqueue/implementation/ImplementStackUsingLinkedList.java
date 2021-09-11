package stackqueue.implementation;

// https://www.geeksforgeeks.org/implement-a-stack-using-singly-linked-list/

public class ImplementStackUsingLinkedList {
	// Java program to Implement a stack
	// using singly linked list
	// import package

	// A linked list node
	private class Node {

		int data; // integer data
		Node link; // reference variable Node type
	}

	// create global top reference variable global
	Node top;

	// Constructor
	ImplementStackUsingLinkedList() {
		this.top = null;
	}

	// Utility function to add an element x in the stack
	public void push(int x) // insert at the beginning
	{
		// create new node temp and allocate memory
		Node temp = new Node();

		// initialize data into temp data field
		temp.data = x;

		// put top reference into temp link
		temp.link = top;

		// update top reference
		top = temp;
	}

	// Utility function to check if the stack is empty or not
	public boolean isEmpty() {
		return top == null;
	}

	// Utility function to return top element in a stack
	public int peek() {
		// check for empty stack
		if (!isEmpty()) {
			return top.data;
		} else {
			System.out.println("Stack is empty");
			return -1;
		}
	}

	// Utility function to pop top element from the stack
	public void pop() // remove at the beginning
	{
		// check for stack underflow
		if (top == null) {
			System.out.print("\nStack Underflow");
			return;
		}

		// update the top pointer to point to the next node
		top = (top).link;
	}

	public void display() {
		// check for stack underflow
		if (top == null) {
			System.out.printf("\nStack Underflow");
		} else {
			Node temp = top;
			while (temp != null) {

				// print node data
				System.out.printf("%d->", temp.data);

				// assign temp link to temp
				temp = temp.link;
			}
		}
	}

	public static void main(String[] args) {
		// create Object of Implementing class
		ImplementStackUsingLinkedList obj = new ImplementStackUsingLinkedList();
		// insert Stack value
		obj.push(11);
		obj.push(22);
		obj.push(33);
		obj.push(44);

		// print Stack elements
		obj.display();

		// print Top element of Stack
		System.out.printf("\nTop element is %d\n", obj.peek());

		// Delete top element of Stack
		obj.pop();
		obj.pop();

		// print Stack elements
		obj.display();

		// print Top element of Stack
		System.out.printf("\nTop element is %d\n", obj.peek());
	}
}
