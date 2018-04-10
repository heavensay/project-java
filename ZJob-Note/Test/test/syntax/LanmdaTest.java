package test.syntax;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class LanmdaTest {

    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        f(new String[]{"1","2"});
        list.toString();

//        String s = list.stream().reduce("",(strs, str)->strs+str);
    }

    private void f(String[] args){
        String s = Stream.of(args).reduce("",(strs, str)->{
                    return  strs+","+str;
                }
        );

        System.out.println(s);
    }


    @Test
    public void test2(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        String s = list.stream().reduce("",(strs, str)->{
                if("".equals(strs)){
                    return str;
                }
                return  strs+","+str;
                }
            );
            System.out.println(s);
    }

    @Test
    public void test3(){
        StringBuffer buffer = new StringBuffer("abcde");
        System.out.println(buffer.substring(0,buffer.length()-1));
        System.out.println(buffer.toString());
    }


    /**
     * 匿名内部类写法
     */
    @Test
    public void anomymousTest(){
        //Java 8 way:
        new Thread( () -> System.out.println("In Java8!") ).start();

        new Thread(() -> {
            System.out.println("hello world!");
        }).start();
    }


    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach( n -> System.out.println(n) );
    }

}
