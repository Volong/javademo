package github.io.volong;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		list.add("a");

		for (String s : list) {
			list.remove(s);
		}
		// Iterator<Integer> iterator = list.iterator();
		// while(iterator.hasNext()){
		// 	Integer integer = iterator.next();
		// 	if(integer==2) {
		// 		iterator.remove();
		// 	}
		// }

		ArrayList var1 = new ArrayList();
		var1.add("a");
		Iterator var2 = var1.iterator();

		while(var2.hasNext()) {
			String var3 = (String)var2.next();
			var1.remove(var3);
		}

		Executors.newSingleThreadExecutor();
	}
}
