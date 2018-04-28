package github.io.volong.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @date 2018年3月16日 下午4:27:27
 *
 */
public class QuickSort2 {

    public static void main(String[] args) {
        
        int[] arr = {2, 6, 0, 8, 4, -1};
        
        quickSort(arr);
        
        System.out.println(Arrays.toString(arr));
    }
    
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        
        int pivotValue = arr[pivotIndex];
        int storeIndex = left;
        
        swap(arr, pivotIndex, right);
        
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        
        swap(arr, right, storeIndex);
        
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static void quickSort(int[] arr, int left, int right) {
        
        if (right > left) {
            int partition = partition(arr, left, right, left);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
    }
}
