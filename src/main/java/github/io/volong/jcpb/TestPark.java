package github.io.volong.jcpb;

import java.util.concurrent.locks.LockSupport;

public class TestPark {

	public static void main(String[] args) {
		
		System.out.println("begin park");
		
		LockSupport.park();
		
		System.out.println("end park");
	}
}
