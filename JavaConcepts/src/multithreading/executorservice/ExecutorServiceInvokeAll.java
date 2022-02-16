package multithreading.executorservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ExecutorServiceInvokeAll {
	private static Callable<String> sleepAndGet(int i, String msg) {
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return i + " " + msg;
			}

		};
		return callable;
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
//			ExecutorService threadPoolExecutor = new ThreadPoolExecutor(5, 10, 3000, TimeUnit.MICROSECONDS,
//					new ArrayBlockingQueue<>(128));
		List<Callable<String>> callables = Arrays.asList(sleepAndGet(2, "Bravo"), sleepAndGet(1, "Alpha"),
				(sleepAndGet(3, "Charlie")));
		List<String> results = executorService.invokeAll(callables, 100, TimeUnit.MILLISECONDS).stream()
				.peek(future -> System.out.println(future.toString() + "is done: " + future.isDone()
						+ " || is cancelled: " + future.isCancelled()))
				.map(future -> {
					try {
						return future.get();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}).collect(Collectors.toList());
		System.out.println("results: " + results);
		executorService.shutdown();
	}
}
