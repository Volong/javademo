package algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @date 2018年3月16日 下午4:27:27
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        
        int[] arr = {2, 6, 0, 8, 4, -1};
        
        quickSort(arr, arr.length, 0);
        
        System.out.println(Arrays.toString(arr));
    }
    
    public static void quickSort(int[] arr, int length, int pivotIndex) {
        
        if (length < 2) {
            return;
        }
        
        int pivot = arr[pivotIndex];
        for (int i = 0; i < length; i++) {
            if (arr[i] > arr[pivotIndex]) {
                int temp = arr[i];
                arr[i] = pivot;
                arr[pivotIndex] = temp;
                pivotIndex = i;
            }
        }
        quickSort(arr, pivotIndex + 1, 0);
        quickSort(arr, length - pivotIndex, pivotIndex);
        
    }

}
