package testlog.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeanSlf4j {

	private static Logger logger = LoggerFactory.getLogger(BeanSlf4j.class);
	
	public void f1(){

		logger.warn("BeanSlf4j f1() warn {},{}","a",2);
		
		logger.debug("BeanSlf4j f1() debug");
		
		logger.info("BeanSlf4j f1() info");
		
		logger.error("BeanSlf4j f1() error");
		
		System.out.println(" BeanSlf4j f1() over ");
	}
	
}
