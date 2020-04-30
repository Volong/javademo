package github.io.longo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] arr1 = {1, 0 , 4, 3, 8, -1, 7, 6 , 4, 10};

        int[][] arr2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {4, 3, 2, 1},
                {8, 7, 6, 5}
        };

        for (int i = 0; i < arr2.length * arr2[0].length; i++) {
            int row = i / arr2[0].length;
            int col = i % arr2[0].length;

            System.out.print(arr2[row][col]);

            if (col == (arr2[0].length - 1)) {
                System.out.println();
            }
        }

        sort(arr1);

        System.out.println(Arrays.toString(arr1));

    }

    public static void sort(int[] arr) {
        lomutoSort(arr, 0, arr.length - 1);
    }

    public static void lomutoSort(int[] arr, int low, int high) {

        if (low < high) {
            int i = lomutoPartition(arr, low, high);
            lomutoSort(arr, low, i - 1);
            lomutoSort(arr, i + 1, high);
        }
    }

    public static int lomutoPartition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low;

        for (int j = i; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, high);

        return i;
    }


    public static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high]= temp;
    }
}
