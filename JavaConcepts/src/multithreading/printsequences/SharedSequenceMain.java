package multithreading.printsequences;

public class SharedSequenceMain {

	private static Runnable getSharedRunnable(SharedSequenceGen sharedSequenceGen, int seq) {
		return () -> {
			sharedSequenceGen.printNumbers(seq);
		};
	}

	public static void main(String[] args) {
		int threads=3;
		int limit=5;
		SharedSequenceGen sharedSequenceGen = new SharedSequenceGen(threads, limit);
		Thread t1 = new Thread(new SharedSequenceRunnable(sharedSequenceGen, 1));
		Thread t2 = new Thread(new SharedSequenceRunnable(sharedSequenceGen, 2));
		Thread t3 = new Thread(getSharedRunnable(sharedSequenceGen, 0));

		t1.start();
		t2.start();
		t3.start();

	}
}
