package TestRandom;



import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;


public class TestString {
	@Test
	public void test1(){
		String s = "浙江";
	//	assertEquals("浙江", s.substring(0, 3));
//		assertTrue(s.startsWith("浙江"));
	}
	@Test
	public void test2(){
		StringBuffer sb = new StringBuffer();
//		sb.append("ab\r\nc");
		sb.append("ab\r\nc");
		System.out.println(sb.toString());
	}
	@Test
	public void test3(){
		char c = '\r';
		System.out.println((byte)c);
	}
	@Test
	public void test5(){
		System.out.println(173 << 0);
	}
	
	@Test
	public void test6(){
		StringA a = new StringA();
		StringB b = new StringB();
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		t1.start();
		t2.start();
	}

	
	public void testListtoArray(){
		List list = new ArrayList();
		String[] s = new String[5];
		System.out.println("--dfdf- "+list.toArray().length+"  s[] :"+s.length);
	}

}
