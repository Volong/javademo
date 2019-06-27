package github.io.volong;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.Executors;

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
            Singleton instance = Singleton.getInstance();
            System.out.println(instance);
        }
    }
}
