package algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 认为第一个元素已经排好序了，从第二个元素开始比较
 * 每次跟已经排好序的元素从后面往前面开始比较，如果小于当前元素，则当前元素往后移
 * 
 * 时间复杂读：O(n^2)
 * 空间复杂度：O(1)
 * 
 * @author volong
 *
 */
public class InsertSort {

	public static void main(String[] args) {
		
		int[] arr = {2, 6, 5, 0, 8, 4, -1};
		
		for (int i = 0; i < arr.length - 1; i++) {
			
			for (int j = i + 1; j > 0; j--) {
				
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
		
	}
}
