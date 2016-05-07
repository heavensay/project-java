package testlog.jul;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * java.util.logging日志系统日志级别
 * SEVERE (highest value) 
 * 	WARNING 
*	INFO 
*	CONFIG 
*	FINE 
*	FINER 
*	 FINEST (lowest value) 
 * @author admin
 *
 */
public class BeanJul {

	private static Logger logger = Logger.getLogger(BeanJul.class.getName());
	
	public void f1(){

		logger.setLevel(Level.INFO); 
		
		
		logger.finest(" BeanJul finest ");
		logger.info("BeanJul f1() info");
		logger.warning(" BeanJul warning !");
		
		System.out.println(" BeanJul f1() over ");
	}
	
}
