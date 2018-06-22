package github.io.volong;

public class Main {

    public static void main(String[] args) {
    	System.out.println((1 << 31) -1);
    	System.out.println(0x7fffffff);
    }
    
    public static final int hash(Object k) {
        int h = 0;

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
}
