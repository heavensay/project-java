package testvalidation;

import org.junit.Test;

public class TestValidation {

    @Test
    public void testSimpleValid(){
        User user = new User();
//        user.setName("tom");
//        user.setAge(5);

        ValidationUtil.validate(user);

    }

}
