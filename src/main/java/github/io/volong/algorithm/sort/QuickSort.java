package github.io.volong.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 选取一个基准值，小于基准值的元素放基准值的左边，大于基准值的元素防基准值的右边
 * 对小于/大于基准值的元素再执行如上步骤
 * 
 * 时间复杂度：最佳(平均)：O(nlogn)；最坏：O(n^2) 
 * 空间复杂度：O(1)
 **********************************************************************
 *
 * 自己写的一个最简单的实现。最原始的快速排序思想
 */
public class QuickSort {

    public static void main(String[] args) {
        
        int[] arr = {2, 6, 0, 8, 4, -1};
        
        int[] quickSort = quickSort(arr);
        
        System.out.println(Arrays.toString(quickSort));
        
    }

    public static int[] quickSort(int[] arr) {
    	
    	if (arr.length < 2) {
    		return arr;
    	}
    	
    	int pivot = arr[0];
    	int[] lowArr = new int[arr.length];
    	int[] highArr = new int[arr.length];
    	int lowIndex = 0;
    	int highIndex = 0;
    	
    	for (int i = 0; i < arr.length; i++) {
			if (arr[i] < pivot) {
				lowArr[lowIndex] = arr[i];
				lowIndex++;
			} else if (arr[i] > pivot) {
				highArr[highIndex] = arr[i];
				highIndex++;
			}
		}
    	
    	int[] lowSort = quickSort(Arrays.copyOf(lowArr, lowIndex));
		int[] highSort = quickSort(Arrays.copyOf(highArr, highIndex));
		
		return merge(lowSort, pivot, highSort);
    }

    public static int[] merge(int[] lowArr, int pivot, int[] highArr) {
    	
    	int lowIndex = lowArr.length;
    	int highIndex = highArr.length;
    	
    	int[] mergeArr = new int[lowIndex + highIndex + 1];
    	
    	for (int i = 0; i < lowArr.length; i++) {
			mergeArr[i] = lowArr[i];
		}
    	mergeArr[lowIndex] = pivot;
    	
    	for (int i = 0; i < highArr.length; i++) {
			mergeArr[lowIndex + 1 + i] = highArr[i];
		}
    	
    	return mergeArr;
    }
}
