package testlog.apache_commons_logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class BeanApachelog {

	private static Log log = LogFactory.getLog(BeanApachelog.class);
	
	public void f1(){

		log.warn("BeanApachelog f1() warn ");
		
		log.debug("BeanApachelog f1() debug",new NullPointerException());//lo4j最后参数如果是exception,回打印堆载错误日志
		
		log.info("BeanApachelog f1() info");
		
		log.error("BeanApachelog f1() error");
		
		
		System.out.println(" BeanApachelog f1() over ");
	}
	
}
