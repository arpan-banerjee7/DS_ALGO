package multithreading.threadpoolblockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CustomThreadPool {

	private BlockingQueue<Runnable> taskQueue = null;
	private List<RunnablePool> workers;

	public CustomThreadPool(int threadNumber, int maxTasks) {
		taskQueue = new ArrayBlockingQueue<>(maxTasks);
		workers = new ArrayList<>();
		for (int i = 0; i < threadNumber; i++) {

			// initialize and add the runnable instances
			workers.add(new RunnablePool(taskQueue));
			// create threads and start
			new Thread(workers.get(i)).start();
		}

	}

	// put tasks in the queue
	public void execute(Runnable r) {
		taskQueue.offer(r);
	}

	public void doStop() {
		synchronized (taskQueue) {
			for (RunnablePool worker : workers) {
				worker.stop();
			}
		}

	}

	public void waitUntilAllTasksOver() {
		while (!taskQueue.isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
