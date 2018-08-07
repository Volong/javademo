package github.io.volong.jcpb;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class TestUnsafe1 {

	static final Unsafe unsafe;
	
	private volatile long state = 0;
	
	static final long stateOffset;
	
	static {
		try {
			// 反射获取 Unsafe 的成员变量 theUnsafe
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			
			// 设置为可存取
			field.setAccessible(true);
			
			// 获取该变量的值
			unsafe = (Unsafe) field.get(null);
			
			// 获取 state 在 TestUnsafe1 中的偏移量
			stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}
	
	public static void main(String[] args) {
		TestUnsafe1 testUnSafe = new TestUnsafe1();
		
		boolean compareAndSwapInt = unsafe.compareAndSwapInt(testUnSafe, stateOffset, 0, 1);
		System.out.println(compareAndSwapInt);
	}
	
}
