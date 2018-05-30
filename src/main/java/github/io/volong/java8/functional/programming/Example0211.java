package github.io.volong.java8.functional.programming;



import java.util.function.BinaryOperator;

import com.google.common.base.Predicate;

public class Example0211 {

	public static void main(String[] args) {
		Predicate<Integer> atLeast5 = x -> x > 5;
		System.out.println(atLeast5.apply(10));
		
		BinaryOperator<Long> addLongs = (x, y) -> x + y;
		System.out.println(addLongs.apply(10L, 10L));
	}
}
