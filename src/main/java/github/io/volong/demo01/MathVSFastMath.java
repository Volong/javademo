package github.io.volong.demo01;

import java.util.Random;

import net.jafama.FastMath;

public class MathVSFastMath {

    public static void main(String[] args) {
        
        Random random = new Random();
        
        long start1 = System.currentTimeMillis();
        double x = random.nextDouble() * 10000;
        for(int i = 0; i < 100000; i++) {
            Math.tanh(x);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Math.tanh:" + (end1 - start1) + "ms");
        
        
        long start2 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            FastMath.tanh(x);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("FastMath.tanh:" + (end2 - start2) + "ms");
        
        
        long start3 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            Math.sqrt(x);

        }
        long end3 = System.currentTimeMillis();
        System.out.println("Math.sqrt:" + (end3 - start3) + "ms");
        
        long start4 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            FastMath.sqrt(x);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("FastMath.sqrt:" + (end4 - start4) + "ms");
        
    }
}
