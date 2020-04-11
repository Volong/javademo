package github.io.longo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] arr = {1, 0 , 4, 3, 8, 7, 6 , 4, 10};

        int length = arr.length;

        for (int end = length - 1; end > 0; end--) {

            for (int i = 0; i < end; i++) {

                if (arr[i] > arr[i+1]) {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

    }

}
