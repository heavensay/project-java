package TestRefect.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.Map;

import TestRefect.InjectPractice.Demo;
import TestRefect.InjectPractice.IDemo;

import junit.framework.TestCase;

public class TestDynamic extends TestCase {
	public void test1(){
		DynamicProxy dp = new DynamicProxy(new ProcessImp());
		Process process = dp.getDynamicProxy();
		process.hit("adsasd");
		process.process();
		process.superProcess();
	}
	
	public void test2multipleInterface(){
		Class[] c = Process.class.getInterfaces();
		ProcessImp processImp = new ProcessImp();
		Class c4 = processImp.getClass();
		Class[] interfaces = c4.getInterfaces();
		for (Class class1 : interfaces) {
			System.out.println(class1.getName());
		}
		System.out.println(ProcessImp.class.getDeclaredClasses().length);
		System.out.println(Process.class.getInterfaces());
		System.out.println("ProcessImp getInterfaes:"+c4.getInterfaces());
	}
	
	/**
	 * test interface type
	 */
	public void test3interface(){
		assertTrue(SuperProcess.class.isAssignableFrom(Process.class));
	} 
}
