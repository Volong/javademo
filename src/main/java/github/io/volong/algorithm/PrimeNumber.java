package github.io.volong.algorithm;

/**
 * 
 * 判断一个数是不是质数
 * 
 */
public class PrimeNumber {

    public static void main(String[] args) {
        System.out.println(isPrimeNumber(0));
        System.out.println(isPrimeNumber(1));
        System.out.println(isPrimeNumber(2));
        System.out.println(isPrimeNumber(3));
        System.out.println(isPrimeNumber(5));
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
        
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < sqrt; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
