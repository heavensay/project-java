package TestRefect.InjectPractice;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

public class PoxyTest extends TestCase{
	public void test1(){
		IDemo dp = ProxyFactory.getDemoProxy(Demo.class);
		dp.dosomething();
	}
	
	/**
	 * test interface type
	 */
	public void test2interface(){
		assertFalse(Demo.class.getInterfaces()[0].isAssignableFrom(Map.class));
		assertTrue(Demo.class.getInterfaces()[0].isAssignableFrom(IDemo.class));
	} 
	
	public void test3Main(){
		Main.print_class(Demo.class);
		Main.print_class(HashMap.class);
	}
	
}
