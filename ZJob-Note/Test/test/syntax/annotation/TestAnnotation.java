package test.syntax.annotation;

import org.junit.Test;

public class TestAnnotation {

    /**
     * 测试@Repeatable注解
     */
    @Test
    public void testRepeatable(){
        Roles roles = User.class.getAnnotation(Roles.class);
        System.out.println(roles);  //roles

        Role role = User.class.getAnnotation(Role.class);
        System.out.println(role);   //null

        Role[] roles2 = User.class.getAnnotationsByType(Role.class);
        System.out.println(roles2.length);          // 2
    }

}
