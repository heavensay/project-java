package testlog.apache_commons_logging;


import org.junit.Before;
import org.junit.Test;

public class TestApacheCommonsLogging{
	
	@Before
	public void before(){
		//初始化log4j日志系统
//		PropertyConfigurator.configure("bin/testlog/log4j.properties");
	}
	
	
	@Test
	public void test1Simplest() throws Exception{
		BeanApachelog bean1 = new BeanApachelog();
		bean1.f1();
	}
	
}
