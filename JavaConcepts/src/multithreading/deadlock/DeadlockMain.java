package multithreading.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockMain {
	public static void main(String[] args) {

		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();

		Runnable runnable1 = new RunnableSync1(lock1, lock2);
		Runnable runnable2 = new RunnableSync2(lock1, lock2);

		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);

		thread1.start();
		thread2.start();

	}
	
	// DEADLOCK
//	Thread-0 attempting to lock Lock 1
//	Thread-0 locked Lock 1
//	Thread-1 attempting to lock Lock 2
//	Thread-1 locked Lock 2
//	Thread-0 attempting to lock Lock 2
//	Thread-1 attempting to lock Lock 1

}
