package test.syntax.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * findFirst
 orElse
 streams
 filter
 map
 return Optional.ofNullable(
 list.stream().filter((e) -> e.get(PRODUCT_ID_KEY) == prdId).map((e) -> e.get(COUNT_KEY))
 .findFirst().orElse(null)).orElse(new Integer(0));
 */
public class StreamTest {

    static List<String> lists=new ArrayList<String >();
    {
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");
    }


    /**
     * filter简单测试
     */
    @Test
    public void testFilter(){
        lists.stream().filter( s-> s.startsWith("a")).forEach(System.out::println);

        String first = lists.stream().filter( s-> s.startsWith("a")).findFirst().orElse("z1");
        System.out.println(first);
    }

    @Test
    public void mapTest(){
        lists.stream().map( String::toUpperCase).forEach(System.out::println);
        System.out.println("=================");
        lists.stream().map(  s -> s.toUpperCase()).forEach(System.out::println);
//        lists.stream().map( s -> System.out::println );
    }

    /**
     * 空list，能否map
     */
    @Test
    public void testEmptyListForMap(){
        List emptyList = new ArrayList();
        emptyList.stream().map( e -> {
            System.out.println("map");
            System.out.println(e);
            return e;
        }).collect(Collectors.toList());
    }

}
