package github.io.volong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String str = "dfdffdsafas";

//        if (str == null || str.length() == 0) {
//            return 0;
//        }

        int count = 0;
        int n = str.length();

        for (int i = n - 1; i >= 0; i--) {

            for (int j = i; j < n; j++) {

                if (i == j) { // 相邻的元素
                    count++;
                } else if (i + 1 == j && str.charAt(i) == str.charAt(j)) { // 隔了一个元素
                    count++;
                } else if (str.charAt(i) == str.charAt(j) && str.charAt(i + 1) == str.charAt(j - 1)) { // 隔了几个元素
                    count++;
                }
            }
        }

        System.out.println(count);
    }

}
