package github.io.longo.tlojp.chapter08;


/**
 * 限制给定的参数类型上界为某个具体类
 */
public class NumberPair<U extends Number, V extends Number> extends Pair<U, V>{

	public NumberPair(U fitst, V second) {
		super(fitst, second);
	}

	/**
	 * 限定给定的参数必须是某个接口
	 * 
	 * 例：限定类型必须实现 Comparable 接口
	 * 
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T max(T[] arr) {
		T max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].compareTo(max) > 0) {
				max = arr[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		NumberPair<Integer, Double> pair = new NumberPair<>(10, 10.11);
		// 当类型不是 Number 时，会报错
//		NumberPair<Integer, String> pair1 = new NumberPair<>(10, "10.11");
	}
}
