package github.io.volong.validate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudentTest {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static void main(String[] args) {
        Student student = new Student();
        student.setName(null);
        student.setAddress("北京");
        student.setBirthday(new Date());
        student.setFriendName(null);
        student.setWeight(new BigDecimal(30));
        student.setEmail("yihailongqq.com");
        student.setSpellName("XXXXXX");


        List<String> messages = validate(student);
        messages.forEach(s -> System.out.println(s));
    }

    public static <T> List<String> validate(T t) {

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate(t);

        ArrayList<String> messageList = new ArrayList<>();

        for (ConstraintViolation<T> o : validate) {
            messageList.add(o.getMessage());
        }

        return messageList;
    }
}
