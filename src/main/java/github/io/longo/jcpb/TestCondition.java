package github.io.longo.jcpb;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition {

	public static void main(String[] args) throws InterruptedException {
		
		ReentrantLock lock = new ReentrantLock();
		
		// 创建一个条件变量，一个 lock 可以对应多个条件变量
		Condition condition = lock.newCondition();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// 如果在没有获取到锁前调用了条件变量的 await 方法会抛出 java.lang.IllegalMonitorStateException 异常。
				lock.lock();
				
				try {
				    System.out.println("begin wait");
				    // 阻塞当前线程
				    condition.await();
				    
				    System.out.println("end wait");

				} catch (Exception e) {
				    e.printStackTrace();

				} finally {
				    lock.unlock();
				}
			}
		});
		
		t1.start();
		
		Thread.sleep(3000);

		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();//(6)
				try {
				    System.out.println("begin signal");
				    condition.signal();
				    System.out.println("end signal");
				} catch (Exception e) {
				    e.printStackTrace();

				} finally {
				    lock.unlock();
				}
			}
		});
		
		t2.start();
		
		
	}
}
