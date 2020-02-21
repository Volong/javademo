package github.io.longo.jcpb;

import sun.misc.Unsafe;

/**
 *  
 *  运行结果会报错，报错信息如下：
 *  <pre>
 *  Exception in thread "main" java.lang.ExceptionInInitializerError
 *	Caused by: java.lang.SecurityException: Unsafe
 *		at sun.misc.Unsafe.getUnsafe(Unsafe.java:90)
 *		at github.io.volong.jcpb.TestUnSafe.<clinit>(TestUnSafe.java:23)
 *
 *  </pre>
 *  
 *  @see TestUnsafe1
 *  
 */
public class TestUnSafe {

	/**
	 *  获取 Unsafe 的实例
	 *  
	 */
	static final Unsafe unsafe = Unsafe.getUnsafe();
	
	private volatile long state = 0;

	// 记录变量 state 在类 TestUnsafe 中的偏移量 
	static final long stateOffset;
	
	static {
		try {
			stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
			
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println(e.getLocalizedMessage());
            throw new Error(e);
		}
		
	}
	
	public static void main(String[] args) {
		
		TestUnSafe test = new TestUnSafe();
		boolean compareAndSwapInt = unsafe.compareAndSwapInt(test, stateOffset, 0, 	1);
		System.out.println(compareAndSwapInt);
	}
	
}
