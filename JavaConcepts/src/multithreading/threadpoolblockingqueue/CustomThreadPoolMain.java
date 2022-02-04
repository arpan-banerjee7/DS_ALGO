package multithreading.threadpoolblockingqueue;

public class CustomThreadPoolMain {

	public static void main(String[] args) {
		CustomThreadPool threadPool = new CustomThreadPool(5, 10);
		for (int i = 0; i < 10; i++) {
			int taskNo = i;
			// add tasks in queue of threadPool, it expects a Runnable instance
			threadPool.execute(() -> {
				String message = Thread.currentThread().getName() + " Task: " + taskNo;
				System.out.println(message);
			});
			// threadPool.execute(new CustomTask());
		}

		threadPool.waitUntilAllTasksOver();
		threadPool.doStop();

	}

	// another way
	private static class CustomTask implements Runnable {
		@Override
		public void run() {
			int taskNo;
			for (int i = 0; i < 10; i++) {
				taskNo = i;
				String message = Thread.currentThread().getName() + "Task: " + taskNo;
				System.out.println(message);
			}
		}

	}

}
