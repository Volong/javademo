package github.io.volong.j8fp.chapter02;

import com.github.benmanes.caffeine.cache.Weigher;
import com.google.common.base.Predicate;

import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;

public class Example {

    public static void main(String[] args) {

        Predicate<Integer> tPredicate = x -> x > 5;
        System.out.println( tPredicate.apply(6));

        BinaryOperator<Long> kvWeigher = (x, y) -> x + y;
    }
}
