package test.syntax.lambda;

import org.junit.Test;

import java.util.Optional;

/**
 * @Author: lijianyu
 * @Date: 2018/7/12 11:43
 */
public class OptionalTest {

    /**
     *
     */
    @Test
    public void testNullMapNoException(){
        String s = null;
        Object obj = Optional.ofNullable(s).map( e->e.split(",")).orElse(null);
        System.out.println(obj);
    }

}
