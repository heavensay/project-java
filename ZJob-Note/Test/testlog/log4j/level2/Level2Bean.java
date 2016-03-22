package testlog.log4j.level2;

import org.apache.log4j.Logger;


public class Level2Bean {
	private static Logger logger = Logger.getLogger(Level2Bean.class);
	
	public void f1(){

		logger.warn("Level2Bean f1() warn");
		
		logger.debug("Level2Bean f1() debug");
		
		logger.info("Level2Bean f1() info");
		
		logger.error("Level2Bean f1() error");
		
		System.out.println(" Level2Bean f1() over ");
	}
}
