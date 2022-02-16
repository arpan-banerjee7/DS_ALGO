package multithreading.printsequences;

public class SharedSequenceGen {
	private int number = 1;
	private int numberOfThreads;
	private int totalNumbersInSequence;

	public SharedSequenceGen(int numberOfThreads, int totalNumbersInSequence) {
		super();
		this.numberOfThreads = numberOfThreads;
		this.totalNumbersInSequence = totalNumbersInSequence;
	}

	public void printNumbers(int result) {
		synchronized (this) {
			while (number < totalNumbersInSequence-1) {
				while (number % numberOfThreads != result) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println(Thread.currentThread().getName() + " " + number++);
				notifyAll();
			}
		}
	}
}