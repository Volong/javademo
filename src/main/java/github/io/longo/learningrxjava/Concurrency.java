package github.io.longo.learningrxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @since 2019-09-10 18:38
 */
public class Concurrency {


    @Test
    public void interval() {

        Observable.interval(1, TimeUnit.SECONDS)
                  .map(i -> i + " SECONDS")
                  .subscribe(System.out::println);

        sleep(5000);
    }

    @Test
    public void intenseCalculation() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .map(s -> intenseCalculation((s)))
                  .subscribe(System.out::println);

        Observable.range(1, 6).map(s -> intenseCalculation(s)).subscribe(System.out::println);
    }

    /**
     * 并发处理
     */
    @Test
    public void subscribeOn() {

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .subscribeOn(Schedulers.computation())
                  .map(s -> intenseCalculation(s))
                  .subscribe(System.out::println);

        Observable.range(1, 6)
                  .subscribeOn(Schedulers.computation())
                  .map(s -> intenseCalculation(s))
                  .subscribe(System.out::println);

        sleep(20000);
    }

    /**
     * 对多个线程中的结果进行操作
     */
    @Test
    public void zip() {

        Observable<String> observable1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<Integer> observable2 = Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        // 将多个线程中的结果合并
        Observable.zip(observable1, observable2, (s, i) -> s + "-" + i).subscribe(System.out::println);

        // 收集多个线程中的结果
        Observable.merge(observable1, observable2).subscribe(System.out::println);

        sleep(20000);
    }

    /**
     * 阻塞操作
     */
    @Test
    public void blockingSubscribe() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .subscribeOn(Schedulers.computation())
                  .map(Concurrency::intenseCalculation)
                  .blockingSubscribe(System.out::println);

    }

    @Test
    public void newThread() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .subscribeOn(Schedulers.newThread())
                  .subscribe(System.out::println);

        sleep(20000);
    }

    @Test
    public void schedulerFrom() {

        ExecutorService executor = Executors.newFixedThreadPool(20);

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .subscribeOn(Schedulers.from(executor))
                  .subscribe(System.out::println);

        sleep(2000);
    }


    private static <T> T intenseCalculation(T value) {
        sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
