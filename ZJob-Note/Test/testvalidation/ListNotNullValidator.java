package testvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class ListNotNullValidator implements ConstraintValidator<ListNotNull,List> {

    @Override
    public void initialize(ListNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext context) {
        if(list != null){
            return list.stream().allMatch( e -> {
                if (e == null)  return false;
                return true;
            });
        }
        return true;
    }
}
