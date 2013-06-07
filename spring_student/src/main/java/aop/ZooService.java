package aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class ZooService {
	
	public void before(String str2){
		System.out.println(" before zoo ...  str"+str2);
	}
	
	public void after(){
		System.out.println(" after zoo ...");
	}
	
	public void around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println(" aruound before ");
		pjp.proceed();
		System.out.println(" around after ");
	}
}
