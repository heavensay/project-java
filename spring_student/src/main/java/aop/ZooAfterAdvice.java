package aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class ZooAfterAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnobject) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(" zoo has been opening ...after returening advice "+returnobject);
	}

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(" zoo has been opening ...after returening advice override");
	}

}
