package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Person {

    private String givenName;
    private String surName;
    private int age;
    private String eMail;
    private String phone;
    private String address;

    private Person(Person.Builder builder) {

        givenName = builder.givenName;
        surName = builder.surName;
        age = builder.age;
        eMail = builder.eMail;
        phone = builder.phone;
        address = builder.address;
    }

    public String printCustom(Function<Person, String> f) {
        return f.apply(this);
    }
    
    public static class Builder {

        private String givenName = "";
        private String surName = "";
        private int age = 0;
        private String eMail = "";
        private String phone = "";
        private String address = "";

        public Person.Builder givenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Person.Builder surName(String surName) {
            this.surName = surName;
            return this;
        }

        public Person.Builder age(int val) {
            age = val;
            return this;
        }

        public Person.Builder email(String val) {
            eMail = val;
            return this;
        }

        public Person.Builder phoneNumber(String val) {
            phone = val;
            return this;
        }

        public Person.Builder address(String val) {
            address = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public static List<Person> createShortList() {
        List<Person> people = new ArrayList<>();

        people.add(new Person.Builder().givenName("Bob").surName("Baker").age(21).email("bob.baker@example.com")
                .phoneNumber("201-121-4678").address("44 4th St, Smallville, KS 12333").build());

        people.add(new Person.Builder().givenName("Jane").surName("Doe").age(25).email("jane.doe@example.com")
                .phoneNumber("202-123-4678").address("33 3rd St, Smallville, KS 12333").build());

        people.add(new Person.Builder().givenName("John").surName("Doe").age(25).email("john.doe@example.com")
                .phoneNumber("202-123-4678").address("33 3rd St, Smallville, KS 12333").build());

        people.add(new Person.Builder().givenName("James").surName("Johnson").age(45).email("james.johnson@example.com")
                .phoneNumber("333-456-1233").address("201 2nd St, New York, NY 12111").build());

        people.add(new Person.Builder().givenName("Joe").surName("Bailey").age(67).email("joebob.bailey@example.com")
                .phoneNumber("112-111-1111").address("111 1st St, Town, CA 11111").build());

        people.add(new Person.Builder().givenName("Phil").surName("Smith").age(55).email("phil.smith@examp;e.com")
                .phoneNumber("222-33-1234").address("22 2nd St, New Park, CO 222333").build());

        people.add(new Person.Builder().givenName("Betty").surName("Jones").age(85).email("betty.jones@example.com")
                .phoneNumber("211-33-1234").address("22 4th St, New Park, CO 222333").build());

        return people;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void printEasternName() {
        System.out.println("\nName: " + this.getSurName() + " " + this.getGivenName() + "\n" + "Age: " + this.getAge()
                + "  " + "\n" + "Phone: " + this.getPhone() + "\n" + "Address: " + this.getAddress());
    }
}
