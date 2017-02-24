package testprocess;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessUtilTest {

	private static Logger logger = LoggerFactory.getLogger(ProcessUtilTest.class);
	
	private static String scriptPath = "C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/";
	
	@Test
	public void processUtilTest() throws Exception{
		String commandPath = scriptPath +  "start-sleep.bat";
		StringBuffer command =  new StringBuffer("cmd /c "+commandPath);
		
		Long timeout = 25*1000L;
		ProcessHandlerResult handlerResult = ProcessUtil.buildProcessHandlerResult();
		logger.debug("process begin");
		ProcessUtil.exec(command.toString(), timeout, handlerResult);
		Thread.sleep(1000L);
		logger.debug("hasResult:{},exitValue:{}",handlerResult.isHasResult(),handlerResult.getExitCode());
		Thread.sleep(30000L);
		logger.debug("hasResult:{},exitValue:{}",handlerResult.isHasResult(),handlerResult.getExitCode());
	}
	
}
