package github.io.longo.jcip;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程饥饿死锁: 
 * 
 * 在单线程的 Executor 中，如果一个任务将另一个任务提交到同一个 Executor，并且等待这个被提交任务的结果，那么通常会发生死锁。
 * 第二个任务停留在工作队列中，并等待第一个任务的完成，而第一个任务又无法完成，因为它在等待第二个任务的完成。
 * 
 * 在更大的线程池中，如果所有正在执行任务的线程都由于等待其他仍处于工作队列中的任务而阻塞，那么会发生同样的问题。
 * 
 * 只要线程池中的任务需要无限地等待一些必须由池中其他任务才能提供的资源或条件，
 * 例如某个任务等待另一个任务的返回值或执行结果，那么除非线程池足够大，否则将发生线程饥饿死锁。
 * 
 * 
 */
public class ThreadDeadLock {

	private static ExecutorService exec = Executors.newSingleThreadExecutor();

	// private static ExecutorService exec = Executors.newFixedThreadPool(2);

	
	public class LoadFileTask implements Callable<String> {

		private final String fileName;

		public LoadFileTask(String fileName) throws InterruptedException {
			this.fileName = fileName;
			System.out.println(this.fileName);

		}

		@Override
		public String call() throws Exception {
			// 处理文件
			System.out.println("处理完成:" + this.fileName);
			return this.fileName;
		}

	}

	public class RenderPageTask implements Callable<String> {

		@Override
		public String call() throws Exception {

			Future<String> header, footer;
			header = exec.submit(new LoadFileTask("header.html"));
			footer = exec.submit(new LoadFileTask("footer.html"));
			String page = renderBody();
			// 将发生死锁 --- 由于任务在等待子任务的结果
			return header.get() + page + footer.get();
		}

		private String renderBody() {
			// 渲染 body
			return "renderBody";
		}
	}

	public static void main(String[] args) throws Exception {
		ThreadDeadLock t = new ThreadDeadLock();
		RenderPageTask r = t.new RenderPageTask();

		Future<String> submit = exec.submit(r);
		System.out.println(submit.get());
	}
}
