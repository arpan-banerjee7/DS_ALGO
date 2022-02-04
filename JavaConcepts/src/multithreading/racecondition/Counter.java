package multithreading.racecondition;

public class Counter {
	private long count = 0;

	public long increment() {
		this.count++;
		return this.count;
	}

	public long getCount() {
		return this.count;
	}
}
