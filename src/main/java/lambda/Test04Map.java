package lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test04Map {

	public static void main(String[] args) {

		List<Person> createShortList = Person.createShortList();

		int sum = createShortList.stream().filter(p -> p.getAge() >= 16).mapToInt(p -> p.getAge()).sum();

		System.out.println("sum age is:" + sum);

		// key 不重复时的处理方式
		System.out.println("key 不重复 ---------------------");
		Map<String, Integer> collect1 = createShortList.stream()
				.collect(Collectors.toMap(Person::getGivenName, Person::getAge));

		collect1.forEach((k, v) -> System.out.println(k + ":" + v));

		// key 重复时的处理方式
		System.out.println("key 重复 ---------------------");
		// 最后一个参数的意思是：如果 p1, p2 的 key 重复，那么以 p1 的 key 为准
		Map<String, Integer> collect2 = createShortList.stream()
				.collect(Collectors.toMap(Person::getSurName, Person::getAge, (p1, p2) -> p1));
		
		collect2.forEach((k, v) -> System.out.println(k + ":" + v));
	}
}
