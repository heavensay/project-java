package test.syntax.lambda;

/**
 *函数式接口 - Functional Interface
 * 这个接口里面只能有一个抽象方法
 */
@FunctionalInterface
public interface IHello {
    public void sayHello(String str);
}
