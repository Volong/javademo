package github.io.longo.j8fp.chapter02;

import java.util.function.BinaryOperator;

import com.google.common.base.Predicate;

public class Example {

    public static void main(String[] args) {

        Predicate<Integer> tPredicate = x -> x > 5;
        System.out.println( tPredicate.apply(6));

        BinaryOperator<Long> kvWeigher = (x, y) -> x + y;
    }
}
