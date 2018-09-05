package github.io.volong.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序 
 * 第一次选择最小的元素与第一个元素交换
 * 然后从剩下的元素中选择最小的元素与未排序元素的第一个元素交换。
 * 
 */
public class SelectSort {

    public static void main(String[] args) {
        
        int[] arr = {2, 6, 0, 8, 4, -1};
        
        for (int i = 0; i < arr.length; i++) {
            
            int minIndex = i;
            
            for (int j = i + 1; j < arr.length; j++) {
                
                // 每次跟最小的比较
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        
        System.out.println(Arrays.toString(arr));
    }
    
    
}
