package testlog.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeanSlf4j {

	private static Logger logger = LoggerFactory.getLogger(BeanSlf4j.class);
	
	public void f1(){

		logger.warn("BeanSlf4j f1() warn {},{},{},{}","a",1,2,3);
		
		logger.debug("BeanSlf4j f1() debug,{}",2,3,new NullPointerException());//lo4j最后参数如果是exception,回打印堆载错误日志
		
		logger.info("BeanSlf4j f1() info");
		
		logger.error("BeanSlf4j f1() error");
		
		
		System.out.println(" BeanSlf4j f1() over ");
	}
	
}
