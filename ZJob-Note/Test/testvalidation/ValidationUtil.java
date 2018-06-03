package testvalidation;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

    public static <T>  void validate(T t) throws ValidationException {

        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = vFactory.getValidator();
        Set<ConstraintViolation<T>> set =  validator.validate(t);
        if(set.size()>0){
            StringBuilder validateError = new StringBuilder();
            for(ConstraintViolation<T> val : set){
                validateError.append(val.getMessage());
            }
            throw new ValidationException(validateError.toString());
        }
    }

}
