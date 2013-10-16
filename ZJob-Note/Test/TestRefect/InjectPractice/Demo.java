package TestRefect.InjectPractice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Demo implements InvocationHandler{
	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before the target method to do something ");
		return null;
	}
}
