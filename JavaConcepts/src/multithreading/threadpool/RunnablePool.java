package multithreading.threadpool;

import java.util.LinkedList;

public class RunnablePool implements Runnable {

	private LinkedList<Runnable> taskQueue;

	public RunnablePool(LinkedList<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		Runnable runnable;
		while (true) {
			synchronized (taskQueue) {
				while (taskQueue.isEmpty()) {
					try {
						taskQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				runnable = (Runnable) taskQueue.removeFirst();
			}
			runnable.run();
		}
	}

}
