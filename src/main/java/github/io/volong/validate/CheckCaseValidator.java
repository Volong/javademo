package github.io.volong.validate;

import com.sun.org.apache.bcel.internal.generic.CHECKCAST;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;

    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        if (caseMode == CaseMode.UPPER) {
            return value.equals(value.toUpperCase());
        }

        return value.equals(value.toLowerCase());

    }
}
