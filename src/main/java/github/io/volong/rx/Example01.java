package github.io.volong.rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @since 2019-07-22 09:01
 */
public class Example01 {

    public static void main(String[] args) {

        Observable<Object> observable = Observable.create(emiter -> {
            emiter.onNext("a");
            emiter.onNext("b");
            emiter.onNext("c");
            emiter.onNext("d");
            emiter.onComplete();
        });

        observable.subscribe(System.out::println);
    }

    public static void hello(String ...args) {
        Flowable.fromArray(args).subscribe(System.out::println);
    }
}
