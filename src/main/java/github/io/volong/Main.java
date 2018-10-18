package github.io.volong;

import java.util.concurrent.locks.StampedLock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println((8 > 1) ? 1 << 16 : 0);
		
		StampedLock lock = new StampedLock();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long writeLock = lock.writeLock();
				System.out.println(writeLock);
			}
		}).start();
		
		Thread.sleep(1000);
		
		long writeLock2 = lock.writeLock();
		System.out.println(writeLock2);
	}
}
