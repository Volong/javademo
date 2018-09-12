package github.io.volong.jcpb;

import java.util.concurrent.locks.LockSupport;

/**
 *  如果当前线程没有调用 park()，则在调用 unpark() 方法后，再调用 park()，会立刻返回
 */
public class TestUnpark {

	public static void main(String[] args) {
		
		System.out.println("begin park!");
		
		// 获取当前线程的许可证
		LockSupport.unpark(Thread.currentThread());
		
		LockSupport.park();
		
		System.out.println("end park!");
	}
}
