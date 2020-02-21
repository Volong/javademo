package github.io.longo.jpie.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotComparable {

	private int i;

	private NotComparable(final int i) {
		this.i = i;
	}

	public static void main(String[] args) {

		final List<NotComparable> objects = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			objects.add(new NotComparable(i));
		}
		try {
			// 将会报错，因为没有实现 Comparable 接口
			Arrays.sort(objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
