package github.io.volong.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 依次比较相邻的元素，如果前一个元素大于后一个元素则交换位置
 * 所以每次比较完就会找出一个最大的元素
 * 
 * 时间复杂度：O(n^2)
 * 空间复杂度:O(1)
 * 
 */
public class BubbleSort {

	public static void main(String[] args) {
		
		int[] arr = {2, 6, 0, 8, 4, -1};
		
		for (int i = 0; i < arr.length -1; i++) {
			
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
		
	}
}
