package github.io.longo.lambda;

import java.util.List;

public class Test01ForEach {

    public static void main(String[] args) {
        
        List<Person> createShortList = Person.createShortList();
        createShortList.forEach(p -> p.printEasternName());
        
        createShortList.forEach(Person::printEasternName);
        
        createShortList.forEach(p -> System.out.println(p.printCustom(pp -> pp.getAddress())));
    }
}
