package TestRefect.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Dynamic implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(method.getName().equals("before")){
			System.out.println("invocation before");
		}
		method.invoke(proxy, args);
		System.out.println("---invocation after");
		return null;
	}

}
