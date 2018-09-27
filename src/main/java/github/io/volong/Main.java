package github.io.volong;

import java.util.concurrent.atomic.LongAdder;

public class Main {

	public static void main(String[] args) {
		LongAdder longAdder = new LongAdder();
		longAdder.add(2);
		System.out.println(longAdder);
	}
}
