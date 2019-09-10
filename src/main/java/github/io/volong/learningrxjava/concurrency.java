package github.io.volong.learningrxjava;

import io.reactivex.Observable;
import org.apdplat.word.vector.T;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @since 2019-09-10 18:38
 */
public class concurrency {


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
