package test.syntax.lambda;

import org.junit.Test;

public class LambdaTest {

    /**
     * 定义匿名函数
     */
    @Test
    public void anomyousTest(){
        /**
         *IHello hello = new Ihello(){
         *       public void sayHello(String str){
         *           System.out.println(str);
         *       }
         *}
         *hello.sayHello("world");
         */
        IHello hello = s -> System.out.println(s);
        hello.sayHello("world");
    }

    /**
     * ObjectRef::methodName
     * 所引用的方法其实是Lambda表达式的方法体的实现
     *
     * 引用静态方法调用
     */
    @Test
    public void refStaticMethodTest(){
        IHello hello = System.out::println;
        hello.sayHello(" ref static method ");
    }


}
