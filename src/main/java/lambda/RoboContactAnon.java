package lambda;

import java.util.List;

public class RoboContactAnon {

    public void phoneContacts(List<Person> p1, MyTest<Person> test) {
        for (Person p : p1) {
            if (test.test(p)) {
                roboCall(p);
            }
        }
    }
    
    public void emailContacts(List<Person> p1, MyTest<Person> test) {
        for (Person p : p1) {
            if (test.test(p)) {
                roboEmail(p);
            }
        }
    }
    
    public void mailContacts(List<Person> p1, MyTest<Person> test) {
        for (Person p : p1) {
            if (test.test(p)) {
                roboMail(p);
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
