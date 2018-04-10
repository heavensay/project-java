package test.syntax;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamTest {

    @Test
    public void test1(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.stream().map( (e)-> e+2 ).forEach(System.out::println);
        //System.out.println(count);

    }


    @Test
    public void test2(){
        Integer num = new Integer(2);
        Integer num2 = null;
        System.out.println(Optional.ofNullable(num).orElse(6));
        System.out.println(Optional.ofNullable(num2).orElse(7));
    }
}
