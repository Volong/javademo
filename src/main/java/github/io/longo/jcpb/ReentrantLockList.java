package github.io.longo.jcpb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockList {

    private List<String> array = new ArrayList<>();
    
    private volatile ReentrantLock lock = new ReentrantLock();
    
    public void add(String e) {
        
        lock.lock();
        
        try {
            array.add(e);
        } finally {
            lock.unlock();
        }
    }
    
}
