package github.io.longo.lambda;

import java.util.List;

public class RoboCallTest04 {

    public static void main(String[] args) {
        
        List<Person> createShortList = Person.createShortList();
        RoboContactLambda robo = new RoboContactLambda();
        
        robo.phoneContacts(createShortList, p -> p.getAge() >= 16);
        
    }
}
