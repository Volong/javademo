package github.io.volong.jcpb;

import java.util.concurrent.locks.LockSupport;

public class TestParkWithCondition {

	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("child thread begin park!");
				
				// 只有当前线程被中断了之后才会返回
				while (!Thread.currentThread().isInterrupted()) {
					// 调用park方法，挂起自己
					LockSupport.park();
				}
				
                
                System.out.println("child thread unpark!");
			}
		});
		
		thread.start();
		
		// 即使调用了 unpark() 方法也不会返回
		LockSupport.unpark(thread);
		
		Thread.sleep(3000);
		
		System.out.println("main thread begin unpark!");
		
		thread.interrupt();
	}
}
