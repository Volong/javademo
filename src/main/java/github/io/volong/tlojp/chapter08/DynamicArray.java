package github.io.volong.tlojp.chapter08;

import java.util.Arrays;
import java.util.Random;

public class DynamicArray<E> {
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int size;
	
	private Object[] elementData;
	
	
	public DynamicArray() {
		this.elementData = new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * 添加元素到数组中
	 * 
	 * @param e 
	 */
	public void add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
	}

	/**
	 * 上界为其它类型参数
	 * 
	 * <T extends E> 定义类型参数，声明了一个类型参数 T，可以放在泛型类类名的后面、泛型方法返回值的前面
	 * 
	 * @param c
	 */
	public <T extends E> void addAll(DynamicArray<T> c) {
		for (int i = 0; i < c.size; i++) {
			add(c.get(i));
		}
	}
	
	/**
	 * 上界为其它类型参数
	 * 
	 * <? extends E> 用于实例化类型参数，用于实例化泛型变量中的类型参数，不知道这个具体类型，只知道是 E 或 E 的某个子类
	 * 
	 * @param c
	 */
	public void addAll1(DynamicArray<? extends E> c) {
		for (int i = 0; i < c.size; i++) {
			add(c.get(i));
		}
	}
	
	/**
	 * 保证容量
	 * 
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int length = elementData.length;
		
		if (length >= capacity) {
			return;
		}
		
		if (length < capacity) {
			elementData = Arrays.copyOf(elementData, length * 2);
		}
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) elementData[index];
	}
	
	public int size() {
		return size;
	}
	
	/**
	 * 替换指定位置上的元素
	 * 
	 * @param index 下标
	 * @param element 元素
	 * @return
	 */
	public E set(int index, E element) {
		E oldValue = get(index);
		elementData[index] = element;
		return oldValue;
	}

	/**
	 * 获取元素下标
	 * @param <T>
	 * 
	 * @param arr
	 * @param elm
	 * @return
	 */
	public static <T> int indexOf(DynamicArray<T> arr, Object elm) {
		for (int i = 0; i < arr.size; i++) {
			if (arr.get(i).equals(elm)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void swap(DynamicArray<?> arr, int i, int j) {
		swapInternal(arr, i, j);
	}
	
	public static <T> void swapInternal(DynamicArray<T> arr, int i, int j) {
		T tmp = arr.get(j);
		arr.set(i, arr.get(j));
		arr.set(j, tmp);
	}

	
	public static <T extends Comparable<? super T>> T max(DynamicArray<T> arr) {
		T max = arr.get(0);
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).compareTo(max) > 0) {
				max = arr.get(i);
			}
		}
		
		return max;
	}
	
	/*
	 * 下面这两个重载方法会报错
	 * 虽然参数都是 DynamicArray，但是实例化类型不同，一个是 DynamicArray<Integer>，一个是 DynamicArray<String>
	 * 看似是两个不同类型的参数，但是类型擦除之后，它们的声明是一样的
	 */
//	public static void test(DynamicArray<Integer> ints) {};
//	public static void test(DynamicArray<String> strs) {};
	
	public static void main(String[] args) {
		
		DynamicArray<Double> dynamicArray = new DynamicArray<>();
		Random random = new Random();
		int size = random.nextInt(100);
		for (int i = 0; i < size; i++) {
			dynamicArray.add(Math.random());
		}
		
		System.out.println(dynamicArray.get(random.nextInt(size)));
		
		DynamicArray<Number> numbers = new DynamicArray<>();
		DynamicArray<Integer> ints = new DynamicArray<>();
		
		ints.add(1);
		ints.add(2);
		numbers.addAll(ints);
		
		DynamicArray<Double> dous = new DynamicArray<>();
		dous.add(1.2);
		dous.add(1.3);
		numbers.addAll(dous);
		
		
//		DynamicArray<? extends Number> numbers1 = new DynamicArray<>();
//		numbers1 = ints;
		/*
		 * 会报错；因为 <? extends Number> 表示是 Number 的某个子类型，但是不知道具体子类型
		 * 如果允许写入，Java 无法保证安全性
		 * 所以只能允许读，不能写
		 * 
		 */
//		numbers1.addAll(dous);
		
		DynamicArray<?> numbers2 = new DynamicArray<>();
		numbers2 = dous;
		
		DynamicArray<Child> childs = new DynamicArray<>();
		childs.add(new Child(20));
		childs.add(new Child(20));
		
		Child child = max(childs);
		System.out.println(child);
		
		// 泛型对象的 getClass 方法的返回值与原始类型对象相同
		System.out.println(DynamicArray.class == numbers.getClass()); // true
		System.out.println(DynamicArray.class == ints.getClass());    // true
		System.out.println(numbers.getClass() == ints.getClass());    // true
		
		/*
		 * 这种方式是错误的
		 * 因为 instanceof 是运行时判断，与泛型无关 
		 */
//		if (numbers instanceof DynamicArray<Number>) {
//			
//		}
		
		// 但是却支持这种写法
		// 我的理解是 ? 表示任意类型，也就是与泛型无关
		// 不过这样写没有什么意义，因为无论如何这都是成立的
		if (numbers instanceof DynamicArray<?>) {
			System.out.println(1111);
		}
	}
}
