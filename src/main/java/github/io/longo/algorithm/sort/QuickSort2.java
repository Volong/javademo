package github.io.longo.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 */
public class QuickSort2 {

    public static void main(String[] args) {
        
        int[] arr = {2, 6, 8, 0, 4, -1};
        
        quickSort(arr);
        
        System.out.println(Arrays.toString(arr));
    }
    
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 
     * 分区
     * 
     * @param arr 
     * @param left       开始位置
     * @param right      结束位置
     * @param pivotIndex 基准位置
     * @return
     */
    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        
        int pivotValue = arr[pivotIndex]; // 2
        int storeIndex = left; // 0
        
        // 将基准位置的值临时保存在右边
        swap(arr, pivotIndex, right);
        
        // -1, 6, 8, 0, 4, 2 
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) { // ? < 2
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        
        // 将基准位置的值与新的基准值互换
        // 这就保证了基准值的左边都比它小，右边都比它大
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
