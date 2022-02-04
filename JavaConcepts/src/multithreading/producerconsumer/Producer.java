package multithreading.producerconsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	BlockingQueue<String> blockingQueue = null;

	public Producer(BlockingQueue<String> queue) {
		this.blockingQueue = queue;
	}

	@Override
	public void run() {
		while (true) {
			long timeMillis = System.currentTimeMillis();
			try {
				this.blockingQueue.put("" + timeMillis);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Producer was interrupted");
			}
		}
	}

}