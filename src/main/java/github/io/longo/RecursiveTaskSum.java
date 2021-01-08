package github.io.longo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * @author yihailong
 * @since 2020-12-25 14:55
 */
public class RecursiveTaskSum extends RecursiveTask<Long> {

	
	private final long[] numbers;
	
	private final int startIndex;
	
	private final int endIndex;
	
	private static final long THRESHOLD = 10_000L;

	public RecursiveTaskSum(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public RecursiveTaskSum(long[] numbers, int startIndex, int endIndex) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	protected Long compute() {
		
		int length = endIndex - startIndex;
		
		if (length <= THRESHOLD) {
			
			long result = 0;
			for (int i = startIndex; i < endIndex; i++) {
				result += numbers[i];
			}
			
			return result;
		}
		
		int tempEndIndex = startIndex + length / 2;
		
		RecursiveTaskSum firstTask = new RecursiveTaskSum(numbers, startIndex, tempEndIndex);
		firstTask.fork();

		RecursiveTaskSum secondTask = new RecursiveTaskSum(numbers, tempEndIndex, endIndex);
		secondTask.fork();

		System.out.println("before join");
		Long join = firstTask.join();
		System.out.println("after join");
		Long join1 = secondTask.join();
		
		return join + join1;
	}

	public static void main(String[] args) {

//		recursiveTask();
		batchTaskDefect();

//		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 353, Executors.newCachedThreadPool());
//
//		CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 354);
//
//		try {
//			System.out.println(completableFuture.get());
//			System.out.println(integerCompletableFuture.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		
//		CompletableFuture.runAsync(() -> System.out.println(346));
//
//		ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//		CompletableFuture<Integer> thenApply = CompletableFuture.supplyAsync(() -> {
//			System.out.println("supplyAsync");
//			sleep(3);
//			return "Java";
//		}, executorService).thenApply(e -> {
//			System.out.println("thenApply");
//			return e.length();
//		});
//
//		System.out.println("----------------------");
//
//		try {
//			System.out.println(thenApply.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		
//		executorService.shutdown();
	}

	private static void recursiveTask() {
		long[] numbers = LongStream.rangeClosed(1, 9_000_000).toArray();

		RecursiveTaskSum recursiveTaskSum = new RecursiveTaskSum(numbers);

		Long sum = ForkJoinPool.commonPool().invoke(recursiveTaskSum);

		assert sum == LongStream.rangeClosed(1, 9_000_000).sum();
	}

	private static void batchTaskDefect() {

		ExecutorService executorService = Executors.newCachedThreadPool();
		ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

		List<Callable<Integer>> tasks = Arrays.asList(
			() -> {
				sleep(30);
				System.out.println("sleep 30s");
				return 30;
			},
			() -> {
				sleep(10);
				System.out.println("sleep 10s");
				return 10;
			},
			() -> {
				sleep(20);
				System.out.println("sleep 20s");
				return 20;
			}
		);

		tasks.forEach(completionService::submit);

		for (int i = 0; i < tasks.size(); i++) {
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}


	}
	
	private static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
