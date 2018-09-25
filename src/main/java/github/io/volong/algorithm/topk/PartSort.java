package github.io.volong.algorithm.topk;

import java.util.Arrays;

/**
 * 局部排序：不全局排序，只对最大的 k 个排序
 */
public class PartSort {

	public static void main(String[] args) {
		
		Integer[] arr = { 5, 3, 7, 1, 8, 2, 9, 4, 7, 2, 6, 6 };
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] < arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
		Integer[] newArr = new Integer[5];
		
		System.arraycopy(arr, 0, newArr, 0, 5);
		
		System.out.println(Arrays.toString(newArr));
	}
}
