package github.io.volong;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    static class Singleton {
        private volatile static Singleton instance;

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        public static void main(String[] args) {

            Integer[] integers = new Integer[2];
            Integer[] copy = integers;
            integers[0] = 1;
            System.out.println(integers == copy);

            Integer[] integers1 = new Integer[3];
            integers = integers1;
            System.out.println(integers == copy);
        }
    }
}
