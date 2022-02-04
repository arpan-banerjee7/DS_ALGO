package multithreading.threadpoolblockingqueue;

import java.util.concurrent.BlockingQueue;

public class RunnablePool implements Runnable {

	private BlockingQueue<Runnable> taskQueue;
	Thread currThread;
	private boolean isStopped;

	public RunnablePool(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
		this.currThread = null;
		this.isStopped = false;
	}

	@Override
	public void run() {
		Runnable runnable = null;
		currThread = Thread.currentThread();
		while (!isStopped) {
			// if there is no task in the queue then this method is blocked indefinitely
			try {
				runnable = (Runnable) taskQueue.take();
			} catch (InterruptedException e) {
				// handle
			}
			runnable.run();
		}
	}

	// made this synchronized to make sure the value of isStopped is written back to
	// the memory and the updated data is visible to the threads
	public void stop() {
		isStopped = true;
		/// breaks the thread out the removeFirst() call
		currThread.interrupt();

	}

}
