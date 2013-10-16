package TestRefect.InjectPractice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class ProxyFactory {
	
	public static <T> T getDemoProxy(Class<T> clazz){
		for (Method method : clazz.getMethods()) {
			Annotation[] annotations =  method.getAnnotations();
			for (Annotation annotation : annotations) {
				
				Class c = InjectAnnotation.class;
				if(InjectAnnotation.class.equals(annotation.annotationType())){
					String time = ((InjectAnnotation)annotation).value();
					if("before".equals(time)){
						//Proxy.newProxyInstance(clazz.getClassLoader(), IDemo.class, h)
					}
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		ProxyFactory pf = new ProxyFactory();
		pf.getDemoProxy(Demo.class);
	}
}
