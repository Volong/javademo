package github.io.volong.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static final CountDownLatch LATCH = new CountDownLatch(3);
    
    public static void main(String[] args) {
        
        Thread thread1 = new Thread() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("线程1 执行完成");
                LATCH.countDown();
            }
        };
        
        Thread thread2 = new Thread() {
            
            @Override
            public void run() {
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("线程2 执行完成");
                LATCH.countDown();
            }
        };
        
        Thread thread3 = new Thread() {
            
            @Override
            public void run() {
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("线程3 执行完成");
                LATCH.countDown();
            }
        };
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        try {
            // 当计数变成 0 的时候才会继续往下执行
            LATCH.await();
        } catch (InterruptedException e) {
            System.out.println("可以被其它线程中断");
        }
        
        System.out.println("所有线程都执行完成");
    }
}
