package github.io.volong.jcpb;

import java.util.concurrent.locks.LockSupport;

/**
 *  没有拿到 LockSupport 关联的许可证，线程将会被挂起
 */
public class TestPark {

	public static void main(String[] args) {
		
		System.out.println("begin park");
		
		LockSupport.park();
		
		System.out.println("end park");
	}
}
