package multithreading.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomThreadPool {

	private LinkedList<Runnable> taskQueue;
	private List<RunnablePool> workers;

	public CustomThreadPool(int threadNumber) {
		taskQueue = new LinkedList<>();
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
		synchronized (taskQueue) {
			taskQueue.addLast(r);
			taskQueue.notify();
		}
	}

}
