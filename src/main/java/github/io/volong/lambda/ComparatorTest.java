package github.io.volong.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    public static void main(String[] args) {
        
        List<Person> personList = Person.createShortList();
        
        // 使用匿名内部类的方式
        Collections.sort(personList, new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o1.getSurName().compareTo(o2.getSurName());
            }
            
        });
        
        System.out.println("=== Sorted Asc SurName ===");
        for (Person p : personList) {
            System.out.println(p.getSurName());
        }
        
        // 使用 Lambda 方式
        
        Collections.sort(personList, (Person p1, Person p2) -> p1.getSurName().compareTo(p2.getSurName()));
        
        System.out.println("=== Sorted Asc SurName ===");
        for (Person p : personList) {
            System.out.println(p.getSurName());
        }
        
        System.out.println("=== Sorted Desc SurName ===");
        
        Collections.sort(personList, (Person p1, Person p2) -> p2.getSurName().compareTo(p1.getSurName()));
        for (Person p : personList) {
            System.out.println(p.getSurName());
        }
    }
}
