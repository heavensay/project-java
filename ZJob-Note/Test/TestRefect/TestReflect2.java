package TestRefect;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;


public class TestReflect2 extends TestCase{
	TestPublicObject tpo = new TestPublicObject();
	
	public void test1() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
	IllegalAccessException, InvocationTargetException, InstantiationException{
		
		Class c = TestPublicObject.class;
		// getMethod取得public方法，getDeclaredMethod取得所有方法
		Method m = c.getDeclaredMethod("setAab001", String.class);
		
		m.setAccessible(true);   //这样就可以访问private 的方法了,true反射的时候取消java语言访问检查
		TestPublicObject t2 = tpo.getClass().newInstance();
		c.getConstructor(int.class).newInstance(5);
		
		System.out.println(t2.getAab001());
		System.out.println(tpo.getAab001());
	}
	
}









