package multithreading.threadpool;

import java.util.LinkedList;

public class RunnablePool implements Runnable {

	private LinkedList<Runnable> taskQueue;
	Thread currThread;
	private boolean isStopped;

	public RunnablePool(LinkedList<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
		this.currThread = null;
		this.isStopped = false;
	}

	@Override
	public void run() {
		Runnable runnable;
		currThread = Thread.currentThread();
		while (!isStopped) {
			synchronized (taskQueue) {
				// if there is no task in the queue then this method is blocked indefinitely
				while (taskQueue.isEmpty()) {
					try {
						taskQueue.wait();
					} catch (InterruptedException e) {

					}
				}
				runnable = (Runnable) taskQueue.removeFirst();
			}
			runnable.run();
		}
	}

//	// made this synchronized to make sure the value of isStopped is written back to
//	// the memory and the updated data is visible to the threads
//	public void stop() {
//		synchronized (taskQueue) {
//			isStopped = true;
//			/// breaks the thread out the removeFirst() call
//			currThread.interrupt();
//
//		}
//
//	}

}
