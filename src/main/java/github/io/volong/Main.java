package github.io.volong;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		
		Task1 task1 = new Task1();
		
		pool.schedule(task1, 5, TimeUnit.SECONDS);//延迟5s后，执行且只执行一次task1
	}
}
