package multithreading.printsequences;

public class SharedSequenceRunnable implements Runnable{
	private SharedSequenceGen sharedSequenceGen;
	private int result;

	public SharedSequenceRunnable(SharedSequenceGen sharedSequenceGen, int result) {
		super();
		this.sharedSequenceGen = sharedSequenceGen;
		this.result = result;
	}

	@Override
	public void run() {
		sharedSequenceGen.printNumbers(result);
	}
}
