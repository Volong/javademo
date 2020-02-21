package github.io.longo.jcpb;

import java.util.concurrent.locks.LockSupport;

public class TestParkAndUnpark {

	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("child thread begin park!");
				
				// 被阻塞
				LockSupport.park();
				
				System.out.println("child thread unpark!");
			}
		});
		
		
		thread.start();
		
		Thread.sleep(3000);
		
		System.out.println("main thread begin unpark!");
		
		// thread 线程会获取许可证，然后 park() 方法会返回
		LockSupport.unpark(thread);
		
	}
}
