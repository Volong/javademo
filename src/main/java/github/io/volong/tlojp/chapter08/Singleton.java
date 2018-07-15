package github.io.volong.tlojp.chapter08;

public class Singleton<T> {

	/*
	 * 泛型类声明的类型参数，可以在实例变量和方法中使用
	 * 但是在静态变量和静态方法中是不能使用的
	 * 由于类型擦除，Singleton 类型只有一份，静态类型与静态方法都是类型属性，与类型参数无关
	 * 如果合法，那么对于每种实例化的类型都要有对应的静态变量与静态方法
	 * 
	 * 不过静态方法可以是泛型方法，可以声明自己的类型参数，这个参数与泛型类的类型参数无关
	 */
	/*
	private static T instance;
	public static synchronized T getInstance() {
		return null;
	}
	*/
	
	public static synchronized <E> void getInstance(E e) {
		System.out.println("泛型静态方法");
	}
	
}
