package testlog.log4j;

import org.apache.log4j.Logger;

public class Bean1 {

	private static Logger logger = Logger.getLogger(Bean1.class);
	
	public void f1(){

		logger.warn("bean1 f1() warn");
		
		logger.debug("bean1 f1() debug");
		
		logger.info("bean1 f1() info");
		
		logger.error("bean1 f1() error");
		
		System.out.println(" f1() over ");
	}
	
}
