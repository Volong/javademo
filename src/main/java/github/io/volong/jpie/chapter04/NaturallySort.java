package github.io.volong.jpie.chapter04;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class NaturallySort {

	@Test
	public void sortInt() {
		final int[] numbers = { -3, -5, 1, 7, 4, -2 };
		final int[] expected = { -5, -3, -2, 1, 4, 7 };

		Arrays.sort(numbers);

		assertArrayEquals(expected, numbers);
	}

	@Test
	public void sortObjects() {
		final String[] strings = { "z", "x", "y", "abc", "zzz", "zazzy" };
		final String[] expected = { "abc", "x", "y", "z", "zazzy", "zzz" };
		
		Arrays.sort(strings);
		
		assertArrayEquals(expected, strings);
	}
}
