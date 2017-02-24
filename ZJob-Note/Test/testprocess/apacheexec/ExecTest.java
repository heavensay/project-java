package testprocess.apacheexec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testlog.StdOutErrRedirect;

/**
 * 
 * apache exec工具集测试
 * @author ljy
 *
 */
public class ExecTest {

	private static Logger logger = LoggerFactory.getLogger(ExecTest.class);
	
	private static String scriptPath = "C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/";
	
	/**
	 * 简单命令执行测试(阻塞)
	 * @throws ExecuteException
	 * @throws IOException
	 */
	@Test
	public void syncExecTest() throws ExecuteException, IOException{
		
		String commandPath = scriptPath +  "start-sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(3);
		logger.debug("exec begin");
		int exitValue = executor.execute(cmdLine);//会阻塞
		logger.debug("exitvalue:{}",exitValue);
	}
	
	/**
	 * Watchdog测试
	 * 	超时关闭Process
	 * @throws Exception
	 */
	@Test
	public void timeoutWatchdogTest() throws Exception{
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
	
	/**
	 * ExecuteResultHandler 非阻塞执行Process测试
	 * @throws Exception
	 */
	@Test
	public void asyncProcessTest() throws Exception{
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		ExecuteWatchdog watchdog = new ExecuteWatchdog(3*1000); 
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
		
		executor.setWatchdog(watchdog);  
		  
		executor.setExitValue(1);  
		logger.debug("exec begin");
		executor.execute(cmdLine,resultHandler);//非阻塞执行

		logger.debug("waitfor");
		resultHandler.waitFor();	//阻塞执行process运行完成
		logger.debug("hasresult:{}",resultHandler.hasResult());
	}
	
	/**
	 * Process产生信息打印测试
	 * 		自定义信息输出流
	 * @throws Exception
	 */
	@Test
	public void printProcessLogTest() throws Exception{
		StdOutErrRedirect.redirectSystemOutAndErrToLog();//系统输出流重定向
		
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		ExecuteWatchdog watchdog = new ExecuteWatchdog(3*1000); 
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
		
		executor.setWatchdog(watchdog);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//process内容打印
		PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
		executor.setStreamHandler(streamHandler);
		executor.setExitValue(1);
		logger.debug("exec begin");
		executor.execute(cmdLine,resultHandler);//非阻塞执行

		logger.debug("waitfor");
		resultHandler.waitFor();	//阻塞执行process运行完成
		logger.debug("hasresult:{}",resultHandler.hasResult());
		logger.debug("result:{}",baos.toString());
	}
	
	/**
	 * Process产生信息打印测试
	 * 		重定向系统流，以打印Process产生的信息
	 * @throws Exception
	 */
	@Test
	public void printProcessLogTest2() throws Exception{
		StdOutErrRedirect.redirectSystemOutAndErrToLog();//系统输出流重定向
		
		String commandPath = scriptPath +  "sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		ExecuteWatchdog watchdog = new ExecuteWatchdog(3*1000); 
		
		CommandLine cmdLine = CommandLine.parse(command.toString());
		DefaultExecutor executor = new DefaultExecutor();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
		
		executor.setWatchdog(watchdog);  
		  
		executor.setExitValue(1);
		logger.debug("exec begin");
		executor.execute(cmdLine,resultHandler);//非阻塞执行

		logger.debug("waitfor");
		resultHandler.waitFor();	//阻塞执行process运行完成
		logger.debug("hasresult:{}",resultHandler.hasResult());
	}
	
	public static void main(String[] args)throws Exception{
		ExecTest execTest = new ExecTest();
		execTest.printProcessLogTest2();
	}
}
