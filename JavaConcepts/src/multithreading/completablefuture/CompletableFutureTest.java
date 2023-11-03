package multithreading.completablefuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CompletableFutureTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		List<CompletableFuture<Integer>> futures = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			final int a = i + 1;
			if (i == 10) {
				futures.add(CompletableFuture.supplyAsync(() -> {
					throw new RuntimeException("Something went wrong" + a);
				}));
			}
			futures.add(CompletableFuture.supplyAsync(() -> a));
		}

		
		// Use your custom allOfOrException method
		CompletableFuture<List<Integer>> result = allOfOrException(futures);

		try {
			List<Integer> resultList = result.get();
			System.out.println("Results: " + resultList);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}

	public static <T> CompletableFuture<List<T>> allOfOrException(Collection<CompletableFuture<T>> futures) {
		CompletableFuture<List<T>> result = new CompletableFuture<>();
		AtomicInteger remaining = new AtomicInteger(futures.size());
		AtomicBoolean hasFailed = new AtomicBoolean(false);

		futures.forEach(future -> future.handle((value, ex) -> {
			if (ex != null) {
				if (hasFailed.compareAndSet(false, true)) {
					result.completeExceptionally(ex);
				}
			} else if (remaining.decrementAndGet() == 0 && !hasFailed.get()) {
				result.complete(futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
			}
			return null;
		}));

		return result;
	}
}
