package github.io.volong;

import org.apdplat.word.dictionary.DictionaryFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        InheritableThreadLocal<Object> inheritableThreadLocal = new InheritableThreadLocal<>();

        inheritableThreadLocal.set("1111");

        System.out.println(Thread.currentThread().getName());


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            Object o = inheritableThreadLocal.get();
            System.out.println(o);

            inheritableThreadLocal.set("3333");

        }).start();

        System.out.println(inheritableThreadLocal.get());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            Object o = inheritableThreadLocal.get();
            System.out.println(o);
        }).start();

        System.out.println(inheritableThreadLocal.get());

    }


    public static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];
        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);


    }
}
