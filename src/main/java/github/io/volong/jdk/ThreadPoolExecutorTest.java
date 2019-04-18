package github.io.volong.jdk;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @time 2019-04-18
 */
public class ThreadPoolExecutorTest {

    /**
     * 测试在什么情况下，会初始化所有的核心线程
     */
    @Test
    public void testPrestartAllCoreThreads() {
        // ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        executorService.prestartAllCoreThreads();

        System.out.println((1 << (Integer.SIZE - 3)) - 1);
    }
}
