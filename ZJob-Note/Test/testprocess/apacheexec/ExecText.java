package testprocess.apacheexec;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecText {

private static Logger logger = LoggerFactory.getLogger(ExecText.class);
	
	private static String scriptPath = "C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/";
	
	@Test
	public void basicExecTest() throws ExecuteException, IOException{
		
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		logger.debug("exec begin");
		int exitValue = executor.execute(cmdLine);//会阻塞
		logger.debug("exitvalue:{}",exitValue);
	}
	
	/**
	 * process超时关闭测试
	 * @throws Exception
	 */
	@Test
	public void watchdogTest() throws Exception{
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		ExecuteWatchdog watchdog = new ExecuteWatchdog(3*1000); 
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		executor.setWatchdog(watchdog);  
		  
		executor.setExitValue(1);  
		logger.debug("exec begin");
		int exitValue = executor.execute(cmdLine);//会阻塞
		logger.debug("exitvalue:{}",exitValue);
	}
	
	@Test
	public void t() throws Exception{
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		ExecuteWatchdog watchdog = new ExecuteWatchdog(3*1000); 
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
		
		executor.setWatchdog(watchdog);  
		  
		executor.setExitValue(1);  
		logger.debug("exec begin");
		executor.execute(cmdLine,resultHandler);

		logger.debug("waitfor");
		resultHandler.waitFor();
		logger.debug("result:{}",resultHandler.hasResult());
		
		
	}
}
