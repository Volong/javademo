package lambda;

import java.util.List;

public class RoboContactLambda {

    public void phoneContacts(List<Person> pl, Predicate<Person> pre) {
        for (Person p : pl) {
            if (pre.test(p)) {
                roboCall(p);
            }
        }
    }
    
    public void roboCall(Person p) {
        System.out.println(
                "Calling " + p.getGivenName() + " " + p.getSurName() + " age " + p.getAge() + " at " + p.getPhone());
    }

    public void roboEmail(Person p) {
        System.out.println(
                "EMailing " + p.getGivenName() + " " + p.getSurName() + " age " + p.getAge() + " at " + p.geteMail());
    }

    public void roboMail(Person p) {
        System.out.println(
                "Mailing " + p.getGivenName() + " " + p.getSurName() + " age " + p.getAge() + " at " + p.getAddress());
    }
}
