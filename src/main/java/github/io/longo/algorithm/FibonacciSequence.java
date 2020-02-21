package github.io.longo.algorithm;

/**
 *  定义：F(0) = 1，F(1) = 1, F(n) = F(n-1) + F(n-2)（n >= 2，n ∈ N*）。
 *  F(0) 表示第零项。
 *  从第 2 项开始，每一项都等于前两项之和。
 *  当 n 越来越大，前一项与后一项的比值越来越接近黄金分割。
 *  
 *  来源：https://mp.weixin.qq.com/s/3LR-iVC4zgj0tGhZ780PcQ
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        System.out.println(recursion(10));
        System.out.println(forwardPass(10));
    }
    
    /**
     * 递归法：直接根据公式进行递归。
     * 
     * @param n
     * @return
     */
    public static int recursion(int n) {
        
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        
        return recursion(n - 1) + recursion(n - 2);
    }
    
    /**
     * 正推法。
     * 
     * 
     * @param n
     * @return
     */
    public static int forwardPass(int n) {
        
        int[] sum = new int[n + 1];
        
        sum[0] = 0;
        sum[1] = 1;
                
        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + sum[i - 2];
        }
        
        return sum[n];
    }
}
