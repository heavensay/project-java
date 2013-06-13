package aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.aspectj.SimpleAspectInstanceFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

	// 1、读取配置文件实例化一个IoC容器
	private static ApplicationContext context = null;
	
	@Before
	public void before(){
//		context = new ClassPathXmlApplicationContext("aop/application.xml");
	}
	
	@Test
	public void test1() {
		// 2、从容器中获取Bean
		context = new ClassPathXmlApplicationContext("aop/application.xml");
		IZoo zoo = context.getBean("zoo", IZoo.class);
		zoo.open();
		System.out.println("-------****---------");
		zoo.close(" happy day");
	}

	@Test public void test4ProxyFactoryBean(){
	     ApplicationContext cxt = new ClassPathXmlApplicationContext("aop/application-proxyfactorybean.xml");	
	     IZoo zoo = cxt.getBean("testproxyfactBean",IZoo.class);
	     zoo.open();
	}
	
	/**
	 * 手动new相关aop类，来实现切面功能。
	 * spring的配置和文件，最终也是生成相关类来实现aop功能
	 * @throws Exception
	 */
	@Test public void test5Assembly() throws Exception{
		
		Zoo zoo = new Zoo();
		
		ProxyFactory factory = new ProxyFactory(zoo);
		factory.setInterfaces(new Class[]{IZoo.class});
		
//		Advisor advisor = new  DefaultPointcutAdvisor();
		
		Method after = ZooService.class.getMethod("after", null);
		Method before = ZooService.class.getMethod("before", String.class);
		Method around = ZooService.class.getMethod("around", ProceedingJoinPoint.class);
		
		//对所有方法进行拦截
		AspectJExpressionPointcut expressionPointcut1 = new AspectJExpressionPointcut();
		expressionPointcut1.setExpression("execution(* aop..*.*(..))");
		
		//测试参数的传递,在class文件中没有生成调试信息的话，JVM运行的时候是获取不到方法的参数名称
		//所以需要配置的时候有方法的参数的名称
		AspectJExpressionPointcut expressionPointcut2 = new AspectJExpressionPointcut();
		expressionPointcut2.setExpression("execution(* aop..*.*(..)) and args(str)");
		expressionPointcut2.setParameterNames(new String[]{"str"});
		expressionPointcut2.setParameterTypes(new Class[]{String.class});
		
		AspectInstanceFactory aif = new SimpleAspectInstanceFactory(ZooService.class);
		AbstractAspectJAdvice afterAdvice = new AspectJAfterAdvice(after, expressionPointcut1, aif);
		AbstractAspectJAdvice beforeAdvice = new AspectJMethodBeforeAdvice(before,expressionPointcut2,aif);
		//advice(通知)上的的参数名也需要写明
		beforeAdvice.setArgumentNames("str");
		
		AbstractAspectJAdvice aroundAdvice = new AspectJAroundAdvice(around,expressionPointcut1,aif);
		
		Advisor afterAdvisor = new  AspectJPointcutAdvisor(afterAdvice);
		Advisor beforeAdvisor = new  AspectJPointcutAdvisor(beforeAdvice);
		Advisor aroundAdvisor = new  AspectJPointcutAdvisor(aroundAdvice);
		
		//ExposeInvocationInterceptor需要加上，不加在chain上，会出错
		factory.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		factory.addAdvisor(afterAdvisor);
		factory.addAdvisor(beforeAdvisor);
		factory.addAdvisor(aroundAdvisor);
		
//		Object o = factory.getProxy();
		IZoo proxy = (IZoo)factory.getProxy();
		proxy.open();
		
		System.out.println("----------------****-----------------");
		
		proxy.close(" happy day ");
	}
	
	@Test
	public void test6AopAnnotationStyle() {
		context = new ClassPathXmlApplicationContext("aop/application-annotation.xml");
		
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		IZoo zoo = context.getBean("zoo", IZoo.class);
		zoo.open();
		
		System.out.println("----------------****-----------------");
		
		zoo.close(" happy day ");
	}
}
