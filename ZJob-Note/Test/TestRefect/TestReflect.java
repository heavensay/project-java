package TestRefect;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;


public class TestReflect extends TestCase{
	/**
	 * test method
	 * retult : only get the methods of public 
	 */
	public void test1Method(){
		Class c = TestObject.class;
		Method[] mArray = c.getMethods();
		for(Method method : mArray){
			System.out.println(method.toString());
		}
	}

	public void test2Method(){
		Class c = TestObject.class;
		Method[] mArray = c.getDeclaredMethods();
		for(Method method : mArray){
			System.out.println("name="+method.getName());
			System.out.println(method.getDeclaringClass());
			Class[] cArr = method.getParameterTypes();
			for (int i = 0;i<cArr.length;i++) {
				System.out.println("params:"+cArr[i]);
			}
			
			Class[] eArr = method.getExceptionTypes();
			for (Class e : eArr) {
				System.out.println("Exception:"+e);
			}
			System.out.println("-----------------");
		}
	}
	
	
	public void test3Method(){
		try {
			Object o = Class.forName("TestObject");  
			
			if(o instanceof TestObject){
				System.out.println("Use Class.forName() can create a class instance");
			}else if(o instanceof Class){
				System.out.println("it's a Class");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test4Constructor(){
		Class c = TestObject.class;
//		Constructor[] mArray = c.getDeclaredConstructors();  // get all Constructor
		Constructor[] mArray = c.getConstructors();           //always get public Constructor
		for(Constructor constructor : mArray){
			System.out.println("name="+constructor.getName());
			System.out.println(constructor.getDeclaringClass());
			Class[] cArr = constructor.getParameterTypes();
			for (int i = 0;i<cArr.length;i++) {
				System.out.println("params:"+cArr[i]);
			}
			
			Class[] eArr = constructor.getExceptionTypes();
			for (Class e : eArr) {
				System.out.println("Exception:"+e);
			}
			System.out.println("-----------------");
		}
	}
	
	/**
	 * invoke method
	 * @throws ClassNotFoundException, 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public void test5Invoke() throws ClassNotFoundException, SecurityException, NoSuchMethodException,
	IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Class c = Class.forName("TestObject");
		Class[] partypes = new Class[2];
		partypes[0] = int.class;
		partypes[1] = Integer.class;
//		partypes[1] = int.class;  fail
		
		
		Object[] os = new Object[]{1,new Integer(2)};
		Method m = c.getMethod("bagOrOrigin", partypes);
		m.invoke(new TestObject(), os);
		
	}
	
	/**
	 * test modifier     
	 * result:fail
	 */
	public void test6Modifier(){
		Class c = TestObject.class;
		int a = c.getModifiers();
		String sM = Modifier.toString(a);
		System.out.println(sM);
		Method[] mArray = c.getDeclaredMethods();
		for (Method method : mArray) {
			int b = method.getModifiers();
			System.out.println(method.getName()+"--"+Modifier.toString(b)+"---"+b);
		}
	}
	
	public void test7Array(){
		int a[] = new int[]{2,4,6};
		Object o = Array.newInstance(int.class, a);
	}
	/**
	 * dynamic get method
	 */
	public void test8getMethod() throws Exception{
		Method m  = TestObject.class.getMethod("getString"/*, new TestObject[0].getClass()*/);
		System.out.println(m.toString());
	}
	/**
	 *  new a instance;
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 */
	public void test9newConstructor() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
	InstantiationException, IllegalAccessException, InvocationTargetException{
		Class c = TestObject.class;
		Constructor cons = c.getConstructor(int.class);
		Object o = cons.newInstance(new Integer(8));
		System.out.println(o);
	}
	
	public void test01classpath() throws ClassNotFoundException{
		Class clazz = TestObject.class;
		System.out.println(clazz.getName());
		System.out.println(clazz.toString());
		System.out.println(clazz.getPackage().getName());
		System.out.println(clazz.getSimpleName());
		
		Class clazz2 = Class.forName("TestRefect.TestObject");
		assertNull(clazz);
	}
	
	public void test02(){
		Class dc = Dto.class;
		Field[] fields = dc.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName()+" : ");
		}
	}
	
}
class Dto{
	private String name;
	protected int id;
	public String classname;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}








