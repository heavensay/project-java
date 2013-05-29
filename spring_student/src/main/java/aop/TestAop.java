package aop;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.aspectj.SimpleAspectInstanceFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

	// 1、读取配置文件实例化一个IoC容器
	private static ApplicationContext context = new ClassPathXmlApplicationContext("aop/application.xml");
	
	@Test
	public void test1() {
		// 2、从容器中获取Bean
		IZoo zoo = context.getBean("zoo", IZoo.class);
		zoo.open();
	}

	@Test
	public void test2(){
		System.out.println(" test2 ");
	}
	
	@Test
	public void test3(){
		Tiger tiger = context.getBean("tiger",Tiger.class);
//		tiger.getName();
		tiger.before();
	}
	
	@Test public void test4ProxyFactoryBean(){
	     ApplicationContext cxt = new ClassPathXmlApplicationContext("aop/application-proxyfactorybean.xml");	
	     IZoo zoo = cxt.getBean("testproxyfactBean",IZoo.class);
	     zoo.open();
	}
	
	@Test public void test5ProxyFactoryBean() throws Exception{
		
		Zoo zoo = new Zoo();
		
		ProxyFactory factory = new ProxyFactory(zoo);
		factory.setInterfaces(new Class[]{IZoo.class});
		
//		Advisor advisor = new  DefaultPointcutAdvisor();
		
		Method after = ZooService.class.getMethod("after", null);
		AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
		expressionPointcut.setExpression("execution(* aop..*.*(..))");
		AspectInstanceFactory aif = new SimpleAspectInstanceFactory(ZooService.class);
		AbstractAspectJAdvice advice = new AspectJAfterAdvice(after, expressionPointcut, aif);
		Advisor advisor = new  AspectJPointcutAdvisor(advice);
		
		factory.addAdvisor(advisor);
		
//		Object o = factory.getProxy();
		IZoo proxy = (IZoo)factory.getProxy();
		proxy.open();
		
	}
	
//	@Test
//	public void test3() {
//		Resource resource = new ClassPathResource("aop/application.xml");
//		// 1、读取配置文件实例化一个aop容器
//		DefaultListableBeanFactory context = new XmlBeanFactory(resource);
//		
//		//autowired处理器使spring能自动解析装配bean中annotation。
//		AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//		processor.setBeanFactory(context);
//		context.addBeanPostProcessor(processor);
//		
//		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
//		Zoo zoo = context.getBean("zoo", Zoo.class);
//
//		System.out.println(zoo.getName()+"---"+zoo.getOpentime());
//		System.out.println(zoo.getTiger());
//		
//	}
}
