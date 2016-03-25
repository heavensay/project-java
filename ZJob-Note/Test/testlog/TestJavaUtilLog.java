package testlog;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * java.util.logging.logger默认的级别是INFO，比INFO更低的日志将不显示。
 * Logger的默认级别定义是在jre安装目录的lib下面。
 * Limit the message that are printed on the console to INFO and above. 
 * java.util.logging.ConsoleHandler.level = INFO
 * @author admin
 *
 */
public class TestJavaUtilLog {

	@Test
	public void test1(){
        Logger log = Logger.getLogger(TestJavaUtilLog.class.getName()); 
        log.setLevel(Level.INFO); 
        Logger log1 = Logger.getLogger("lavasoft"); 
        System.out.println(log==log1);     //true 
        Logger log2 = Logger.getLogger("lavasoft.blog"); 
        log2.setLevel(Level.WARNING); 

        log.info("log aaa"); 
        log2.info("log2 bbb"); 
        log2.fine("log2 fine"); 
	}
	
	@Test
	public void test2() throws Exception{
        Logger log = Logger.getLogger("lavasoft"); 
        log.setLevel(Level.INFO); 
        Logger log1 = Logger.getLogger("lavasoft"); 
        System.out.println(log==log1);     //true 
        Logger log2 = Logger.getLogger("lavasoft.blog"); 
//        log2.setLevel(Level.WARNING); 

        ConsoleHandler consoleHandler =new ConsoleHandler(); 
        consoleHandler.setLevel(Level.ALL); 
        log.addHandler(consoleHandler); 
        FileHandler fileHandler = new FileHandler("bin/testlog/testjavatuillog%g.log"); 
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new MyLogHander()); //默认存储格式为xml
        log.addHandler(fileHandler); 
        log.info("aaa"); 
        log2.info("bbb"); 
        log2.fine("fine"); 
	}
	
	
}
class MyLogHander extends Formatter { 
    @Override 
    public String format(LogRecord record) { 
            return record.getLevel() + ":" + record.getMessage()+"\n"; 
    } 
}