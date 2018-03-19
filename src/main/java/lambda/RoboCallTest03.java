package lambda;

import java.util.List;

public class RoboCallTest03 {

    public static void main(String[] args) {
        List<Person> createShortList = Person.createShortList();
        
        RoboContactAnon robo = new RoboContactAnon();
//        robo.phoneContacts(createShortList, new MyTest<Person>() {
//            
//            @Override
//            public boolean test(Person p) {
//                return p.getAge() >= 16;
//            }
//        });
        
        // 我也可以直接使用 Lambda 表达式，为什么要 new 一个匿名内部类？费解
        robo.phoneContacts(createShortList, m -> m.getAge() >= 16);
    }
}
