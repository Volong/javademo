package github.io.volong.learningrxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @since 2019-07-23 09:25
 */
public class Example {

    public static void main(String[] args) {


    }


    @Test
    public void create() {

        Observable<Object> observable = Observable.create(emitter -> {
            emitter.onNext("a");
            emitter.onNext("b");
            emitter.onNext("c");
            emitter.onNext("d");
            emitter.onNext("abcd");
            emitter.onComplete();
        });

        observable.subscribe(System.out::println);
    }

    @Test
    public void just() {

        List<String> items = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        Observable<String> source = Observable.fromIterable(items);

        source.map(String::length)
              .filter(i -> i >= 5)
              .subscribe(s -> System.out.println("RECEIVED: " + s));

    }

    /**
     * 自定义Observer
     */
    @Test
    public void implementingObserver() {

        Observable<String> just = Observable.just("a", "b", "c", "d", "abcd");

        Observer<Integer> myObserver = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next:" + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("done");
            }
        };

        just.map(String::length).filter(i -> i >= 5).subscribe(myObserver);
    }

    /**
     * Cold Observable : 可以重复消费。类似mp3，所有人都可以重头开始播放
     *
     * Observable.just() 与 Observable.fromIterable() 都是这种类型的Observable
     */
    @Test
    public void coldObservable() {
        Observable<String> just = Observable.just("a", "b", "c", "d", "abcd");

        // 每个订阅者都是独立，相互之间的数据不会受到影响
        just.subscribe(System.out::println);
        System.out.println();
        just.map(String::length).filter(i -> i == 1).subscribe(System.out::println);
        System.out.println();
        just.subscribe(System.out::println);
    }

    /**
     * Hot Observable : 类似于电台，后收听的人不会听到之前已经播放的东西
     *
     */
    @Test
    public void hotObservable() {

        ConnectableObservable<String> just = Observable.just("a", "b", "efg", "c", "d", "abcd").publish();

        // 所有的观察者都同时收到信息进行处理
        just.subscribe(System.out::println);

        System.out.println();

        just.map(String::length).filter(i -> i == 1).subscribe(System.out::println);

        just.connect();
    }

    @Test
    public void range() {
        Observable.range(3, 10).subscribe(System.out::println);
    }
}
