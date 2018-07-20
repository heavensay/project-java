package testvalidation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

public class TestValidation {


    @Test
    public void testSimpleValid(){
        User user = new User();
//        user.setName("tom");
//        user.setAge(5);
        ValidationUtil.validate(user);
    }

    /**
     * 正则匹配测试
     */
    @Test
    public void testRegRexValid(){
        User user = new User();
        user.setName("tom");
        user.setAge(16);
        user.setId(1);
        ValidationUtil.validate(user);
    }

    /**
     * 年龄规则测试
     * 国际化信息输出
     */
    @Test
    public void testAgeValid(){
        User user = new User();
        user.setName("tom");
        user.setAge(8);
        user.setId(1);
        ValidationUtil.validate(user);
    }



    @Rule
    public ExpectedException exceptedEx = ExpectedException.none();

    /**
     * 测试自定义的validation注解标记和逻辑验证类
     */
    @Test
    public void testCustomAnnotationValid(){
        User user = new User();
        List list = new ArrayList();
        list.add("father");
        list.add(null);
        list.add("mother");
        user.setRelations(list);

        exceptedEx.expect(ValidationException.class);
        exceptedEx.expectMessage("list中不能");

        ValidationUtil.validate(user);
    }

}
