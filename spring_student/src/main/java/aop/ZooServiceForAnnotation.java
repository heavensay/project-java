package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ZooServiceForAnnotation {

	
	
	@Before(value="execution(* aop..*.*(..))  && args(str)" , argNames="str")
	public void before(String str2) {
		System.out.println(" annotation before zoo ...  str" + str2);
	}

	@Pointcut(value="execution(* aop..*.*(..))")
	public void afterpoint(){
		System.out.println(" pppppp --");
	}
	
	@After(value="afterpoint()" ,argNames="")
	public void after() {
		System.out.println(" annotation after zoo ...");
	}

	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println(" annotation aruound before ");
		pjp.proceed();
		System.out.println(" annotation around after ");
	}
}
