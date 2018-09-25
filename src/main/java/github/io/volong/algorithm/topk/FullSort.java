package github.io.volong.algorithm.topk;

import java.util.Arrays;

/**
 * 全排序实现：将 n 个数全部排序之后，取出最大的 k 个
 * 
 */
public class FullSort {

	/**
	 * 从 {5,3,7,1,8,2,9,4,7,2,6,6} 这 n=12 个数中，找出最大的 k=5 个。
	 */
	public static void main(String[] args) {

		Integer[] arr = { 5, 3, 7, 1, 8, 2, 9, 4, 7, 2, 6, 6 };
		
		Arrays.sort(arr, (o1, o2) -> o2 - o1);
		
		Integer[] newArr = new Integer[5];
		
		System.arraycopy(arr, 0, newArr, 0, 5);
		
		System.out.println(Arrays.toString(newArr));

	}
}
