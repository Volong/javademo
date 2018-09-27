package github.io.volong.algorithm;

/**
 * 
 * 判断一个数是不是质数：在大于 1 的数中，除了 1 和该数之外，无法被其它自然数整除的数
 * 
 */
public class PrimeNumber {

    public static void main(String[] args) {
        System.out.println(isPrimeNumber(0));
        System.out.println(isPrimeNumber(1));
        System.out.println(isPrimeNumber(2));
        System.out.println(isPrimeNumber(3));
        System.out.println(isPrimeNumber(5));
        System.out.println(isPrimeNumber(9));
        System.out.println(isPrimeNumber(56));
        System.out.println(isPrimeNumber(87));
    }

    public static boolean isPrimeNumber(int number) {
        
        if (number == 2 || number == 3) {
            return true;
        }
        
        if (number % 2 == 0) {
            return false;
        }
        
        /*
         * 如果一个数可以被分解成两个数，那么一定是一个大于等于 sqrt(number)，另一个小于等于 sqrt(number)
         * 所以只需要遍历到 sqrt(number) 就可以了
         */
        int sqrt = (int) Math.sqrt(number) + 1;
        
        for (int i = 3; i < sqrt; i += 2) {
            // 余数为 0 即为可以整除
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
