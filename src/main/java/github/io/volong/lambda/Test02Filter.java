package github.io.volong.lambda;

import java.util.List;

public class Test02Filter {

    public static void main(String[] args) {
        
        List<Person> createShortList = Person.createShortList();
        
        createShortList
            .stream()
            .filter(p -> p.getAge() >= 16)
            .forEach(Person::printEasternName);
        
        createShortList.stream()
            .filter(p -> p.getAge() >= 18 && p.getAge() <= 25)
            .forEach(p -> p.printEasternName());
    }
}
