package github.io.volong.java8.functional.programming;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectToList {

	public static void main(String[] args) {
		
		List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
		assertEquals(collect, Arrays.asList("a", "b", "c"));
	}
}
