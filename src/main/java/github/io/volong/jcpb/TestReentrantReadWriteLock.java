package github.io.volong.jcpb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.Test;

public class TestReentrantReadWriteLock {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    private static Lock writeLock = lock.writeLock();
    
    private static Lock readLock = lock.readLock();

    /**
     * 在同一个线程中，先获取写锁，不会影响后续读锁的获取 
     * @throws InterruptedException 
     */
    @Test
    public void writeReadOnSameThreadTest() {
        
        writeLock.lock();
        
        System.out.println("write is lock");
        
        readLock.lock();
        
        System.out.println("read is lock");
    }
    
    /**
     * 在同一个线程中，先获取读锁，再获取写锁时会被挂起
     */
    @Test
    public void readWriteOnSameThreadTest() {
        
        readLock.lock();
        
        System.out.println("read is lock");
        
        writeLock.lock();
        
        System.out.println("write is lock");
    }
    
    /**
     * 在一个线程中先获取到写锁，在另一线程中获取读锁时，会被挂起 
     */
    @Test
    public void writeReadOnTwoThread() throws InterruptedException {
        
        new Thread(() -> {
                writeLock.lock();
                System.out.println("write is lock");
            }
        ).start();
        
        Thread.sleep(1000);
        
        readLock.lock();
        
        System.out.println("read is lock");
        
    }
    
    /**
     * 在一个线程中先获取到读锁，在另一个线程中获取写锁时，会被挂起 
     */
    @Test
    public void readWriteOnTwoThread() throws InterruptedException {
        
        new Thread(() -> {
            readLock.lock();
            System.out.println("read is lock");
        }).start();
        
        Thread.sleep(1000);
        
        writeLock.lock();
        
        System.out.println("write is lock");
    }
    
    
}
