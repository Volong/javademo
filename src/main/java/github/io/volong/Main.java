package github.io.volong;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    private static ArrayList<String> array = new ArrayList<String>();
    //独占锁
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static Lock readLock = lock.readLock();
    private final static Lock writeLock = lock.writeLock();

    
	public static void main(String[] args) throws InterruptedException {
	    
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                writeLock.lock();
                System.out.println("write already lock");
                
//                readLock.lock();
//                System.out.println("read already lock");
            }
        }).start();
        
        Thread.sleep(1000);
        
        
        
        array.add("yi");

	    readLock.lock();
	    System.out.println("read1 already lock");
	    
	    readLock.lock();
	    System.out.println("read2 already lock");
	    
	    System.out.println(array);
	}
}
