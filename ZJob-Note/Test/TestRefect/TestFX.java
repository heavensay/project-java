package TestRefect;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;


public class TestFX extends TestCase {
	
	/**
	 * result: 对于null可以强转，否则会ClassCastException
	 */
	public void test1(){
		String a = "c";
		String b = null;
		inField(a);  //failure
		inField(b);  //success
	}
	
	private void inField(Object o){
		TestObject t = (TestObject)o;
	}
	
	public void test2(){
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(5);
		List<?> l2 = l1;   // 只能检索数据，不能添加数据
//		l2.add(new Object());   //cannot pass in compile,
	}
	
	public void test3(){
		//List<?> l2 = new ArrayList<?>();  //不能通过编译 ，后面不能为？
	}
	
	
	public void test4(){
		Date date = new Date();
		List<Date> list = new ArrayList<Date>();
		list.add(date);
	}
	
	public void test5FF(){
		ff(TestObject.class);
	}
	
	public void test6(){
		Fx fx = new Fx();
		System.out.println(fx.getT());
	}
	
	public void ff(Class<?> c){
		System.out.println(c.getName());
	}
	
	class Fx<T>{
		private T a = (T)"ccc";
		T getT(){
			return a;
		}
	}
	
	public<T extends Date> void TestDown(List<T> list,T date){
		
	}
	
	public void TestDown(List<? super Date> list){
		
	}
}
