package TestRefect.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProxy implements InvocationHandler{
	Process invocation ;
	DynamicProxy(Process invocation){
		this.invocation = invocation;
	}
	
	public Process getDynamicProxy(){
		Class[] interfaces = invocation.getClass().getInterfaces();
		Process process = (Process)Proxy.newProxyInstance(SuperProcess.class.getClassLoader(),
				new Class[]{Process.class}/*new Class[]{SuperProcess.class}*/, this);	
		return process;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(method.getName().equals("hit")){
			System.out.println("hit before");
		}else if(method.getName().equals("process")){
			System.out.println("process before");
		}else if(method.getName().equals("superProcess")){
			System.out.println("subprocess before");
		}
		method.invoke(invocation, args);
		System.out.println("---method after");
		return null;
	}
	
}
