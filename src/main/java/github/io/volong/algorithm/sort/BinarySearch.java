package github.io.volong.algorithm.sort;

public class BinarySearch {

    public static void main(String[] args) {

        int srcArray[] = { 3, 5, 11, 17, 21, 23, 28, 30, 32, 50, 64, 78, 81, 95, 101 };
        
        int target = 30;
        
        int begin = 0;
        int end = srcArray.length - 1;
        
        for (int i = 0; i < srcArray.length; i++) {
            int mid = (begin + end) / 2;
            if (target == srcArray[mid]) {
                System.out.println("目标元素的位置为:" + mid);
                break;
            }
            if (srcArray[mid] < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
            
            if (i == srcArray.length - 1) {
                System.out.println("没有找到该元素");
            }
        }
    }
}
