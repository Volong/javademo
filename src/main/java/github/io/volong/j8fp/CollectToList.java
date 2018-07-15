package github.io.volong.j8fp;


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
