package tx;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tx.entity.DctConstant;
import tx.entity.User;
import tx.service.UserService;

/**
 * 声明式事务测试
 * @author banana
 *
 */
public class TestDeclareTx {

	/**
	 * 简单的插入一个用户
	 */
	@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:tx/application.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("tsd");
		user.setPassword("aagg");
		userService.saveTuser(user);
	}
	
	/**
	 * 简单的插入2个用户
	 */
	@Test
	public void test2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:tx/application.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("tsd");
		user.setPassword("aagg");
		userService.saveTuser2(user);
		System.out.println("  ****end insert user**** ");
	}
	
	/**
	 * 简单的插入2个用户
	 *  serviceA.method1()->method2()
	 *  
	 *  没有观察到有事务传播行为的发生,最总一起提交事务
	 */
	@Test
	public void test3() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:tx/application.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("tsd");
		user.setPassword("aagg");
		userService.saveTuser(user);
		userService.saveTuser2(user);
		
		System.out.println("  ****end insert user**** ");
	}
	
	/**
	 * 辅助测试事务中运行事务
	 * serviceA.method1()->serviceB.method2 
	 * 
	 * 这 2 个服务类的 2 个方法通过 Spring 的事务传播机制都工作在同一个事务中
	 */
	@Test
	public void test4(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:tx/application.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("test4");
		user.setPassword("444444");
		
		DctConstant dctConstant = new DctConstant();
		dctConstant.setTypecode("test4code");
		dctConstant.setTypename("test4name");
		
		userService.saveMix(user, dctConstant);
		System.out.println("  **** test4 over **** ");
	}
	
	
	/**
	 * 辅助测试事务中运行事务
	 * serviceA.method1()->serviceB.method2 
	 * 
	 * 这 2 个服务类的 2 个方法通过 Spring 的事务传播机制都工作在同一个事务中
	 * 
	 * RuntimeException  会回滚
	 */
	@Test
	public void test5(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:tx/application.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("test5");
		user.setPassword("55555");
		
		DctConstant dctConstant = new DctConstant();
		dctConstant.setTypecode("test5code");
		dctConstant.setTypename("test5name");
		
		userService.saveMixHaveRuntimeException(user, dctConstant);
		System.out.println("  **** test5 over **** ");
	}
	
	/**
	 * 辅助测试事务中运行事务
	 * serviceA.method1()->serviceB.method2 
	 * 
	 * Exception会自动提交
	 * 
	 * 这 2 个服务类的 2 个方法通过 Spring 的事务传播机制都工作在同一个事务中
	 * @throws Exception 
	 */
	@Test
	public void test6() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:tx/application.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("test5");
		user.setPassword("55555");
		
		DctConstant dctConstant = new DctConstant();
		dctConstant.setTypecode("test5code");
		dctConstant.setTypename("test5name");
		
		userService.saveMixHaveException(user, dctConstant);
		System.out.println("  **** test5 over **** ");
	}
	
	
}
