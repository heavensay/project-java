package testjvm.testclassloader.hot;

import java.lang.reflect.Method;
import java.net.URL;

public class TestHotSwap {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception {
		A a = new A();
		B b = new B();
		a.setB(b);

		System.out.println("A classLoader is :"+ a.getClass()
				.getClassLoader());
		System.out.println("B classLoader is :"+  b.getClass()
				.getClassLoader());
		System.out.println("A.b classLoader is :"+  a.getB().getClass()
				.getClassLoader());

		HotSwapClassLoader c1 = new HotSwapClassLoader(new URL[] { new URL(
				"file:/D:/Ecpworkspace/ZJob-Note/bin/") }, a.getClass()
				.getClassLoader());
		Class clazz = c1.load("testjvm.testclassloader.hot.A");
		Object aInstance = clazz.newInstance();

		Method method1 = clazz.getMethod("setB", B.class);
		method1.invoke(aInstance, b);

		Method method2 = clazz.getMethod("getB", null);
		Object bInstance = method2.invoke(aInstance, null);
		System.out.println(" reloaded A classLoader is :"+ clazz.getClassLoader());
		System.out.println(" reloaded A.b classLoader is :"+ bInstance
				.getClass().getClassLoader());
	}
}