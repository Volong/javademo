package github.io.volong.learningrxjava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @since 2019-08-14 10:00
 */
public class BasicOperator {

    /**
     * 输出字符串长度不等于5的字符
     */
    @Test
    public void filter() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s -> s.length() != 5).subscribe(System.out::println);
    }


    @Test
    public void take() throws InterruptedException {
        // 拿前三个字符串
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .take(3).subscribe(System.out::println);

        // 每秒发出1条数据，但是take只接收5秒内的数据
        Observable.interval(1, TimeUnit.SECONDS)
                  .take(5, TimeUnit.SECONDS).subscribe(System.out::println);

        Thread.currentThread().join();
    }

    /**
     * （跳过）忽略指定条数的信息
     */
    @Test
    public void skip() {

        Observable.range(1, 100)
                  .skip(90)
                  .subscribe(System.out::println);
    }

    /**
     * 有条件的拿
     */
    @Test
    public void takeWhile() {
        Observable.range(1,100)
                  .takeWhile(i -> i < 5)
                  .subscribe(System.out::println);
    }

    /**
     * 有条件的跳过
     */
    @Test
    public void skipWhile() {
        Observable.range(1, 100)
                  .skipWhile(i -> i <= 95)
                  .subscribe(System.out::println);
    }

    /**
     * 去掉重复的元素
     */
    @Test
    public void distinct() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                  .map(String::length)
                  .distinct()
                  .subscribe(System.out::println);
    }

    /**
     * 去掉连续重复的元素
     */
    @Test
    public void distinctUntilChanged() {
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                  .distinctUntilChanged()
                  .subscribe(System.out::println);
    }

    /**
     * 获取指定位置处的元素，如果指定位置处的元素不存在，则会返回一个空
     */
    @Test
    public void elementAt() {
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                  .elementAt(7)
                  .subscribe(System.out::println);
    }
}
