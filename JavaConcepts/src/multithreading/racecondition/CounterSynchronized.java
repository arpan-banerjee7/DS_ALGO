package multithreading.racecondition;

public class CounterSynchronized {
	private long count = 0;

	public long increment() {
		synchronized (this) {
			this.count++;
			return this.count;
		}
	}

	public long getCount() {
		synchronized (this) {
			return this.count;
		}
	}
}
