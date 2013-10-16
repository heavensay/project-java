package TestRefect;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import junit.framework.TestCase;


public class TestAnnotation extends TestCase {
	
	public void test1(){
		Class c = AnnotationBeTest.class;
		boolean flag = c.isAnnotation();
		Annotation[] annontation = c.getAnnotations();
		
		
		Method[] method5 = c.getDeclaredMethods();
		for (Method method2 : method5) {
			
			Annotation[] ann2 = method2.getAnnotations();
			boolean bl = method2.isAnnotationPresent(AnnotationObject.class);
			AnnotationObject ann3 = method2.getAnnotation(AnnotationObject.class);
			for (Annotation annotation : ann2) {
				System.out.println(annotation.toString());
			}
			
		}
		
	}
	
	public void test2(){
		Class c = AnnotationBeTest2.class;
		Method[] method = c.getDeclaredMethods();
		Method method2 = c.getEnclosingMethod();
	}
	
}
