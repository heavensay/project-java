package ioc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class TestIoc {

	/**
	 * 
	 */
	@Test
	public void test1Basic() {
		// 1、读取配置文件实例化一个IoC容器
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"ioc/application.xml");
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		Zoo zoo = context.getBean("zoo", Zoo.class);

		System.out.println(zoo.getName()+"---"+zoo.getOpentime());
		// Tiger tiger = context.getBean("tiger", Tiger.class);
		Tiger tiger = context.getBean(Tiger.class);
		System.out.println(tiger);
		System.out.println(zoo.getOpentime());
	}

	/**
	 * XmlBeanFactory只提供了基本的容器功能，而ApplicationContext扩展了很多功能(容器自动添加BeanFactoryPostProcessor->CustomEditorConfigurer功能)。
	 */
	@Test(expected=Exception.class)
	public void test2PlainFactory() {
		Resource resource = new ClassPathResource("ioc/application.xml");
		// 1、读取配置文件实例化一个IoC容器
		DefaultListableBeanFactory context = new XmlBeanFactory(resource);
		
		//autowired处理器使spring能自动解析装配bean中annotation。
		AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
		processor.setBeanFactory(context);
		context.addBeanPostProcessor(processor);
		
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		Zoo zoo = context.getBean("zoo", Zoo.class);
		System.out.println(zoo.getTiger());
		System.out.println(zoo.getOpentime());
	}
	
	@Test
	public void test3BeanDefinitionReader(){
		DefaultResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("/ioc/application.xml");
//		Resource resource = new FileSystemResource("application.xml");
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		BeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		
		int count = reader.loadBeanDefinitions(resource);
		String[] beanDefinitionNames = reader.getRegistry().getBeanDefinitionNames();
		System.out.println("----------------------");
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
		System.out.println("----------------------");
		System.out.println(count);
		System.out.println(reader.getRegistry().getBeanDefinitionCount());
		
		BeanDefinition beanDefinition = registry.getBeanDefinition("zoo");
		
	}
}
