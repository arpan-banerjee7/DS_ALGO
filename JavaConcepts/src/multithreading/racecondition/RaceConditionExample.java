package multithreading.racecondition;

public class RaceConditionExample {

	public static void main(String[] args) {
		Counter counter = new Counter();
		CounterSynchronized counterSynchronized = new CounterSynchronized();
		Thread thread1 = new Thread(createRunnable(counterSynchronized, "Thread 1: final count-"));
		Thread thread2 = new Thread(createRunnable(counterSynchronized, "Thread 2: final count-"));
		thread1.start();
		thread2.start();

	}

	// to get parameter into the run method of runnable interface
	private static Runnable createRunnable(CounterSynchronized counterSynchronized, String message) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					counterSynchronized.increment();
				}
				System.out.println(Thread.currentThread().getName() + " " + counterSynchronized.getCount());
			}
		};
		return runnable;
	}

	
	
	// Java 8
	public static Runnable getRunnable(Counter counter, String message) {
		return () -> {
			for (int i = 0; i < 100000; i++) {
				counter.increment();
			}
			System.out.println(Thread.currentThread().getName() + " " + counter.getCount());
		};
	}

}
