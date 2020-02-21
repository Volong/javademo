package github.io.longo.lambda;

import java.util.List;
import java.util.stream.Collectors;

public class Test03toList {

	public static void main(String[] args) {
		
		List<Person> createShortList = Person.createShortList();
		
		List<Person> collect = createShortList.stream()
					   .filter(p -> p.getAge() >= 80)
					   .collect(Collectors.toList());
		
		collect.forEach(Person::printEasternName);
	}
}
