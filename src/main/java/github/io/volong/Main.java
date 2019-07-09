package github.io.volong;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

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

            ThreadLocalRandom mainCurrent = ThreadLocalRandom.current();
            int i = mainCurrent.nextInt(5);
            System.out.println("i:" + i);
            int i1 = mainCurrent.nextInt(5);
            System.out.println("i1:" + i1);

            new Thread(new Runnable() {

                @Override
                public void run() {
                    ThreadLocalRandom i2Current = ThreadLocalRandom.current();
                    int i2 = i2Current.nextInt(5);
                    System.out.println("i2:" + i2);
                    System.out.println(i2Current == mainCurrent);
                }
            }).start();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    ThreadLocalRandom i3Current = ThreadLocalRandom.current();
                    int i3 = mainCurrent.nextInt(5);
                    System.out.println("i3:" + i3);
                    System.out.println(i3Current == mainCurrent);
                }
            }).start();
        }
    }
}
