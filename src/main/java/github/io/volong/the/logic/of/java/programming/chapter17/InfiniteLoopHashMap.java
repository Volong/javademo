package github.io.volong.the.logic.of.java.programming.chapter17;

import java.util.HashMap;
import java.util.Random;

/**
 * 
 *  HashMap 无限死循环
 *
 */
public class InfiniteLoopHashMap {

    public static void unsafeConcurrentUpdate() {
        final HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < 1000; i++) {
            
            Thread t = new Thread() {
                
                Random rnd = new Random();
                
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        map.put(rnd.nextInt(), 1);
                    }
                }
            };
            
            t.start();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
