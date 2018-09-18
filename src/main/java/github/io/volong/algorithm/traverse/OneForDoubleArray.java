package github.io.volong.algorithm.traverse;

/**
 *  一个 for 循环遍历二维数组 
 */
public class OneForDoubleArray {
    
    public static void main(String[] args) {

        int[][] arr = { 
                        { 62, 35, 62, 23, 1, 4 }, 
                        { 32, 25, 34, 69, 2, 5 }, 
                        { 15, 24, 26, 833, 3, 6 } 
                      };

        for (int i = 0; i < arr.length * arr[0].length; i++) {
            
            final int row = i / arr[0].length;
            final int col = i % arr[0].length;
            
            System.out.print(arr[row][col] + ", ");
            
            if ((i % arr[0].length) == (arr[0].length - 1)) {
                System.out.println();
            }
        }
    
    }
}
