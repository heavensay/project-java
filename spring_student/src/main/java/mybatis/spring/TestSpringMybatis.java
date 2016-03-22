package mybatis.spring;
import mybatis.User;
import mybatis.spring.service.UserService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringMybatis {

	@Test
	public void test1(){
		// 1、读取配置文件实例化一个IoC容器
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"mybatis/spring/spring-ds-master.xml");
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		UserService userService = context.getBean(UserService.class);
		
		User dto = new User();
		dto.setName("TestSpringMybatis test1");
		
		userService.insertUser(dto);
		System.out.println("********over test1********");
	}
	
}
