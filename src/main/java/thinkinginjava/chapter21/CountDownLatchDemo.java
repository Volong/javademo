package thinkinginjava.chapter21;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskPortion implements Runnable {
	
	private static int counter = 0;
	
	private final int id = counter++;
	
	private static Random rand = new Random(47);
	
	private final CountDownLatch latch;
	
	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this + "completed");
		latch.countDown();
	}
	
	public String toString() {
		return String.format("%1$-3d", id);
	}
	
}

class WaitingTask implements Runnable {

	private static int counter = 0;
	
	private final int id = counter++;
	
	private final CountDownLatch latch;
	
	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {

		try {
			latch.await();
			System.out.println(latch.getCount());
			System.out.println("Latch barrier passed for " + this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return String.format("WaitingTask %1$-3d", id);
	}
}

public class CountDownLatchDemo {

	static final int SIZE = 90;
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(10);
		
		for (int i = 0; i < 100; i++) {
			exec.execute(new WaitingTask(latch));
		}
		
		for (int i = 0; i < 100; i++) {
			exec.execute(new TaskPortion(latch));
		}
		
		System.out.println("Launched all tasks");
		exec.shutdown();
	}
}

