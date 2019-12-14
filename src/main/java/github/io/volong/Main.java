package github.io.volong;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, String> map = new LinkedHashMap<Integer, String>(16, .75f, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return super.removeEldestEntry(eldest);
            }
        };

        map.put(1, "a");
        map.put(2, "a");
        map.put(3, "a");

        System.out.println(map.get(1));

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
        Thread.currentThread().join();
    }

}
