package testlog.slf4j;


import org.junit.Before;
import org.junit.Test;

public class TestSlf4j{
	
	@Before
	public void before(){
		//初始化log4j日志系统
//		PropertyConfigurator.configure("bin/testlog/log4j.properties");
	}
	
	
	@Test
	public void test1Simplest() throws Exception{
		BeanSlf4j bean1 = new BeanSlf4j();
		bean1.f1();
	}
	
	@Test
	public void test2Level2() throws Exception{
	}	

}
