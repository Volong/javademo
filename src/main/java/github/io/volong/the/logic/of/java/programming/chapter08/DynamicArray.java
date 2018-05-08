package github.io.volong.the.logic.of.java.programming.chapter08;

import java.util.Arrays;
import java.util.Random;

import javax.servlet.FilterRegistration.Dynamic;

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
//		numbers1.addAll(1.2);
		
//		DynamicArray<?> numbers2 = new DynamicArray<>();
//		numbers2.add(1);
		
	}
}
