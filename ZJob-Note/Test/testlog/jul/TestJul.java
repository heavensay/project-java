package testlog.jul;


import org.junit.Before;
import org.junit.Test;

public class TestJul{
	
	@Before
	public void before(){
		//初始化log4j日志系统
//		PropertyConfigurator.configure("bin/testlog/log4j.properties");
	}
	
	
	@Test
	public void test1Simplest() throws Exception{
		BeanJul bean1 = new BeanJul();
		bean1.f1();
	}
	

}
