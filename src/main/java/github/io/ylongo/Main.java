package github.io.ylongo;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int[] arr1 = {1, 0 , 4, 3, 8, -1, 7, 6 , 4, 10};

        int[][] arr2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {4, 3, 2, 1},
                {8, 7, 6, 5}
        };

        sort(arr1);

        System.out.println(Arrays.toString(arr1));
    }

    public static void sort(int[] arr) {

        int length = arr.length;


        for (int i = length - 1; i > 0;) {

            int newEnd = 0;
            for (int j = 0; j < i; j++) {

                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    newEnd = j;
                }
            }

            i = newEnd;
        }
    }

    public static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high]= temp;
    }
}

